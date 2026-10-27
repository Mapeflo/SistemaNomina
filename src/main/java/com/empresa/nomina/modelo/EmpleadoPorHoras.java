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

        if(getAniosEnEmpresa() > 1){
            double porcentajeBeneficio = 0.2;
            return tarifaPorHora * porcentajeBeneficio;
        } else{
            return 0;
        }

    }

    @Override
    public double calcularDeducciones() {

        double seguroSocial = 0.04;
        double pension = 0.04;
        return calcularSalarioBruto() * pension - calcularSalarioBruto() * seguroSocial;

    }

    @Override
    public double calcularSalarioBruto() {

        if(horasLaboradas > 8){
            double valorHoraExtra = 1.5;
            return (tarifaPorHora * horasLaboradas) * valorHoraExtra;
        } else {
            return tarifaPorHora * horasLaboradas;
        }

    }



    @Override
    public double calcularSalarioNeto() {
        return super.calcularSalarioNeto();
    }








}
