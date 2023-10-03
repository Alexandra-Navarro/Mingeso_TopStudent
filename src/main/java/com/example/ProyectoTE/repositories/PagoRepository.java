package com.example.ProyectoTE.repositories;

import com.example.ProyectoTE.entities.EstudianteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<EstudianteEntity, Long> {
}
