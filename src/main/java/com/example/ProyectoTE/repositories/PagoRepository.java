package com.example.ProyectoTE.repositories;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.entities.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PagoRepository extends JpaRepository< PagoEntity, Long> {
}
