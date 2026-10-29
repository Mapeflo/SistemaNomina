package com.empresa.nomina.modelo;

import com.empresa.nomina.accionesSistema.CalculadoraArl;
import com.empresa.nomina.accionesSistema.ValidadorNumeros;

public class EmpleadoPorHoras extends Empleado{

    private double tarifaPorHora;
    private int horasLaboradas;
    private int nivelArl;
    private boolean aceptaFondoAhorro;

    public EmpleadoPorHoras(String nombre, String identificacion, int aniosEnEmpresa, double tarifaPorHora, int horasLaboradas, int nivelArl, boolean aceptaFondoAhorro){
        super(nombre, identificacion, aniosEnEmpresa);
        this.tarifaPorHora = ValidadorNumeros.validarNegativo(tarifaPorHora);
        this.horasLaboradas = ValidadorNumeros.validarNegativo(horasLaboradas);
        this.nivelArl = ValidadorNumeros.validarFueraRango(nivelArl, 1, 5);
        this.aceptaFondoAhorro = aceptaFondoAhorro;
    }


    @Override
    public double calcularBeneficios() {
        double beneficio = 0;

        // Fondo de ahorro de 2%
        if (getAniosEnEmpresa() > 1 && aceptaFondoAhorro) {
            double porcentajeBeneficio = 0.02;
            beneficio += calcularSalarioBruto() * porcentajeBeneficio;
            return beneficio;
        }
        return beneficio;
    }


    @Override
    public double calcularDeducciones() {

        double seguroSocial = 0.04;
        double pension = 0.04;


        return calcularSalarioBruto() * pension +
                calcularSalarioBruto() * seguroSocial +
                CalculadoraArl.calcularArl(this.nivelArl, calcularSalarioBruto());
    }


    @Override
    public double calcularSalarioBruto() {
        double salarioBruto = this.horasLaboradas * this.tarifaPorHora;
        int horasExtras;
        int horasNormales = 40;


        //Cuando el trabajador hace horas extras
        if (horasLaboradas > horasNormales) {
            horasExtras = horasLaboradas - horasNormales;

            double valorHoraExtra = 1.5;
            return tarifaPorHora * horasNormales +
                    tarifaPorHora * horasExtras * valorHoraExtra;
        }

        return salarioBruto;
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