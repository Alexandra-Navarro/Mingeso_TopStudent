package com.example.ProyectoTE.services;

import com.example.ProyectoTE.entities.PagoEntity;
import com.example.ProyectoTE.entities.EstudianteEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PagoService {

    // Método para generar cuotas de pago para un estudiante
    public List<PagoEntity> generarCuotas(EstudianteEntity estudiante) {
        List<PagoEntity> cuotas = new ArrayList<>();

        // Monto de la matrícula y el arancel total
        Integer matricula = 70000;
        Integer arancelTotal = 1500000;

        // Calcular el descuento por tipo de colegio
        Double descuentoTipoColegio = calcularDescuentoTipoColegio(estudiante, null);

        // Calcular el descuento por años desde que egresó del colegio
        Double descuentoAniosEgreso = calcularDescuentoAniosEgreso(estudiante, null);

        // Calcular el descuento total
        Double descuentoTotal = descuentoTipoColegio + descuentoAniosEgreso;

        // Calcular el monto a pagar
        Double montoAPagar = arancelTotal - (arancelTotal * descuentoTotal);

        // Crear cuotas mensuales
        LocalDate fechaPago = LocalDate.now().plusDays(5); // Fecha de inicio de pago
        for (int i = 1; i <= 10; i++) {
            PagoEntity cuota = new PagoEntity();
            cuota.setMatricula(matricula.toString());
            cuota.setFechaPago(fechaPago.toString());
            cuota.setArancelTotal(arancelTotal);

            if (i == 1) {
                // Aplicar descuento adicional para la primera cuota
                cuota.setArancelTotal((int) (arancelTotal - (arancelTotal * 0.5)));
            }

            cuota.setEstado("Pendiente");
            cuota.setEstudiante(estudiante);

            cuotas.add(cuota);

            // Incrementar la fecha de pago al siguiente mes
            fechaPago = fechaPago.plusMonths(1);
        }

        return cuotas;
    }

    // Método para calcular el descuento por tipo de colegio
    private Double calcularDescuentoTipoColegio(EstudianteEntity estudiante, PagoEntity pago) {
        switch (estudiante.getTipoColegioProcedencia()) {
            case "Municipal":
                return pago.getArancelTotal() * 0.2;
            case "Subvencionado":
                return pago.getArancelTotal() * 0.1;
            case "Privado":
                return pago.getArancelTotal() * 0.0;
            default:
                return 0.0;
        }
    }

    // Método para calcular el descuento por años desde que egresó del colegio
    private Double calcularDescuentoAniosEgreso(EstudianteEntity estudiante, PagoEntity pago) {
        if (estudiante.getAnioEgresoColegio() < 1) {
            return pago.getArancelTotal() * 0.15;
        } else if (estudiante.getAnioEgresoColegio() >= 1 && estudiante.getAnioEgresoColegio() <= 2) {
            return pago.getArancelTotal() * 0.08;
        } else if (estudiante.getAnioEgresoColegio() >= 3 && estudiante.getAnioEgresoColegio() <= 4) {
            return pago.getArancelTotal() * 0.04;
        } else {
            return 0.0;
        }
    }
}
