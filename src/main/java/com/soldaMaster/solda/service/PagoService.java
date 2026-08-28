package com.soldaMaster.solda.service;
import java.math.BigDecimal;
import java.util.List;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.CuotaResponse;
import com.soldaMaster.solda.dto.DetallePagoRequest;
import com.soldaMaster.solda.dto.DetallePagoResponse;
import com.soldaMaster.solda.dto.PagoRequest;
import com.soldaMaster.solda.dto.PagoResponse;
import com.soldaMaster.solda.entity.Pagos;
import com.soldaMaster.solda.mapper.PagoMapper;
import com.soldaMaster.solda.repository.PagoRepository;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class PagoService {
    private final PagoRepository repository;
    private final PagoMapper mapper;
    private final DetallePagoService dPagoService;
    private final CuotaService cService;

    public PagoResponse crearPago(PagoRequest request){
        Pagos ingresar = mapper.toEntity(request);

        ingresar = repository.save(ingresar);

        return mapper.toResponse(ingresar);
    }

    public BigDecimal sumarPago(List<PagoRequest> listaPagos){
        if(listaPagos == null || listaPagos.isEmpty()){
            return BigDecimal.ZERO;
        }

        BigDecimal totalPagos = BigDecimal.ZERO;

        for(PagoRequest pago : listaPagos){
            totalPagos = totalPagos.add(pago.getMontoTotal());
        }

        return totalPagos;
    }

    @Transactional
    public PagoResponse distribuirPago(PagoRequest request){
        Pagos realizado = mapper.toEntity(request);

        realizado = repository.save(realizado);
        
        BigDecimal montoDebe = BigDecimal.ZERO;
        BigDecimal montoAbonado = request.getMontoTotal();
        Integer idVenta = request.getIdVenta();

        List<CuotaResponse> cuotasVenta = cService.cuotasDVenta(idVenta);

        for(CuotaResponse cuota : cuotasVenta){
            
            if(cuota.getIdEstadoCuota().getIdEstado() == 10){
                continue;
            }

            if(montoAbonado.compareTo(BigDecimal.ZERO) == 0){
                break;
            }

            DetallePagoRequest pagoCuota = new DetallePagoRequest();
            pagoCuota.setIdPago(realizado.getIdPago());
            pagoCuota.setIdCuota(cuota.getIdCuota());
            
            List<DetallePagoResponse> listaPagos = dPagoService.listaPagoCuota(cuota.getIdCuota());

            BigDecimal montoAbonadoAnterior = BigDecimal.ZERO;

            if(listaPagos != null && !listaPagos.isEmpty() ){

                for(DetallePagoResponse pago : listaPagos){
                    montoAbonadoAnterior = montoAbonadoAnterior.add(pago.getMonto());
                }

                montoDebe = cuota.getMonto();
                montoDebe = montoDebe.subtract(montoAbonadoAnterior);

            }else{

                montoDebe = cuota.getMonto();

            }

            if(montoAbonado.compareTo(montoDebe) >= 0){
                pagoCuota.setMonto(montoDebe);
                
                montoAbonado = montoAbonado.subtract(montoDebe);
                cService.cuotaPagada(cuota.getIdCuota());
                
            }else{
                pagoCuota.setMonto(montoAbonado);
                montoAbonado = BigDecimal.ZERO;
            }

            dPagoService.crearDetallePago(pagoCuota);

            
        }
        cService.actualizarEstadoventa(idVenta);
        return mapper.toResponse(realizado);
    }

    public List<PagoResponse> mostrarPagos(){
        return mapper.toResponseList(repository.findAll());
    }
  
}
