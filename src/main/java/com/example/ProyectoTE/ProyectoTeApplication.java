package com.example.ProyectoTE;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.repositories.EstudianteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ProyectoTeApplication implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(ProyectoTeApplication.class, args);
	}
	 @Autowired
	 private EstudianteRepository estudianteRepository;
	@Override
	public void run(String... args) throws Exception {
		EstudianteEntity estudiante1 = new EstudianteEntity(1,"20.553.677-9","Navarro Calderon","Alexandra","2001-05-30","Subvencionado","Colegio Alicante",4);
		estudianteRepository.save(estudiante1);

	}

}
