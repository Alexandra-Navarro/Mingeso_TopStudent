package com.example.ProyectoTE.controllers;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.services.EstudianteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;

@Controller
@RequestMapping
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;
    @GetMapping("/listar")
    public String listar(Model model){
        ArrayList<EstudianteEntity>estudiante=estudianteService.obtenerEstudiantes();
        model.addAttribute("estudiante",estudiante);
        return "index";
    }
    @PostMapping("/listar/guardarEstudiante")
    public String guardarEstudiante(EstudianteEntity estudiante) {
        // Guardar el estudiante en la base de datos
        estudianteService.guardarEstudiante(estudiante);
        return "redirect:/listar";
    }
}

