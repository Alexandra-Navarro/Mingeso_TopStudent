package com.example.ProyectoTE.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "puntajes")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class ResultadosEntity  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Integer id;
    private String rutAlumno;
    private String fechaExamen;
    private Integer puntajeObtenido;

//    @ManyToOne
//    private EstudianteEntity estudiante;

}

