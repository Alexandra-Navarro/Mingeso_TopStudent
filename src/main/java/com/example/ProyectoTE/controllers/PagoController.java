package com.example.ProyectoTE.controllers;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.repositories.EstudianteRepository;
import com.example.ProyectoTE.repositories.PagoRepository;
import com.example.ProyectoTE.services.EstudianteService;
import com.example.ProyectoTE.services.PagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;



@Controller
@RequestMapping
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private EstudianteService estudianteService;

    @Autowired
    private PagoRepository pagoRepository;

    @Autowired
    EstudianteRepository estudianteRepository;


    @GetMapping("/registro/listar/cuotas/{rut}")
    public String verCuotasEstudiante(@PathVariable String rut, Model model) {
        EstudianteEntity estudiante = estudianteService.buscarEstudiantePorRut(rut);

        if (estudiante != null) {
            double[] cuotas = pagoService.listarCuotas(estudiante);
            String[] fechasLimite = pagoService.calcularFechasLimitePago(estudiante);
            String[] mesesAtraso = pagoService.calcularMesesAtraso(estudiante, fechasLimite);
            String[] estadosCuotas = pagoService.listarEstado(estudiante);


            model.addAttribute("estudiante", estudiante);
            model.addAttribute("cuotas", cuotas);
            model.addAttribute("fechasLimite", fechasLimite);
            model.addAttribute("mesesAtraso", mesesAtraso);
            model.addAttribute("estadosCuotas", estadosCuotas);

            return "Cuotas";
        }
        return "redirect:/";  // Reemplaza esto con la página adecuada si el estudiante no se encuentra
    }



    @GetMapping("/registro/listar/cuotas/{rut}/{cuotaNumber}")
    public String mostrarFormularioPago(@PathVariable String rut, @PathVariable int cuotaNumber, Model model) {
        EstudianteEntity estudiante = estudianteRepository.findByRut(rut);
        model.addAttribute("estudiante", estudiante);
        model.addAttribute("cuotaNumber", cuotaNumber);
        return "registroCuotas";
    }



}










