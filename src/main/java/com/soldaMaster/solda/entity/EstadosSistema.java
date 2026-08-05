package com.soldaMaster.solda.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "estados_sistema")
@Getter
@Setter
@NoArgsConstructor
public class EstadosSistema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_estado")
    private Integer idEstado;

    @NotNull
    @Size(min = 1, max = 20)
    @Column(name = "tipo_codigo", nullable = false, length = 20)
    private String tipoCodigo;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "nombre", nullable = false, length = 50)
    private String nombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Medidas> medidasList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;

    @OneToMany(mappedBy = "idEstadoCuota", fetch = FetchType.LAZY)
    private List<Cuotas> cuotasList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Productos> productosList;

    @OneToMany(mappedBy = "idEstadoOrden", fetch = FetchType.LAZY)
    private List<OrdenesCompra> ordenesCompraList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Categorias> categoriasList;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idEstado", fetch = FetchType.LAZY)
    private List<Entidades> entidadesList;

    @OneToMany(mappedBy = "idEstadoVenta", fetch = FetchType.LAZY)
    private List<Ventas> ventasList;

}