package com.example.ProyectoTE.controllers;

import com.example.ProyectoTE.entities.PruebasEntity;
import com.example.ProyectoTE.services.PruebasService;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

@Controller
@RequestMapping
public class PruebasController {
    @Autowired
    private PruebasService pruebasService;

    //Se obtienen las pruebas de la base de datos para listarlas
    @GetMapping("/ingresarPrueba/listarPrueba")
    public String listar(@NotNull Model model){
        model.addAttribute("prueba",pruebasService.obtenerPrueba());
        return "listaPruebas"; // nos retorna a los estudiantes
    }

    //Muestra el formulario de ingreso de puntajes de pruebas
    @GetMapping("/ingresarPrueba")
    public String mostrarFormularioIngresoPruebas(@NotNull Model modelo) {
        PruebasEntity prueba = new PruebasEntity();
        modelo.addAttribute("prueba", prueba);
        return "formularioIngresoPruebas";
    }

    // Se guardan las pruebas ingresadas en la base de datos
    @PostMapping("/ingresarPrueba/listarPrueba")
    public String procesarFormularioIngresoPruebas(@ModelAttribute("pruebasEntity") PruebasEntity pruebasEntity) {
        pruebasService.guardarPruebas(pruebasEntity);
        return "redirect:/ingresarPrueba/listarPrueba";

    }

    //Con esto se genera el archivo de excel para las fechas que se van ingresando por pantalla (administrador)
    @GetMapping("/exportarExcel/{fecha}")
    public void exportPruebasToExcelByFecha(HttpServletResponse response, @PathVariable String fecha) throws IOException {
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setHeader("Content-Disposition", "attachment; filename=pruebas_" + fecha + ".xlsx");

        List<PruebasEntity> pruebas = pruebasService.obtenerPruebasPorFecha(LocalDate.parse(fecha));

        try (XSSFWorkbook workbook = new XSSFWorkbook()) {
            XSSFSheet sheet = workbook.createSheet("Pruebas");

            // Crear encabezados
            String[] headers = {"RUT Estudiante", "Asignatura", "Fecha de Examen", "Puntaje Obtenido"};
            XSSFRow headerRow = sheet.createRow(0);
            for (int i = 0; i < headers.length; i++) {
                XSSFCell cell = headerRow.createCell(i);
                cell.setCellValue(headers[i]);
            }

            // Llenar datos de pruebas para la fecha específica
            int rowNum = 1;
            for (PruebasEntity prueba : pruebas) {
                XSSFRow row = sheet.createRow(rowNum++);
                row.createCell(0).setCellValue(prueba.getRutEstudiante());
                row.createCell(1).setCellValue(prueba.getAsignaturaExamen());
                row.createCell(2).setCellValue(prueba.getFechaExamen().toString());
                row.createCell(3).setCellValue(prueba.getPuntajeObtenido());
            }

            workbook.write(response.getOutputStream());
        }
    }

}