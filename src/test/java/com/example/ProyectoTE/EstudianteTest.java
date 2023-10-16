package com.example.ProyectoTE;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.services.EstudianteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
public class EstudianteTest {



    @Autowired
    private EstudianteService estudianteService;

    @Test
    public void testGuardarEstudiante() {
        EstudianteEntity estudiante = new EstudianteEntity();
        estudiante.setRut("20.336.256-3");
        estudiante.setNombre("Juan");
        estudiante.setApellidos("Valenzuela");
        estudiante.setFechaNacimiento("2000-06-20");
        estudiante.setTipoColegioProcedencia("Municipal");
        estudiante.setNombreColegio("Colegio la estrella");
        estudiante.setAnioEgresoColegio(3);

        // Guardar el estudiante utilizando el servicio
        estudianteService.guardarEstudiante(estudiante);

    }

    @Test
    public void testEliminarEstudiantePorId() {
        // Crear un nuevo estudiante y guardarlo en la base de datos
        EstudianteEntity estudiante = new EstudianteEntity();
        estudiante.setRut("20.336.256-3");
        estudiante.setNombre("Juan");
        estudiante.setApellidos("Valenzuela");
        estudiante.setFechaNacimiento("2000-06-20");
        estudiante.setTipoColegioProcedencia("Municipal");
        estudiante.setNombreColegio("Colegio la estrella");
        estudiante.setAnioEgresoColegio(3);

        // Guardar el estudiante en la base de datos
        estudianteService.guardarEstudiante(estudiante);

        // Obtener el ID del estudiante recién guardado
        Long estudianteId = estudiante.getId();

        // Eliminar el estudiante utilizando el servicio
        estudianteService.eliminarEstudiantePorId(estudianteId);


    }

    @Test
    public void testBuscarEstudiantePorRut() {
        // Crear un nuevo estudiante y guardarlo en la base de datos
        EstudianteEntity estudiante = new EstudianteEntity();
        estudiante.setRut("20.336.256-9");
        estudiante.setNombre("Juan");
        estudiante.setApellidos("Valenzuela");
        estudiante.setFechaNacimiento("2000-06-20");
        estudiante.setTipoColegioProcedencia("Municipal");
        estudiante.setNombreColegio("Colegio la estrella");
        estudiante.setAnioEgresoColegio(3);

        // Guardar el estudiante en la base de datos
        estudianteService.guardarEstudiante(estudiante);

        // Llamar a la función para buscar el estudiante por su rut
        String rutABuscar = "20.336.256-9";
        EstudianteEntity estudianteEncontrado = estudianteService.buscarEstudiantePorRut(rutABuscar);

        // Verificar que el estudiante haya sido encontrado correctamente
        assertNotNull(estudianteEncontrado);
        assertEquals(rutABuscar, estudianteEncontrado.getRut());

        // Obtener el ID del estudiante recién guardado
        Long estudianteId = estudiante.getId();

        // Eliminar el estudiante utilizando el servicio
        estudianteService.eliminarEstudiantePorId(estudianteId);


    }

    @Test
    public void testObtenerEstudiantes() {
        // Crear varios estudiantes y guardarlos en la base de datos
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut("21.596.256-3");
        estudiante1.setNombre("Sofia");
        estudiante1.setApellidos("Valenzuela");
        estudiante1.setFechaNacimiento("2000-07-20");
        estudiante1.setTipoColegioProcedencia("Municipal");
        estudiante1.setNombreColegio("Colegio la estrella");
        estudiante1.setAnioEgresoColegio(3);

        EstudianteEntity estudiante2 = new EstudianteEntity();
        estudiante2.setRut("15.123.456-7");
        estudiante2.setNombre("Ana");
        estudiante2.setApellidos("Perez");
        estudiante2.setFechaNacimiento("1999-03-15");
        estudiante2.setTipoColegioProcedencia("Particular");
        estudiante2.setNombreColegio("Colegio San Juan");
        estudiante2.setAnioEgresoColegio(4);

        // Guardar los estudiantes en la base de datos
        estudianteService.guardarEstudiante(estudiante1);
        estudianteService.guardarEstudiante(estudiante2);

        // Llamar a la función para obtener la lista de estudiantes
        ArrayList<EstudianteEntity> estudiantes = estudianteService.obtenerEstudiantes();

        // Verificar que la lista no sea nula y que contenga al menos dos estudiantes
        assertNotNull(estudiantes);
        assertTrue(estudiantes.size() >= 2);
    }

}


