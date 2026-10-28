package com.empresa.nomina.modelo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class EmpleadoTemporalTest {

    @Test
    void testSalarioBruto() {
        EmpleadoTemporal empleado = new EmpleadoTemporal("Pedro", "456", 1, 2500000);
        assertEquals(2500000, empleado.calcularSalarioBruto(), 0.01);
    }

    @Test
    void testBeneficiosSiempreCero() {
        EmpleadoTemporal empleado = new EmpleadoTemporal("Pedro", "456", 1, 2500000);
        // Empleado temporal no recibe beneficios
        assertEquals(0, empleado.calcularBeneficios(), 0.01);
    }

    @Test
    void testDeducciones() {
        EmpleadoTemporal empleado = new EmpleadoTemporal("Pedro", "456", 1, 2500000);
        // 4% de 2.500.000 = 100.000
        assertEquals(100000, empleado.calcularDeducciones(), 0.01);
    }

    @Test
    void testSalarioNeto() {
        EmpleadoTemporal empleado = new EmpleadoTemporal("Pedro", "456", 1, 2500000);
        // Bruto 2.500.000 + beneficios 0 - deducciones 100.000 = 2.400.000
        assertEquals(2400000, empleado.calcularSalarioNeto(), 0.01);
    }

    @Test
    void testSalarioNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () -> {
            new EmpleadoTemporal("Pedro", "456", 1, -500);
        });
    }
}