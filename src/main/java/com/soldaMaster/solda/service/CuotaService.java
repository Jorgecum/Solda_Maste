package com.soldaMaster.solda.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.CuotaRequest;
import com.soldaMaster.solda.dto.CuotaResponse;
import com.soldaMaster.solda.dto.DetallePagoResponse;
import com.soldaMaster.solda.entity.Cuotas;
import com.soldaMaster.solda.entity.DetallePagos;
import com.soldaMaster.solda.entity.EstadosSistema;
import com.soldaMaster.solda.entity.Ventas;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.mapper.CuotaMapper;
import com.soldaMaster.solda.repository.CuotaRepository;
import com.soldaMaster.solda.repository.DetallePagoRespository;
import com.soldaMaster.solda.repository.EstadoRepository;
import com.soldaMaster.solda.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CuotaService {
    private final CuotaRepository repository;
    private final VentaRepository ventaRepository;
    private final CuotaMapper mapper;
    private final EstadoRepository estadoRepository;
    private final DetallePagoRespository detallePagoRepo;

    public CuotaResponse crearCuota(CuotaRequest request){
        Cuotas ingresar = mapper.toEntity(request);
        ingresar = repository.save(ingresar);

        return mapper.toResponse(ingresar);
    }

    public BigDecimal sumarCuota(List<CuotaRequest> listaCuotas){
        if(listaCuotas ==  null || listaCuotas.isEmpty()){
            return BigDecimal.ZERO;
        }

        BigDecimal sumaCuota = BigDecimal.ZERO;
        
        for(CuotaRequest cuota : listaCuotas){
            sumaCuota = sumaCuota.add(cuota.getMonto());
        }

        return sumaCuota;

    }

    public List<CuotaResponse> cuotasDVenta(Integer idVenta){
        List<Cuotas> listaCuotasVenta = repository.findByIdVenta_IdVentaOrderByNumeroCuotaAsc(idVenta);
        List<CuotaResponse> listaRespuestas = mapper.toResponseList(listaCuotasVenta);

        for (CuotaResponse cuota : listaRespuestas) {
            
            List<DetallePagos> pagos = detallePagoRepo.findByIdCuota_IdCuota(cuota.getIdCuota());
            
            BigDecimal pagado = BigDecimal.ZERO;
            if (pagos != null) {
                for (DetallePagos pago : pagos) {
                    pagado = pagado.add(pago.getMonto());
                }
            }
            
            cuota.setMontoPagado(pagado);
            cuota.setMontoPendiente(cuota.getMonto().subtract(pagado));
        }

        return listaRespuestas;
    }
    

    public void cuotaPagada(Integer idCuota){
        Cuotas encontrada = repository.findById(idCuota)
            .orElseThrow(()-> new RecursoNoEncontradoException(idCuota + " Cuota no encontrada"));
        
        EstadosSistema estadoCancelado = new EstadosSistema();
        estadoCancelado.setIdEstado(10);

        encontrada.setIdEstadoCuota(estadoCancelado);

        encontrada = repository.save(encontrada);

    }

    public void actualizarEstadoventa(Integer idVenta) {
        // Obtenemos las cuotas de la venta
        List<CuotaResponse> cuotas = cuotasDVenta(idVenta);

        boolean todasCanceladas = true;

        if (cuotas == null || cuotas.isEmpty()) {
            todasCanceladas = false;
        }else{
            for (CuotaResponse cuota : cuotas) {

                if (cuota.getIdEstadoCuota().getIdEstado() != 10) {
                    todasCanceladas = false;
                    break; 
                }
            }
        }

        if (todasCanceladas && !cuotas.isEmpty()) {
            Ventas venta = ventaRepository.findById(idVenta)
                .orElseThrow(() -> new RuntimeException(idVenta + " venta no encontrada"));
        
            EstadosSistema estadoCancelado = estadoRepository.getReferenceById(8);
            venta.setIdEstadoVenta(estadoCancelado);
            ventaRepository.save(venta);
        }
    }

    public List<CuotaResponse> mostrarCuotas(){
        List<Cuotas> cuotasPagar = repository.findCuotasPorCobrar();
        List<CuotaResponse> listaCuotas = new ArrayList<>();


        for(Cuotas cuota : cuotasPagar){
            BigDecimal montoPagado = BigDecimal.ZERO;
            BigDecimal montoPendiente = cuota.getMonto();
            List<DetallePagos> listaDPago = detallePagoRepo.findByIdCuota_IdCuota(cuota.getIdCuota());            
            
            if(listaDPago != null){
                for(DetallePagos pagoCuota : listaDPago){
                    montoPagado = montoPagado.add(pagoCuota.getMonto());
                }
            }
            
            montoPendiente = montoPendiente.subtract(montoPagado);

            CuotaResponse agregar = mapper.toResponse(cuota);

            agregar.setMontoPagado(montoPagado);
            agregar.setMontoPendiente(montoPendiente);

            listaCuotas.add(agregar);
        }

        return listaCuotas;
    }
}
