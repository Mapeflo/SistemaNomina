package com.empresa.nomina.modelo;

/**
 * Representa un empleado asalariado con salario fijo mensual.
 * Recibe bono del 10% si tiene más de 5 años en la empresa
 * y bono de alimentación por ser empleado permanente.
 */
public class EmpleadoAsalariado extends Empleado {

    private double salarioFijo;

    public EmpleadoAsalariado(String nombre, String identificacion, int aniosEnEmpresa, double salarioFijo) {
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
        double beneficios = 0;

        // Bono del 10% si lleva más de 5 años
        if (getAniosEnEmpresa() > 5) {
            beneficios += salarioFijo * 0.10;
        }

        // Bono de alimentación (empleado permanente)
        beneficios += 1_000_000;

        return beneficios;
    }

    @Override
    public double calcularDeducciones() {
        // Deducciones obligatorias: 4% de Seguro Social y Pensión
        // (ARL se puede ajustar después cuando se integre el módulo de deducciones)
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