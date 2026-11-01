package com.empresa.nomina.modelo;

import java.util.List;

public class CalculadoraNomina {

    // Método para calcular el total de la nómina y validar salarios
    public void procesarNomina(List<Empleado> empleados) {
        double nominaTotal = 0.0;

        System.out.println("=== REPORTE DE NÓMINA GLOBAL ===");

        for (Empleado emp : empleados) {
            // Se asume que la clase abstracta Empleado tiene un método para calcular el salario neto
            double salarioNeto = emp.calcularSalarioNeto();

            // Validación global: ningún salario neto puede ser negativo
            if (salarioNeto < 0) {
                salarioNeto = 0.0;
            }

            System.out.println("- Empleado procesado. Salario Neto: $" + salarioNeto);
            nominaTotal += salarioNeto;
        }

        System.out.println("================================");
        System.out.println("Total General de la Nómina: $" + nominaTotal);
    }
}