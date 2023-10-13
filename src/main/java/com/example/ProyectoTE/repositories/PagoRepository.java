package com.example.ProyectoTE.repositories;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.entities.PagoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface PagoRepository extends JpaRepository< PagoEntity, Long> {

    List<PagoEntity> findByEstudiante(EstudianteEntity estudiante);
}
