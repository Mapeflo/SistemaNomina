package com.empresa.nomina.modelo;

/**
 * Clase abstracta que representa a un empleado genérico.
 * Define el comportamiento común y obliga a las subclases
 * a implementar el cálculo de salario, beneficios y deducciones.
 */
public abstract class Empleado {

    private String nombre;
    private String identificacion;
    private int aniosEnEmpresa;

    public Empleado(String nombre, String identificacion, int aniosEnEmpresa) {
        this.nombre = nombre;
        this.identificacion = identificacion;
        this.aniosEnEmpresa = aniosEnEmpresa;
    }

    // Métodos abstractos (cada tipo de empleado los implementará de forma diferente)
    public abstract double calcularSalarioBruto();

    public abstract double calcularBeneficios();

    public abstract double calcularDeducciones();

    /**
     * Calcula el salario neto del empleado.
     * Valida que el resultado no sea negativo.
     */
    public double calcularSalarioNeto() {
        double salarioNeto = calcularSalarioBruto() + calcularBeneficios() - calcularDeducciones();

        if (salarioNeto < 0) {
            throw new IllegalArgumentException("El salario neto no puede ser negativo");
        }
        return salarioNeto;
    }

    // ==================== Getters ====================
    public String getNombre() {
        return nombre;
    }

    public String getIdentificacion() {
        return identificacion;
    }

    public int getAniosEnEmpresa() {
        return aniosEnEmpresa;
    }

    // ==================== Setters ====================
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setIdentificacion(String identificacion) {
        this.identificacion = identificacion;
    }

    public void setAniosEnEmpresa(int aniosEnEmpresa) {
        this.aniosEnEmpresa = aniosEnEmpresa;
    }
}