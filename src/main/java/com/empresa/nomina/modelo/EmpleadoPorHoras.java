package com.empresa.nomina.modelo;

public class EmpleadoPorHoras extends Empleado{

    private double tarifaPorHora;
    private int horasLaboradas;

    public EmpleadoPorHoras(String nombre, String identificacion, int aniosEnEmpresa, double salarioPorHora, int horasLaboradas){
        super(nombre, identificacion, aniosEnEmpresa);
        this.tarifaPorHora = tarifaPorHora;
        this.horasLaboradas = horasLaboradas;
    }


    @Override
    public double calcularBeneficios() {
        return 0;
    }

    @Override
    public double calcularDeducciones() {
        return 0;
    }

    @Override
    public double calcularSalarioBruto() {
        return 0;
    }



    @Override
    public double calcularSalarioNeto() {
        return super.calcularSalarioNeto();
    }








}
