package com.example.ProyectoTE;

import com.example.ProyectoTE.entities.EstudianteEntity;
import com.example.ProyectoTE.services.PagoService;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class PagoTest {

    EstudianteEntity estudiante= new EstudianteEntity();

    PagoService pagoService = new PagoService();

    @Test
    void calcularDescuentoMunicipal(){
        estudiante.setTipoColegioProcedencia("Municipal");

        double descuento = pagoService.calcularDescuento(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(1200000,descuento, 0.0);

    }
    @Test
    void calcularDescuentoSubvencionado(){
        estudiante.setTipoColegioProcedencia("Subvencionado");

        double descuento = pagoService.calcularDescuento(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(1350000,descuento, 0.0);

    }
    @Test
    void calcularDescuentoPrivado(){
        estudiante.setTipoColegioProcedencia("Privado");

        double descuento = pagoService.calcularDescuento(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(1500000,descuento, 0.0);

    }

    @Test
    void calcularDescuentoEgreso(){
        estudiante.setTipoColegioProcedencia("Privado");
        estudiante.setAnioEgresoColegio(0);

        double descuentoEgreso = pagoService.calcularDescuentoEgreso(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(1275000,descuentoEgreso, 0.0);

    }
    @Test
    void calcularDescuentoEgreso2(){
        estudiante.setTipoColegioProcedencia("Subvencionado");
        estudiante.setAnioEgresoColegio(1);
        double descuentoEgreso = pagoService.calcularDescuentoEgreso(estudiante);
        // Verifica que el cálculo sea correcto
        assertEquals(1242000,descuentoEgreso, 0.0);

    }
    @Test
    void calcularDescuentoEgreso3(){
        estudiante.setTipoColegioProcedencia("Municipal");
        estudiante.setAnioEgresoColegio(3);

        double descuentoEgreso = pagoService.calcularDescuentoEgreso(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(1152000,descuentoEgreso, 0.0);

    }
    @Test
    void calcularDescuentoEgreso4(){
        estudiante.setTipoColegioProcedencia("Municipal");
        estudiante.setAnioEgresoColegio(6);

        double descuentoEgreso = pagoService.calcularDescuentoEgreso(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(1200000,descuentoEgreso, 0.0);

    }

    @Test
    void calcularCuotas(){
        estudiante.setFormaPago("Contado");
        estudiante.setTipoColegioProcedencia("Municipal");
        estudiante.setCantidadCuotasE(1);
        estudiante.setAnioEgresoColegio(1);

        double cuota = pagoService.calcularCuotas(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(552000,cuota, 0.0);

    }
    @Test
    void calcularCuotasMuni(){
        estudiante.setFormaPago("Cuotas");
        estudiante.setTipoColegioProcedencia("Municipal");
        estudiante.setCantidadCuotasE(8);
        estudiante.setAnioEgresoColegio(4);

        double cuota = pagoService.calcularCuotas(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(144000,cuota, 0.0);

    }
    @Test
    void calcularCuotasSub(){
        estudiante.setFormaPago("Cuotas");
        estudiante.setTipoColegioProcedencia("Subvencionado");
        estudiante.setCantidadCuotasE(5);
        estudiante.setAnioEgresoColegio(6);

        double cuota = pagoService.calcularCuotas(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(270000,cuota, 0.0);

    }
    @Test
    void calcularCuotasPriv(){
        estudiante.setFormaPago("Cuotas");
        estudiante.setTipoColegioProcedencia("Privado");
        estudiante.setCantidadCuotasE(4);
        estudiante.setAnioEgresoColegio(2);

        double cuota = pagoService.calcularCuotas(estudiante);

        // Verifica que el cálculo sea correcto
        assertEquals(345000,cuota, 0.0);

    }

    @Test
    void listarCuotas() {
        estudiante.setFormaPago("Cuotas");
        estudiante.setTipoColegioProcedencia("Privado");
        estudiante.setCantidadCuotasE(4);
        estudiante.setAnioEgresoColegio(2);

        double[] listaCuotas = pagoService.listarCuotas(estudiante);

        // Verifica que el tamaño del arreglo de cuotas sea 1
        assertEquals(4, listaCuotas.length);

        // Verifica que el valor de la única cuota sea igual al resultado esperado
        assertEquals(345000, listaCuotas[0], 0.0);
    }
    @Test
    void listarCuotas2() {
        estudiante.setFormaPago("Cuotas");
        estudiante.setTipoColegioProcedencia("Subvencionado");
        estudiante.setCantidadCuotasE(5);
        estudiante.setAnioEgresoColegio(6);

        double[] listaCuotas = pagoService.listarCuotas(estudiante);

        // Verifica que el tamaño del arreglo de cuotas sea 1
        assertEquals(5, listaCuotas.length);

        // Verifica que el valor de la única cuota sea igual al resultado esperado
        assertEquals(270000, listaCuotas[0], 0.0);
    }

    @Test
    void listarCuotas3() {
        estudiante.setFormaPago("Cuotas");
        estudiante.setTipoColegioProcedencia("Municipal");
        estudiante.setCantidadCuotasE(8);
        estudiante.setAnioEgresoColegio(4);

        double[] listaCuotas = pagoService.listarCuotas(estudiante);

        // Verifica que el tamaño del arreglo de cuotas sea 1
        assertEquals(8, listaCuotas.length);

        // Verifica que el valor de la única cuota sea igual al resultado esperado
        assertEquals(144000, listaCuotas[0], 0.0);
    }


    @Test
    void listarEstado1() {

        estudiante.setCantidadCuotasE(1);

        String[] listaEstados = pagoService.listarEstado(estudiante);

        // Verifica que el tamaño del arreglo de estados sea 1
        assertEquals(1, listaEstados.length);

        // Verifica que el estado sea "Pendiente"
        assertEquals("Pendiente", listaEstados[0]);
    }

    @Test
    void listarEstado2() {
        estudiante.setCantidadCuotasE(5);

        String[] listaEstados = pagoService.listarEstado(estudiante);

        // Verifica que el tamaño del arreglo de estados sea 5
        assertEquals(5, listaEstados.length);

        // Verifica que todos los estados sean "Pendiente"
        for (String estado : listaEstados) {
            assertEquals("Pendiente", estado);
        }
    }


    @Test
    void calcularFechasLimitePago1() {
        // Configura el estudiante para cinco cuotas
        estudiante.setCantidadCuotasE(5);

        // Ejecuta la función para obtener las fechas límite
        String[] fechasLimite = pagoService.calcularFechasLimitePago(estudiante);

        // Verifica que el tamaño del arreglo de fechas límite sea 5
        assertEquals(5, fechasLimite.length);
        // Obtiene la fecha actual

    }

    @Test
    void calcularMesesAtraso1() {
        estudiante.setCantidadCuotasE(3);
        String[] fechasLimite = {"2023-03-10", "2023-04-10", "2023-05-10"};

        String[] mesesAtraso = pagoService.calcularMesesAtraso(estudiante, fechasLimite);

        assertArrayEquals(new String[]{"7", "6", "5"}, mesesAtraso);
    }
    @Test
    void calcularMesesAtrasoSinAtrasos() {
        estudiante.setCantidadCuotasE(1);
        String[] fechasLimite = {"2023-11-10"};

        String[] mesesAtraso = pagoService.calcularMesesAtraso(estudiante, fechasLimite);

        assertArrayEquals(new String[]{"0"}, mesesAtraso);
    }

    @Test
    void calcularCuotaFinalSinAtraso() {
        EstudianteEntity estudiante = new EstudianteEntity();
        double cuota = 1000.0;
        int mesesAtraso = 0;

        double cuotaFinal = pagoService.calcularCuotaFinal(estudiante, mesesAtraso, cuota);

        assertEquals(1000.0, cuotaFinal, 0.0);
    }

    @Test
    void calcularCuotaFinalConUnMesAtraso() {
        EstudianteEntity estudiante = new EstudianteEntity();
        double cuota = 1000;
        int mesesAtraso = 1;

        double cuotaFinal = pagoService.calcularCuotaFinal(estudiante, mesesAtraso, cuota);

        // El interés debe ser del 3%, por lo que la cuota final debe ser 1030.0
        assertEquals(1030, cuotaFinal, 0.0);
    }

    @Test
    void calcularCuotaFinalConTresMesesAtraso() {
        EstudianteEntity estudiante = new EstudianteEntity();
        double cuota = 1000;
        int mesesAtraso = 3;

        double cuotaFinal = pagoService.calcularCuotaFinal(estudiante, mesesAtraso, cuota);

        // El interés debe ser del 9%, por lo que la cuota final debe ser 1090.0
        assertEquals(1090, cuotaFinal, 0.0);
    }

    @Test
    void calcularCuotaFinalConMasDeTresMesesAtraso() {
        EstudianteEntity estudiante = new EstudianteEntity();
        double cuota = 1000;
        int mesesAtraso = 4;

        double cuotaFinal = pagoService.calcularCuotaFinal(estudiante, mesesAtraso, cuota);

        // El interés debe ser del 15%, por lo que la cuota final debe ser 1150.0
        assertEquals(1150, cuotaFinal, 0.0);
    }




}
