package com.example.ProyectoTE.services;

import com.example.ProyectoTE.entities.PagoEntity;
import com.example.ProyectoTE.entities.EstudianteEntity;



import com.example.ProyectoTE.repositories.EstudianteRepository;

import com.example.ProyectoTE.repositories.PagoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Objects;

@Service
public class PagoService {

    @Autowired
    private EstudianteRepository estudianteRepository;
    @Autowired
    private  PagoRepository pagoRepository;




    public double calcularDescuento (EstudianteEntity estudiante){
        double arancel= 1500000;
        if (Objects.equals(estudiante.getTipoColegioProcedencia(), "Municipal")){
            arancel = arancel - (arancel * 0.2);
        }
        if (Objects.equals(estudiante.getTipoColegioProcedencia(), "Subvencionado")){
            arancel = arancel - (arancel * 0.1);
        }
        if (Objects.equals(estudiante.getTipoColegioProcedencia(), "Privado")){
            arancel = arancel - (arancel * 0.0);
        }
        return arancel;
    }

    public double calcularDescuentoEgreso (EstudianteEntity estudiante){
        double arancel = calcularDescuento(estudiante);
        if (estudiante.getAnioEgresoColegio() < 1){
            arancel = arancel - (arancel * 0.15);
        }
        if (estudiante.getAnioEgresoColegio() >= 1 && estudiante.getAnioEgresoColegio() <=2 ){
            arancel = arancel - (arancel * 0.08);
        }
        if (estudiante.getAnioEgresoColegio() >= 3 && estudiante.getAnioEgresoColegio() <=4 ){
            arancel = arancel - (arancel * 0.04);
        }
        if (estudiante.getAnioEgresoColegio() >= 5){
            arancel = arancel - (arancel * 0.0);
        }
        return arancel;
    }

    public double calcularCuotas(EstudianteEntity estudiante){
        double arancel = calcularDescuentoEgreso(estudiante);
        if (Objects.equals(estudiante.getFormaPago(), "Contado") && estudiante.getCantidadCuotasE()==1){
            arancel = arancel - (arancel * 0.5);
            return arancel;
        }
        if (Objects.equals(estudiante.getTipoColegioProcedencia(), "Municipal") && estudiante.getCantidadCuotasE()<=10){
            arancel = arancel/(estudiante.getCantidadCuotasE());
        }
        if (Objects.equals(estudiante.getTipoColegioProcedencia(), "Subvencionado") && estudiante.getCantidadCuotasE()<=7){
            arancel = arancel/(estudiante.getCantidadCuotasE());
        }
        if (Objects.equals(estudiante.getTipoColegioProcedencia(), "Privado") && estudiante.getCantidadCuotasE()<=4){
            arancel = arancel/(estudiante.getCantidadCuotasE());
        }

        return arancel;
    }

    public double[] listarCuotas(EstudianteEntity estudiante) {
        double cuota = calcularCuotas(estudiante);
        double[] listaCuotas = new double[estudiante.getCantidadCuotasE()];

        for(int i=0 ; i < estudiante.getCantidadCuotasE(); i++){
            listaCuotas[i] = cuota;
        }
        return listaCuotas;

    }

    public String[] listarEstado(EstudianteEntity estudiante) {
        String[] listaEstados = new String[estudiante.getCantidadCuotasE()];
        for(int i=0 ; i < estudiante.getCantidadCuotasE(); i++){
            listaEstados[i]= "Pendiente";
        }
        return listaEstados;
    }
    


}





