package com.example.ProyectoTE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDate;


@Entity
@Table(name = "pago")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class PagoEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private int id;
    private String matricula;
    private Double valorCuota;
    private Double valorCuotaFinal;
    private Integer cantidadCuotasP;
    private LocalDate fechaLimitePago;
    private LocalDate fechaPagoCuota;
    private Integer mesesAtraso;
    private String estadoCuota;

    @ManyToOne
    private EstudianteEntity estudiante;


}
