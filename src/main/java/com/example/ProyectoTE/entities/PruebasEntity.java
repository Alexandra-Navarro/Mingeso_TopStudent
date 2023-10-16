package com.example.ProyectoTE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "pruebas")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PruebasEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true, nullable = false)
    private Long id;

    private String rutEstudiante;
    private String asignaturaExamen;
    private LocalDate fechaExamen;
    private int puntajeObtenido;
}