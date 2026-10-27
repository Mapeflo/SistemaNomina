package com.empresa.nomina.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoAsalariadoTest {

    @Test
    void testSalarioBruto() {
        EmpleadoAsalariado empleado = new EmpleadoAsalariado("Ana", "123", 3, 3000000);
        assertEquals(3000000, empleado.calcularSalarioBruto(), 0.01);
    }

    @Test
    void testBeneficiosConMasDe5Anios() {
        EmpleadoAsalariado empleado = new EmpleadoAsalariado("Ana", "123", 6, 3000000);
        // 10% de bono (300.000) + bono alimentación (1.000.000) = 1.300.000
        assertEquals(1300000, empleado.calcularBeneficios(), 0.01);
    }

    @Test
    void testBeneficiosConMenosDe5Anios() {
        EmpleadoAsalariado empleado = new EmpleadoAsalariado("Ana", "123", 3, 3000000);
        // Solo bono alimentación
        assertEquals(1000000, empleado.calcularBeneficios(), 0.01);
    }

    @Test
    void testDeducciones() {
        EmpleadoAsalariado empleado = new EmpleadoAsalariado("Ana", "123", 3, 3000000);
        // 4% de 3.000.000 = 120.000
        assertEquals(120000, empleado.calcularDeducciones(), 0.01);
    }

    @Test
    void testSalarioNeto() {
        EmpleadoAsalariado empleado = new EmpleadoAsalariado("Ana", "123", 6, 3000000);
        // Bruto 3.000.000 + beneficios 1.300.000 - deducciones 120.000 = 4.180.000
        assertEquals(4180000, empleado.calcularSalarioNeto(), 0.01);
    }

    @Test
    void testSalarioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EmpleadoAsalariado("Ana", "123", 3, -1000);
        });
    }
}