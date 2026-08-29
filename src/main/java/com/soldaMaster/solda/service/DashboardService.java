package com.soldaMaster.solda.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.soldaMaster.solda.dto.ActividadDTO;
import com.soldaMaster.solda.dto.ProductoResponse;
import com.soldaMaster.solda.entity.MovimientosInventario;
import com.soldaMaster.solda.entity.Ventas;
import com.soldaMaster.solda.repository.MovimientoInventarioRepository;
import com.soldaMaster.solda.repository.VentaRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class DashboardService {
    private final VentaRepository vRepository;
    private final MovimientoInventarioRepository mInventarioRepository;
    private final ProductoService pService;

    public List<ActividadDTO> obtenerActividadReciente(){
        List<ActividadDTO> timeLine = new ArrayList<>();

        for(Ventas v : vRepository.findTop20ByOrderByFechaEmisionDesc()){
            if (v.getFechaEmision() == null) continue; 
            ActividadDTO actividad = new ActividadDTO();
            actividad.setTime(v.getFechaEmision().toString());
            actividad.setLabel(v.getIdEstadoVenta().getIdEstado() == 10 ? "Venta Anulada" : "Venta Registrada");
            actividad.setDesc("Comprobante: "+ (v.getSerieCorrelativa() != null ? v.getSerieCorrelativa() : "S/N"));
            actividad.setFechaFiltro(v.getFechaEmision());
            timeLine.add(actividad);
        }

        for(MovimientosInventario m : mInventarioRepository.findTop20ByOrderByFechaDesc()){
            if (m.getFecha() == null) continue;

            ProductoResponse producto = pService.obtenerProducto(m.getIdProducto().getIdProducto());

            ActividadDTO actividad = new ActividadDTO();
            actividad.setTime(m.getFecha().toString());
            actividad.setLabel(m.getIdTipoMovimiento().getIdTipoMovimiento() == 1? "Kardex: ENTRADA" : "Kardex: SALIDA");
            actividad.setDesc(m.getReferencia() + " (" + m.getCantidad() +" " + producto.getIdUnidadMedida().getNombre() +") ");
            actividad.setFechaFiltro(m.getFecha());
            timeLine.add(actividad);
        }

        timeLine.sort((a, b) -> {
            if (a.getFechaFiltro() == null || b.getFechaFiltro() == null) return 0;
            return b.getFechaFiltro().compareTo(a.getFechaFiltro());
        });

        return timeLine.stream().limit(20).collect(Collectors.toList());
    }

}
