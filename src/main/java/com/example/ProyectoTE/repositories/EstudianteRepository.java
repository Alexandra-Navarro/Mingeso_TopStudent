package com.example.ProyectoTE.repositories;
 import com.example.ProyectoTE.entities.EstudianteEntity;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.jpa.repository.Query;
 import org.springframework.stereotype.Repository;

 import java.util.ArrayList;
 import java.util.List;

@Repository
public interface EstudianteRepository extends JpaRepository <EstudianteEntity, Long> {

 EstudianteEntity findByRut(String rut);
}