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

    //Funcion que obtiene a todos los estudiantes que se encuentran en la base de datos
    public ArrayList<EstudianteEntity>obtenerEstudiantes(){
        return (ArrayList<EstudianteEntity>) estudianteRepository.findAll();
    }
    //Funcion que guarda a estudiante
    public void guardarEstudiante(EstudianteEntity estudiante) {
        estudianteRepository.save(estudiante);
    }

    //Funcion para buscar un estudiante mediante su rut
    public EstudianteEntity buscarEstudiantePorRut(String rut) {
        return estudianteRepository.findByRut(rut);
    }


    //Funcion que elimina un estudiante de la base de datos
    public void eliminarEstudiantePorId(Long id){
        estudianteRepository.deleteById(id);
    }

}
