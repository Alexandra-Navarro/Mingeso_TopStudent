package com.example.ProyectoTE.controllers;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.services.EstudianteService;
import com.example.ProyectoTE.services.PagoService;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.commons.lang3.ArrayUtils;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;


@Controller
@RequestMapping
public class PagoController {

    @Autowired
    private PagoService pagoService;

    @Autowired
    private EstudianteService estudianteService;

    @GetMapping("/registro/listar/cuotas/{rut}")
    public String verCuotasEstudiante(@PathVariable String rut, Model model) {
        EstudianteEntity estudiante = estudianteService.buscarEstudiantePorRut(rut);

        if (estudiante != null) {
            double[] cuotas = pagoService.listarCuotas(estudiante);
            model.addAttribute("estudiante", estudiante);
            model.addAttribute("cuotas", cuotas);
            return "Cuotas";
        }
        return rut;
    }
}
//    @GetMapping("/registro/listar/cuotas")
//    public String listarCuotasEstudiantes(@NotNull Model model) {
//        List<EstudianteEntity> listaEstudiantes = estudianteService.obtenerEstudiantes();
//        List<EstudianteEntity> estudiantesDTOList = new ArrayList<>();
//
//        for (EstudianteEntity estudiante : listaEstudiantes) {
//            double[] estudianteCuotas = pagoService.listarCuotas(estudiante);
//            EstudianteEntity estudianteDTO = new EstudianteEntity();
//
//            estudianteDTO.setRut(estudiante.getRut());
//            estudianteDTO.setNombre(estudiante.getNombre());
//            estudianteDTO.setApellidos(estudiante.getApellidos());
//            estudianteDTO.setCantidadCuotasE(estudiante.getCantidadCuotasE());
//            estudianteDTO.setCuotas(Arrays.asList(ArrayUtils.toObject(estudianteCuotas)));
//
//            estudiantesDTOList.add(estudianteDTO);
//        }
//
//        model.addAttribute("estudiantesDTOList", estudiantesDTOList);
//        return "Cuotas";
//    }







