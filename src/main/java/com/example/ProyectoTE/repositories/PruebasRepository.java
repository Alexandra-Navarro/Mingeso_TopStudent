package com.example.ProyectoTE.repositories;

import com.example.ProyectoTE.entities.PruebasEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PruebasRepository extends JpaRepository<PruebasEntity, Long> {

}
