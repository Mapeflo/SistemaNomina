package com.empresa.nomina.modelo;

import com.empresa.nomina.accionesSistema.ValidadorNumeros;

public class EmpleadoPorHoras extends Empleado{

    private double tarifaPorHora;
    private int horasLaboradas;
    private int nivelArl;
    private boolean aceptaFondoAhorro;

    public EmpleadoPorHoras(String nombre, String identificacion, int aniosEnEmpresa, double tarifaPorHora, int horasLaboradas){
        super(nombre, identificacion, aniosEnEmpresa);
        this.tarifaPorHora = ValidadorNumeros.validarNegativo(tarifaPorHora);
        this.horasLaboradas = ValidadorNumeros.validarNegativo(horasLaboradas);
        this.nivelArl = ValidadorNumeros.validarFueraRango(nivelArl, 1, 5);
        this.aceptaFondoAhorro = aceptaFondoAhorro;
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





    public double getTarifaPorHora() {
        return tarifaPorHora;
    }

    public int getHorasLaboradas() {
        return horasLaboradas;
    }

    public int getNivelArl() {
        return nivelArl;
    }


    public boolean isAceptaFondoAhorro() {
        return aceptaFondoAhorro;
    }




    public void setNivelArl(int nivelArl) {
        ValidadorNumeros.validarNegativo(nivelArl);
        ValidadorNumeros.validarFueraRango(nivelArl,1,5);
        this.nivelArl = nivelArl;
    }

    public void setTarifaPorHora(double tarifaPorHora) {
        ValidadorNumeros.validarNegativo(tarifaPorHora);
        this.tarifaPorHora = tarifaPorHora;
    }

    public void setHorasLaboradas(int horasLaboradas) {
        ValidadorNumeros.validarNegativo(horasLaboradas);
        this.horasLaboradas = horasLaboradas;
    }

    public void setAceptaFondoAhorro(boolean aceptaFondoAhorro) {
        this.aceptaFondoAhorro = aceptaFondoAhorro;
    }
}