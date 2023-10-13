package com.example.ProyectoTE.services;


import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class EstudianteService {
    @Autowired
    private EstudianteRepository estudianteRepository;

    public ArrayList<EstudianteEntity>obtenerEstudiantes(){
        return (ArrayList<EstudianteEntity>) estudianteRepository.findAll();
    }

    public void guardarEstudiante(EstudianteEntity estudiante) {
        estudianteRepository.save(estudiante);
    }

    public EstudianteEntity buscarEstudiantePorRut(String rut) {
        return estudianteRepository.findByRut(rut);
    }

    public EstudianteEntity eliminarEstudiantePorId(Long id){
        estudianteRepository.deleteById(id);
        return null;
    }
    public EstudianteEntity obtenerEstudiantePorId(Long id){
        return  estudianteRepository.findById(id).get();
    }

}
