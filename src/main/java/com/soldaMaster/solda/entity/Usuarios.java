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
@Table(name = "usuarios")
@Getter
@Setter
@NoArgsConstructor
public class Usuarios {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "usuario", length = 50, nullable = false)
    private String usuario;

    @NotNull
    @Size(min = 1, max = 100)
    @Column(name = "password", length = 100, nullable = false)
    private String password;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_entidad", referencedColumnName = "id_entidad")
    private Entidades idEntidad;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "id_estado", referencedColumnName = "id_estado", nullable = false)
    private EstadosSistema idEstado;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_rol", referencedColumnName = "id_rol")
    private Roles idRol;

    @OneToMany(mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<Compras> comprasList;

    @OneToMany(mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<OrdenesCompra> ordenesCompraList;

    @OneToMany(mappedBy = "idUsuario", fetch = FetchType.LAZY)
    private List<Ventas> ventasList;

}