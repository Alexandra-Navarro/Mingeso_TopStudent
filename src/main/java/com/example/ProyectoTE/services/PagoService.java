package com.example.ProyectoTE.services;

import com.example.ProyectoTE.entities.PagoEntity;
import com.example.ProyectoTE.entities.EstudianteEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class PagoService {

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
