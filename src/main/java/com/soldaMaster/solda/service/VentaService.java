package com.soldaMaster.solda.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.CuotaRequest;
import com.soldaMaster.solda.dto.CuotaResponse;
import com.soldaMaster.solda.dto.DetalleVentaRequest;
import com.soldaMaster.solda.dto.PagoRequest;
import com.soldaMaster.solda.dto.VentaCreditoResponse;
import com.soldaMaster.solda.dto.VentaRequest;
import com.soldaMaster.solda.dto.VentaResponse;
import com.soldaMaster.solda.entity.Compras;
import com.soldaMaster.solda.entity.EstadosSistema;
import com.soldaMaster.solda.entity.Ventas;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.mapper.VentaMapper;
import com.soldaMaster.solda.repository.VentaRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class VentaService {
    private final VentaRepository repository;
    private final DetallesVentaService dVentaService;
    private final PagoService pService;
    private final VentaMapper mapper;
    private final CuotaService cService;
    
    @Transactional
    public VentaResponse crearVenta(VentaRequest request){
        verificarMontoVenta(request);

        String serie = generarSerieCorrelativa(request.getTipoComprobante());

        request.setSerieCorrelativa(serie);
        
        Ventas ingresado = mapper.toEntity(request);

        BigDecimal totalPagos = pService.sumarPago(request.getPagosList());

        if (totalPagos.compareTo(request.getTotal()) >= 0) {
            EstadosSistema estadoContado = new EstadosSistema();
            estadoContado.setIdEstado(8); 
            ingresado.setIdEstadoVenta(estadoContado);
        } else {
            EstadosSistema estadoCredito = new EstadosSistema();
            estadoCredito.setIdEstado(6); 
            ingresado.setIdEstadoVenta(estadoCredito);
        }

        ingresado = repository.save(ingresado);

        Integer idVenta = ingresado.getIdVenta();

        String referenciaV = ingresado.getTipoComprobante() + ": " + ingresado.getSerieCorrelativa();
        
        List<DetalleVentaRequest> detalles = request.getDetallesVentasList();

        for(DetalleVentaRequest detalle : detalles){

            detalle.setIdVenta(idVenta);
            dVentaService.crearDetalleVenta(detalle, referenciaV);

        }

        List<PagoRequest> pagos = request.getPagosList();

        if(pagos != null){
            for(PagoRequest pago: pagos){
                pago.setIdVenta(idVenta);
                pService.crearPago(pago);
            }
        }
        

        List<CuotaRequest> cuotas = request.getCuotasList();

        if(cuotas != null){
            for(CuotaRequest cuota: cuotas){
                cuota.setIdVenta(idVenta);
                cService.crearCuota(cuota);
            }
        }
        
        
        return mapper.toResponse(ingresado); 

    }

    public void verificarMontoVenta(VentaRequest request){
        BigDecimal sumaDetalles = BigDecimal.ZERO;

        BigDecimal descuentoGlobal = request.getDescuentoGlobal();
            if (descuentoGlobal == null) {
                descuentoGlobal = BigDecimal.ZERO;
            }

        List<DetalleVentaRequest> detalles = request.getDetallesVentasList();

        for(DetalleVentaRequest detalle : detalles){
            
            Boolean detalleVerificacion = dVentaService.verificarMontoDetalle(detalle);

            if (!detalleVerificacion) {
                throw new IllegalArgumentException(
                    "Inconsistencia de precios en el producto ID: " + detalle.getIdProducto()
                );
            }

            sumaDetalles = sumaDetalles.add(detalle.getSubTotal());
        }

        BigDecimal subtotalConDescuento = sumaDetalles.subtract(descuentoGlobal);

        if (subtotalConDescuento.compareTo(BigDecimal.ZERO) < 0) {
            subtotalConDescuento = BigDecimal.ZERO; 
        }

        BigDecimal porcentajeIgv = new BigDecimal("0.18");
        BigDecimal igv = subtotalConDescuento.multiply(porcentajeIgv);

        BigDecimal totalCalculado = subtotalConDescuento.add(igv);

        BigDecimal diferenciaTotal = totalCalculado.subtract(request.getTotal()).abs();

        if (diferenciaTotal.compareTo(new BigDecimal("0.05")) > 0) {
            throw new IllegalArgumentException(
                "Descuadre: El sistema calculó un Total de " + totalCalculado + " (incluyendo 18% IGV), pero el frontend envió " + request.getTotal()
            );
        }

        BigDecimal totalPagos = pService.sumarPago(request.getPagosList());
        BigDecimal totalCuotas = cService.sumarCuota(request.getCuotasList());

        BigDecimal totalCubierto = totalPagos.add(totalCuotas);

        BigDecimal diferenciaPagos = totalCalculado.subtract(totalCubierto).abs();

        if (diferenciaPagos.compareTo(new BigDecimal("0.05")) > 0) {
            throw new IllegalArgumentException(
                "Descuadre en los pagos: El total a pagar es " + totalCalculado + 
                ", pero los pagos y cuotas suman " + totalCubierto
            );
        }

    }

    public String generarSerieCorrelativa(String tipoComprobante){
        String prefijo = tipoComprobante.equalsIgnoreCase("Factura")? "F001-" : "B001-";

        Ventas ultimaVenta = repository.findTopByTipoComprobanteOrderByIdVentaDesc(tipoComprobante);

        if(ultimaVenta == null || ultimaVenta.getSerieCorrelativa() == null){
            return prefijo + "000001";
        }

        String[] partes = ultimaVenta.getSerieCorrelativa().split("-");

        int ultimoNumero = Integer.parseInt(partes[1]);

        return prefijo + String.format("%06d", ultimoNumero + 1);
    }

    public List<VentaCreditoResponse> ventasCredito(){
        List<Ventas> ventas = repository.findAll();
        List<VentaCreditoResponse> listaVentas = new ArrayList<>();

        List<VentaResponse> todasLasVentas = mapper.toResponseList(ventas);
        for(VentaResponse venta : todasLasVentas){
            List<CuotaResponse> listaCuotas =cService.cuotasDVenta(venta.getIdVenta());
            
            if (listaCuotas == null || listaCuotas.isEmpty()) {
                continue; 
            }

            BigDecimal montoPagado = BigDecimal.ZERO;
            BigDecimal montoTotalDeuda = BigDecimal.ZERO;

            for (CuotaResponse cuota : listaCuotas) {
                montoTotalDeuda = montoTotalDeuda.add(cuota.getMonto());

                if (cuota.getMontoPagado() != null) {
                    montoPagado = montoPagado.add(cuota.getMontoPagado());
                }

            }

            BigDecimal montoPendiente = montoTotalDeuda.subtract(montoPagado);

            String estadoCalculado;
            if (montoPendiente.compareTo(BigDecimal.ZERO) <= 0) {
                estadoCalculado = "Cancelada";
            } else if (montoPagado.compareTo(BigDecimal.ZERO) > 0) {
                estadoCalculado = "Parcial";
            } else {
                estadoCalculado = "Pendiente";
            }

            VentaCreditoResponse ventaCredito = new VentaCreditoResponse();

            ventaCredito.setEstado(estadoCalculado);
            ventaCredito.setCliente(venta.getIdCliente());
            ventaCredito.setIdVenta(venta.getIdVenta());
            ventaCredito.setSerieCorrelativa(venta.getSerieCorrelativa());
            ventaCredito.setTotal(montoTotalDeuda);
            ventaCredito.setTotalPendiente(montoPendiente);
            
            listaVentas.add(ventaCredito);
        }

        return listaVentas;
    }

    public List<VentaResponse> mostrarVentas(){
        return mapper.toResponseList(repository.findAll());
    }

    public VentaResponse obtenerVentaConDetalle(Integer idVenta){
        Ventas encontrada = repository.findById(idVenta)
            .orElseThrow(()-> new RecursoNoEncontradoException(idVenta + " Venta no encontrada"));
        
        VentaResponse venta = mapper.toResponse(encontrada);
        venta.setDetallesVentasList(dVentaService.obtenerDetalleVenta(idVenta));
        return venta;
    }

    public Page<VentaResponse> listarVentaCliente(Integer idCliente, int page, int size){
         if (page < 0) page = 0;
        Page<Ventas> paginaVenta = repository.findByIdCliente_IdEntidadOrderByFechaEmisionDesc(
                idCliente, 
                PageRequest.of(page, size)
        );

        return paginaVenta.map(mapper::toResponse);
    }

}
