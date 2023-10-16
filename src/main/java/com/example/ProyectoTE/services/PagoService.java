package com.example.ProyectoTE.services;

import com.example.ProyectoTE.entities.EstudianteEntity;


import com.example.ProyectoTE.entities.PagoEntity;
import com.example.ProyectoTE.repositories.EstudianteRepository;

import com.example.ProyectoTE.repositories.PagoRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class PagoService {

    @Autowired
    private  PagoRepository pagoRepository;

    //Aqui se calculan los descuentos correspondientes a Tipo de colegio del estudiante
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

    //Se calculan los descuentos según los años de egreso del colegio
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

    // Se calculan los descuentos para los que agan al contado y se verifican los maximos de cuotas por tipo de colegio
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


    public String[] calcularFechasLimitePago(EstudianteEntity estudiante) {
        int cantidadCuotas = estudiante.getCantidadCuotasE();
        String[] fechasLimite = new String[cantidadCuotas];

        // Obtener el mes actual
        LocalDate today = LocalDate.now();
        int currentMonth = today.getMonthValue();

        // Ajustar el mes inicial a marzo del año actual
        int nextMonth = 3;
        int nextYear = today.getYear();

        // Configurar la fecha límite de la primera cuota al día 10 del próximo mes
        for (int i = 0; i < cantidadCuotas; i++) {
            // Establecer la fecha límite de pago al día 10 de cada mes
            LocalDate fechaLimite = LocalDate.of(nextYear, nextMonth, 10);

            // Formatear la fecha en el formato "yyyy-MM-dd"
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            fechasLimite[i] = fechaLimite.format(formatter);

            // Calcular la fecha para el próximo mes
            nextMonth++;
            if (nextMonth > 12) {
                nextMonth = 1;
                nextYear++;
            }
        }

        return fechasLimite;
    }



    public String[] calcularMesesAtraso(EstudianteEntity estudiante, String[] fechasLimite ) {
        int cantidadCuotas = estudiante.getCantidadCuotasE();
        String[] mesesAtraso = new String[cantidadCuotas];
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        for (int i = 0; i < cantidadCuotas; i++) {

            LocalDate fechaLimite = LocalDate.parse(fechasLimite[i], formatter);
            LocalDate fechaPago = LocalDate.now();

            // Calcular la diferencia en meses entre la fecha de pago y la fecha límite
            long meses = ChronoUnit.MONTHS.between(fechaLimite, fechaPago);
            mesesAtraso[i] = String.valueOf(meses);

            // Verificar si el valor es negativo (pago antes de la fecha límite)
            if (meses < 0) {
                mesesAtraso[i] = "0";
            }
        }

        return mesesAtraso;
    }




    public double calcularCuotaFinal(EstudianteEntity estudiante, int mesesAtraso, double cuota) {
        double interes = 0;

        if (mesesAtraso == 1) {
            interes = 0.03;
        } else if (mesesAtraso == 2) {
            interes = 0.06;
        } else if (mesesAtraso == 3) {
            interes = 0.09;
        } else if (mesesAtraso > 3) {
            interes = 0.15;
        }

        return cuota + (cuota * interes);
    }

    public List<PagoEntity> obtenerPagosPorMatricula(String matricula) {
        // Utiliza el repositorio para buscar los pagos por matrícula (RUT)
        return pagoRepository.findByMatricula(matricula);
    }


}





