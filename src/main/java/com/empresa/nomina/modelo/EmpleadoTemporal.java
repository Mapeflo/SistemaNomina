package com.empresa.nomina.modelo;

/**
 * Representa un empleado temporal con salario fijo mensual.
 * No aplica bonos ni beneficios adicionales.
 */
public class EmpleadoTemporal extends Empleado {

    private double salarioFijo;

    public EmpleadoTemporal(String nombre, String identificacion, int aniosEnEmpresa, double salarioFijo) {
        super(nombre, identificacion, aniosEnEmpresa);
        if (salarioFijo < 0) {
            throw new IllegalArgumentException("El salario fijo no puede ser negativo");
        }
        this.salarioFijo = salarioFijo;
    }

    @Override
    public double calcularSalarioBruto() {
        return salarioFijo;
    }

    @Override
    public double calcularBeneficios() {
        // Empleado temporal no recibe bonos ni beneficios adicionales
        return 0;
    }

    @Override
    public double calcularDeducciones() {
        // Deducciones obligatorias: 4% de Seguro Social y Pensión
        double salarioBruto = calcularSalarioBruto();
        return salarioBruto * 0.04;
    }

    public double getSalarioFijo() {
        return salarioFijo;
    }

    public void setSalarioFijo(double salarioFijo) {
        if (salarioFijo < 0) {
            throw new IllegalArgumentException("El salario fijo no puede ser negativo");
        }
        this.salarioFijo = salarioFijo;
    }
}


