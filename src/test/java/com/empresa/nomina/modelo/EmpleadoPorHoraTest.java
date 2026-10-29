package com.empresa.nomina.modelo;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import com.empresa.nomina.accionesSistema.CalculadoraArl;

class EmpleadoPorHoraTest {

    private static final double DELTA = 0.01;


    
    // Método auxiliar: crea un empleado sin repetir los 7 parámetros en cada prueba
    private EmpleadoPorHoras crear(int anios, double tarifa, int horas,
                                   int nivelArl, boolean fondo) {
        return new EmpleadoPorHoras("Ana", "123", anios, tarifa, horas, nivelArl, fondo);
    }




    // Testing para salario bruto

    @ParameterizedTest
    @CsvSource({
            "0,  0",         // sin horas
            "30, 300000",    // menos de 40: sin extras
            "40, 400000",    // límite: todavía sin extras
            "41, 415000",    // 40*10000 + 1*10000*1.5
            "45, 475000",    // 40*10000 + 5*10000*1.5
            "50, 550000"     // 40*10000 + 10*10000*1.5
    })
    void salarioBruto_segunHorasLaboradas(int horas, double esperado) {
        assertEquals(esperado, crear(0, 10_000, horas, 1, false).calcularSalarioBruto(), DELTA);
    }

    @Test
    void salarioBruto_alCambiarLasHoras_seRecalcula() {
        EmpleadoPorHoras e = crear(0, 10_000, 40, 1, false);

        e.setHorasLaboradas(45);

        assertEquals(475_000, e.calcularSalarioBruto(), DELTA);
    }

    @Test
    void salarioBruto_alCambiarLaTarifa_seRecalcula() {
        EmpleadoPorHoras e = crear(0, 10_000, 40, 1, false);

        e.setTarifaPorHora(20_000);

        assertEquals(800_000, e.calcularSalarioBruto(), DELTA);
    }




    // Testing para validar el constructor y sus valores iniciales

    @Test
    void constructor_conHorasNegativas_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> crear(0, 10_000, -1, 1, false));
    }

    @Test
    void constructor_conTarifaNegativa_lanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> crear(0, -10_000, 40, 1, false));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 6, 99})
    void constructor_conNivelArlFueraDeRango_lanzaExcepcion(int nivelInvalido) {
        assertThrows(IllegalArgumentException.class, () -> crear(0, 10_000, 40, nivelInvalido, false));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5})
    void constructor_conNivelArlValido_noLanzaExcepcion(int nivelValido) {
        assertDoesNotThrow(() -> crear(0, 10_000, 40, nivelValido, false));
    }




    // Testing para validar los setters

    @Test
    void setHorasLaboradas_negativas_lanzaExcepcionYConservaElValor() {
        EmpleadoPorHoras e = crear(0, 10_000, 40, 1, false);

        assertThrows(IllegalArgumentException.class, () -> e.setHorasLaboradas(-5));

        assertEquals(40, e.getHorasLaboradas());
    }

    @Test
    void setTarifaPorHora_negativa_lanzaExcepcionYConservaElValor() {
        EmpleadoPorHoras e = crear(0, 10_000, 40, 1, false);

        assertThrows(IllegalArgumentException.class, () -> e.setTarifaPorHora(-1));

        assertEquals(10_000, e.getTarifaPorHora(), DELTA);
    }

    @Test
    void setNivelArl_fueraDeRango_lanzaExcepcionYConservaElValor() {
        EmpleadoPorHoras e = crear(0, 10_000, 40, 2, false);

        assertThrows(IllegalArgumentException.class, () -> e.setNivelArl(7));

        assertEquals(2, e.getNivelArl());
    }




    // Testing para validar los empleados que SI aceptan fondo de ahorro

    @Test
    void fondoAhorro_masDeUnAnioYAcepta_es2PorCientoDelBruto() {
        assertEquals(8_000, crear(2, 10_000, 40, 1, true).calcularBeneficios(), DELTA);
    }

    @Test
    void fondoAhorro_seCalculaSobreElBrutoConHorasExtra() {
        // bruto 475.000 * 0.02
        assertEquals(9_500, crear(2, 10_000, 45, 1, true).calcularBeneficios(), DELTA);
    }

    @Test
    void fondoAhorro_masDeUnAnioPeroNoAcepta_noHayBeneficio() {
        assertEquals(0, crear(5, 10_000, 40, 1, false).calcularBeneficios(), DELTA);
    }

    @Test
    void fondoAhorro_aceptaPeroNoTieneUnAnio_noHayBeneficio() {
        assertEquals(0, crear(0, 10_000, 40, 1, true).calcularBeneficios(), DELTA);
    }

    @Test
    void fondoAhorro_conExactamenteUnAnio_noHayBeneficio() {
        // La regla es "más de 1 año": 1 no califica
        assertEquals(0, crear(1, 10_000, 40, 1, true).calcularBeneficios(), DELTA);
    }





    // Testing para validar las deducciones


    @Test
    void deducciones_incluyenSeguroSocialPensionYArl() {
        EmpleadoPorHoras e = crear(0, 10_000, 40, 1, false);

        double esperado = 400_000 * 0.04    // seguro social
                + 400_000 * 0.04    // pensión
                + CalculadoraArl.calcularArl(1, 400_000);

        assertEquals(esperado, e.calcularDeducciones(), DELTA);
    }

    @Test
    void deducciones_seCalculanSobreElBrutoConHorasExtra() {
        EmpleadoPorHoras e = crear(0, 10_000, 45, 3, false);

        double esperado = 475_000 * 0.08
                + CalculadoraArl.calcularArl(3, 475_000);

        assertEquals(esperado, e.calcularDeducciones(), DELTA);
    }





    // Testing para validar el Salario Neto

    @Test
    void salarioNeto_sinFondoDeAhorro() {
        EmpleadoPorHoras e = crear(0, 10_000, 40, 1, false);

        double esperado = 400_000
                - (400_000 * 0.08 + CalculadoraArl.calcularArl(1, 400_000));

        assertEquals(esperado, e.calcularSalarioNeto(), DELTA);
    }

    @Test
    void salarioNeto_conFondoDeAhorro_sumaElBeneficio() {
        EmpleadoPorHoras e = crear(2, 10_000, 40, 1, true);

        double esperado = 400_000 + 8_000
                - (400_000 * 0.08 + CalculadoraArl.calcularArl(1, 400_000));

        assertEquals(esperado, e.calcularSalarioNeto(), DELTA);
    }

    @Test
    void salarioNeto_funcionaTambienPorPolimorfismo() {
        Empleado e = crear(0, 10_000, 40, 1, false);

        assertTrue(e.calcularSalarioNeto() > 0);
    }
}