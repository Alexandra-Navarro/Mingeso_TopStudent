package com.example.ProyectoTE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


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
    private String fechaPago;
    private Integer arancelTotal;
    private String estado;

    @ManyToOne EstudianteEntity estudiante;


}
