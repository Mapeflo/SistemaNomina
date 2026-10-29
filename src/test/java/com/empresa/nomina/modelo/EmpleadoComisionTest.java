package com.empresa.nomina.modelo;

import com.empresa.nomina.servicio.CalculadoraBeneficios;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Pruebas unitarias para la clase EmpleadoComision
 * y el módulo de beneficios (CalculadoraBeneficios).
 */
class EmpleadoComisionTest {

    @Test
    void calcularSalarioBrutoConComisionNormal() {
        // Ventas de $15.000.000, comisión del 5%
        EmpleadoComision empleado = new EmpleadoComision(
                "Carlos", "1001", 3, 2_000_000, 5.0, 15_000_000);

        // salarioBase(2M) + comision(15M * 5% = 750K) = 2.750.000
        assertEquals(2_750_000, empleado.calcularSalarioBruto(), 0.01);
    }

    @Test
    void calcularSalarioBrutoConBonoAdicionalPorVentas() {
        // Ventas de $25.000.000 (supera umbral de $20M), comisión del 5%
        EmpleadoComision empleado = new EmpleadoComision(
                "Ana", "1002", 4, 2_000_000, 5.0, 25_000_000);

        // salarioBase(2M) + comision(25M * 5% = 1.25M) + bono(25M * 3% = 750K) = 4.000.000
        assertEquals(4_000_000, empleado.calcularSalarioBruto(), 0.01);
    }

    @Test
    void calcularSalarioBrutoConVentasEnCero() {
        EmpleadoComision empleado = new EmpleadoComision(
                "Pedro", "1003", 2, 2_000_000, 5.0, 0);

        // Solo cobra el salario base
        assertEquals(2_000_000, empleado.calcularSalarioBruto(), 0.01);
    }

    @Test
    void ventasExactamenteEnUmbralNoAplicaBonoAdicional() {
        // Ventas exactamente en $20.000.000 (no supera, es igual)
        EmpleadoComision empleado = new EmpleadoComision(
                "Diego", "1009", 2, 2_000_000, 5.0, 20_000_000);

        // salarioBase(2M) + comision(20M * 5% = 1M) = 3.000.000 (sin bono del 3%)
        assertEquals(3_000_000, empleado.calcularSalarioBruto(), 0.01);
    }

    @Test
    void calcularBeneficiosIncluyeBonoAlimentacion() {
        EmpleadoComision empleado = new EmpleadoComision(
                "Laura", "1004", 1, 1_500_000, 10.0, 5_000_000);

        // El bono de alimentación es $1.000.000 para empleados permanentes
        assertEquals(1_000_000, empleado.calcularBeneficios(), 0.01);
    }

    @Test
    void bonoAlimentacionNoAplicaAEmpleadosNoPermanentes() {
        double bonoPermanente = CalculadoraBeneficios.calcularBonoAlimentacion(true);
        assertEquals(1_000_000, bonoPermanente, 0.01);

        double bonoNoPermanente = CalculadoraBeneficios.calcularBonoAlimentacion(false);
        assertEquals(0, bonoNoPermanente, 0.01);
    }

    @Test
    void calcularDeduccionesAplicaCuatroPorciento() {
        EmpleadoComision empleado = new EmpleadoComision(
                "Luis", "1005", 3, 2_000_000, 5.0, 10_000_000);

        // Salario bruto = 2M + (10M * 5%) = 2.500.000
        // Deducciones = 2.500.000 * 4% = 100.000
        double salarioBruto = empleado.calcularSalarioBruto();
        assertEquals(salarioBruto * 0.04, empleado.calcularDeducciones(), 0.01);
    }

    @Test
    void calcularSalarioNetoPositivo() {
        EmpleadoComision empleado = new EmpleadoComision(
                "Maria", "1006", 2, 2_000_000, 5.0, 15_000_000);

        double neto = empleado.calcularSalarioNeto();
        assertTrue(neto > 0, "El salario neto debe ser positivo");
    }

    @Test
    void ventasNegativasLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmpleadoComision("Juan", "1007", 1, 2_000_000, 5.0, -1_000_000));
    }

    @Test
    void salarioBaseNegativoLanzaExcepcion() {
        assertThrows(IllegalArgumentException.class, () ->
                new EmpleadoComision("Rosa", "1008", 1, -500_000, 5.0, 10_000_000));
    }
}
