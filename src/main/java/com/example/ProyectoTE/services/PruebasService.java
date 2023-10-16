package com.example.ProyectoTE.services;


import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.entities.PagoEntity;
import com.example.ProyectoTE.entities.PruebasEntity;
import com.example.ProyectoTE.repositories.EstudianteRepository;
import com.example.ProyectoTE.repositories.PruebasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PruebasService {

    @Autowired
    private PruebasRepository pruebasRepository;

    public ArrayList<PruebasEntity>obtenerPrueba(){
        return (ArrayList<PruebasEntity>) pruebasRepository.findAll();
    }

    public void guardarPruebas(PruebasEntity pruebas) {
        pruebasRepository.save(pruebas);
    }

    public List<PruebasEntity> obtenerPruebasPorFecha(LocalDate fecha) {
        // Recupera todas las pruebas desde el repositorio
        List<PruebasEntity> todasLasPruebas = pruebasRepository.findAll();

        // Filtra las pruebas por la fecha específica
        return todasLasPruebas.stream()
                .filter(prueba -> prueba.getFechaExamen().isEqual(fecha))
                .collect(Collectors.toList());
    }




}