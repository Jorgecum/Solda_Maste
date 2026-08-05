package com.soldaMaster.solda.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "entidades")
@Getter
@Setter
@NoArgsConstructor
public class Entidades {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_entidad")
    private Integer idEntidad;

    @Size(max = 20)
    @Column(name = "tipo_documento", length = 20)
    private String tipoDocumento;

    @Size(max = 20)
    @Column(name = "numero_documento", length = 20)
    private String numeroDocumento;

    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "nombre_razon_social", nullable = false, length = 150)
    private String nombreRazonSocial;

    @Size(max = 250)
    @Column(name = "direccion", length = 250)
    private String direccion;

    @Size(max = 20)
    @Column(name = "telefono", length = 20)
    private String telefono;

    @Size(max = 100)
    @Column(name = "email", length = 100)
    private String email;

    @OneToMany(mappedBy = "idEntidad", fetch = FetchType.LAZY)
    private List<Usuarios> usuariosList;

    @OneToMany(mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private List<Compras> comprasList;

    @OneToMany(mappedBy = "idProveedor", fetch = FetchType.LAZY)
    private List<OrdenesCompra> ordenesCompraList;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    private EstadosSistema idEstado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_entidad", referencedColumnName = "id_tipo_entidad")
    private TiposEntidad idTipoEntidad;

    @OneToMany(mappedBy = "idCliente", fetch = FetchType.LAZY)
    private List<Ventas> ventasList;

}