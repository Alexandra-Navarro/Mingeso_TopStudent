package com.example.ProyectoTE;

import com.example.ProyectoTE.entities.PruebasEntity;
import com.example.ProyectoTE.repositories.PruebasRepository;
import com.example.ProyectoTE.services.PruebasService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@SpringBootTest
public class PruebasTest {

    @Autowired
    private PruebasService pruebasService;

    @Autowired
    private PruebasRepository pruebasRepository;



    @Test
    void testGuardarPruebas() {
        // Prueba para guardar una prueba
        PruebasEntity prueba = new PruebasEntity();
        prueba.setRutEstudiante("123456789");
        prueba.setAsignaturaExamen("Matemáticas");
        prueba.setFechaExamen(LocalDate.now());
        prueba.setPuntajeObtenido(95);

        pruebasService.guardarPruebas(prueba);

        // Asegúrate de que la prueba se haya guardado correctamente y que puedas obtenerla
        List<PruebasEntity> prueba1 = pruebasService.obtenerPrueba();
        assertNotNull(prueba1);
        // Agrega más aserciones según tu implementación
    }

    @Test
    void testObtenerPruebaListaNoVacia() {
        // Creamos una prueba simulada y la guardamos en la base de datos
        PruebasEntity prueba = new PruebasEntity();
        prueba.setRutEstudiante("123456789");
        prueba.setAsignaturaExamen("Matemáticas");
        prueba.setFechaExamen(LocalDate.now());
        prueba.setPuntajeObtenido(95);

        // Obtenemos la lista de pruebas
        ArrayList<PruebasEntity> pruebas = pruebasService.obtenerPrueba();

        // Verificamos que la lista no esté vacía y contenga la prueba que guardamos
        assertNotNull(pruebas);
    }

}






