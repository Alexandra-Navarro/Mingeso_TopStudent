package com.example.ProyectoTE;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.repositories.EstudianteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import static org.assertj.core.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProyectoTeApplicationTests{

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private EstudianteRepository estudianteRepository;

    @Test
    public void whenFindByName_thenReturnEmployee(){
        EstudianteEntity estudiante1 = new EstudianteEntity();
        estudiante1.setRut("20.336.256-3");
        estudiante1.setNombre("Juan");
        estudiante1.setApellidos("Valenzuela");
        estudiante1.setFechaNacimiento("2000-06-20");
        estudiante1.setTipoColegioProcedencia("Municipal");
        estudiante1.setNombreColegio("Colegio la estrella");
        estudiante1.setAnioEgresoColegio(3);

        entityManager.persistAndFlush(estudiante1);
        EstudianteEntity estudiante2 = estudianteRepository.findByRut(estudiante1.getRut());
        assertThat(estudiante2.getRut())
                .isEqualTo(estudiante1.getRut());
    }

}
