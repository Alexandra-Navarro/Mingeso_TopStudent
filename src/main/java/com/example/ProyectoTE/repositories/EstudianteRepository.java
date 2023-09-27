package com.example.ProyectoTE.repositories;
 import com.example.ProyectoTE.entities.EstudianteEntity;
 import org.springframework.data.jpa.repository.JpaRepository;
 import org.springframework.data.repository.CrudRepository;
 import org.springframework.stereotype.Repository;

@Repository
public interface EstudianteRepository extends JpaRepository <EstudianteEntity, Long> {

}