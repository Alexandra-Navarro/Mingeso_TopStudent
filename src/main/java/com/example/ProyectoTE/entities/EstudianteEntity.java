package com.example.ProyectoTE.entities;

import jakarta.persistence.*;
import jakarta.persistence.criteria.CriteriaBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class EstudianteEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(unique = true,nullable = false)
    private Long id;
    private String rut;
    private String apellidos;
    private String nombre;
    private String fechaNacimiento;
    private String tipoColegioProcedencia;
    private String nombreColegio;
    private Integer anioEgresoColegio;
    private String formaPago;
    private Integer cantidadCuotasE;
    private String estadoMatricula;
    private List<Double> cuotas;
    private List<String> estadosCuotas;

}
