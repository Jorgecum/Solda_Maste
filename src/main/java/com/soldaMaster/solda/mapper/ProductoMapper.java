package com.soldaMaster.solda.mapper;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.springframework.beans.factory.annotation.Autowired;

import com.soldaMaster.solda.dto.ProductoRequest;
import com.soldaMaster.solda.dto.ProductoResponse;
import com.soldaMaster.solda.entity.Productos;
import com.soldaMaster.solda.exception.RecursoNoEncontradoException;
import com.soldaMaster.solda.repository.ProductoRepository;

@Mapper(componentModel = "spring", 
    uses = {CategoriaMapper.class, EstadoMapper.class, MedidaMapper.class})
public abstract class ProductoMapper {
    @Autowired
    protected ProductoRepository repository;

    @Mapping(target = "idProducto", ignore = true)
    @Mapping(target = "movimientosInventarioList", ignore = true)
    @Mapping(target = "detalleVentasList", ignore = true)
    @Mapping(target = "detalleOrdenList", ignore = true)
    @Mapping(target = "lotesList", ignore = true)
    @Mapping(target = "detalleNotaCreditoList", ignore = true)
    @Mapping(target = "productosRelacionadosList", ignore = true)
    @Mapping(target = "productosRelacionadosList1", ignore = true)
    @Mapping(target = "detalleComprasList", ignore = true)
    public abstract Productos toEntity (ProductoRequest request);

    public abstract ProductoResponse toResponse(Productos producto);
    

    @InheritConfiguration(name = "toEntity")
    public abstract void actualizarProducto(@MappingTarget Productos actualizar, ProductoRequest datos);

    public Productos map(Integer id){
        return repository.findById(id)
            .orElseThrow(()-> new RecursoNoEncontradoException(id+ " Producto no encotrado")); 
    }

}
