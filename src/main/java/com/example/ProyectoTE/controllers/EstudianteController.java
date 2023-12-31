package com.example.ProyectoTE.controllers;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.services.EstudianteService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping
public class EstudianteController {
    @Autowired
    private EstudianteService estudianteService;

    //Listar estudiantes de la base de datos
    @GetMapping({"/registro/listar","/"})
    public String listar(@NotNull Model model){
        model.addAttribute("estudiante",estudianteService.obtenerEstudiantes());
        return "index"; // nos retorna a los estudiantes
    }

    //Formulario de registro de los alumnos
    @GetMapping("/registro")
    public String mostrarFormularioDeRegistros(@NotNull Model modelo) {
        // Guardar el estudiante en la base de datos
        EstudianteEntity estudiante = new EstudianteEntity();
        modelo.addAttribute("estudiante",estudiante);
        return "registroEstudiante";
    }

    //Se guardan los estudiantes que se ingresan a la base de datos
    @PostMapping("/registro/listar")
    public String guardarEstudiante(@ModelAttribute("estudiante") EstudianteEntity estudiante){
        estudianteService.guardarEstudiante(estudiante);
        return "redirect:/registro/listar";
    }

    //Opción para eliminar un estudiante
    @GetMapping("/registro/listar/{id}")
    public String eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiantePorId(id);
        return "redirect:/registro/listar";
    }
}



