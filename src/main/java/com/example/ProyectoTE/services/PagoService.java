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
    private EstudianteRepository estudianteRepository;
    @Autowired
    private  PagoRepository pagoRepository;

    public Optional<PagoEntity> obtenerCuotasId(long id){
        return pagoRepository.findById(id);
    }


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

            // Formatear la fecha en un formato legible
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
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
    public String[] calcularMesesAtraso(EstudianteEntity estudiante, String[] fechasLimite) {
        int cantidadCuotas = estudiante.getCantidadCuotasE();
        String[] mesesAtraso = new String[cantidadCuotas];

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        for (int i = 0; i < cantidadCuotas; i++) {
            // Parsear la fecha límite de pago y la fecha de pago
            LocalDate fechaLimite = LocalDate.parse(fechasLimite[i], formatter);
            LocalDate fechaPago = LocalDate.now(); // Debes obtener la fecha de pago del pago real

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

    @Transactional
    public void guardarFechaPago(EstudianteEntity estudiante, int cuotaNumber, LocalDate fechaPagoCuota) {
        PagoEntity pago = new PagoEntity();
        pago.setEstudiante(estudiante);
        pago.setFechaPagoCuota(fechaPagoCuota);
        pago.setEstadoCuota("Pagada");

        pagoRepository.save(pago);
    }



    //Funcion para calcular los intereses a las cuotas
    public double calcularIntereses (EstudianteEntity estudiante, PagoEntity pago){
        double cuota = calcularCuotas(estudiante);
        if ( pago.getMesesAtraso() == 0){
            cuota = cuota  + ( cuota * 0);
        }
        if ( pago.getMesesAtraso() == 1){
            cuota = cuota  + ( cuota * 0.03);
        }
        if ( pago.getMesesAtraso() == 2){
            cuota = cuota  + ( cuota * 0.06);
        }
        if ( pago.getMesesAtraso() == 3){
            cuota = cuota  + ( cuota * 0.09);
        }
        if ( pago.getMesesAtraso() > 3){
            cuota = cuota  + ( cuota * 0.15);
        }
        return cuota;
    }


    public List<PagoEntity> obtenerPagosPorEstudiante(EstudianteEntity estudiante) {
        return pagoRepository.findByEstudiante(estudiante);
    }
}





