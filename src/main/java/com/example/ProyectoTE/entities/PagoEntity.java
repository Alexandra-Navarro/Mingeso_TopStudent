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
    private Integer arancelTotal;
    private Integer cantidadCuotasP;
    private String fechaPago;
    private String estado;
    private LocalDate fechaVencimiento;

    @ManyToOne
    private EstudianteEntity estudiante;

    public void setValorCuota(double estudianteCuota) {
    }

    public void setEstudiante(EstudianteEntity estudiante) {
    }
}
