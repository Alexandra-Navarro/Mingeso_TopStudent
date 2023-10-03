package com.example.ProyectoTE.controllers;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.services.EstudianteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.ArrayList;

@Controller
@RequestMapping
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;
    @GetMapping({"/registro/listar","/"})
    public String listar(@NotNull Model model){
        model.addAttribute("estudiante",estudianteService.obtenerEstudiantes());
        return "index"; // nos retorna a los estudiantes
    }
    @GetMapping("/registro")
    public String mostrarFormularioDeRegistros(@NotNull Model modelo) {
        // Guardar el estudiante en la base de datos
        EstudianteEntity estudiante = new EstudianteEntity();
        modelo.addAttribute("estudiante",estudiante);
        return "registroEstudiante";
    }

    @PostMapping("/registro/listar")
    public String guardarEstudiante(@ModelAttribute("estudiante") EstudianteEntity estudiante){
        estudianteService.guardarEstudiante(estudiante);
        return "redirect:/registro/listar";
    }
}

