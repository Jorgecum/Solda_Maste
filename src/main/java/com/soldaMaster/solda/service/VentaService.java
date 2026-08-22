package com.soldaMaster.solda.service;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.CuotaRequest;
import com.soldaMaster.solda.dto.DetalleVentaRequest;
import com.soldaMaster.solda.dto.PagoRequest;
import com.soldaMaster.solda.dto.VentaRequest;
import com.soldaMaster.solda.dto.VentaResponse;
import com.soldaMaster.solda.entity.Ventas;
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

        ingresado = repository.save(ingresado);

        Integer idVenta = ingresado.getIdVenta();

        String referenciaV = ingresado.getTipoComprobante() + ": " + ingresado.getSerieCorrelativa();
        
        List<DetalleVentaRequest> detalles = request.getDetalleVentasList();

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

        List<DetalleVentaRequest> detalles = request.getDetalleVentasList();

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
}
