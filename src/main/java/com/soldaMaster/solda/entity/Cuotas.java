package com.soldaMaster.solda.entity;

import jakarta.persistence.CascadeType;
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
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "cuotas")
@Getter
@Setter
@NoArgsConstructor
public class Cuotas {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cuota")
    private Integer idCuota;

    @Column(name = "numero_cuota")
    private Integer numeroCuota;

    @Column(name = "fecha_vencimiento")
    private LocalDate fechaVencimiento;

    @Column(name = "monto")
    private BigDecimal monto;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idCuota", fetch = FetchType.LAZY)
    private List<DetallePagos> detallePagosList;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_estado_cuota", referencedColumnName = "id_estado")
    private EstadosSistema idEstadoCuota;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_venta", referencedColumnName = "id_venta")
    private Ventas idVenta;

}