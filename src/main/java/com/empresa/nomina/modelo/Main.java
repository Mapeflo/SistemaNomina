package com.empresa.nomina.modelo;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        System.out.println("Iniciando simulación del Sistema de Nómina");

        List<Empleado> listaEmpleados = new ArrayList<>();

        // EmpleadoAsalariado: nombre, identificacion, aniosEnEmpresa, salarioFijo
        listaEmpleados.add(new EmpleadoAsalariado("Ana Asalariada", "001", 6, 2000000.0));

        // EmpleadoTemporal: nombre, identificacion, aniosEnEmpresa, salarioFijo
        listaEmpleados.add(new EmpleadoTemporal("Luis Temporal", "002", 1, 1500000.0));

        // EmpleadoPorHoras: nombre, identificacion, aniosEnEmpresa, tarifaPorHora, horasLaboradas, nivelArl, aceptaFondo
        listaEmpleados.add(new EmpleadoPorHoras("Carlos Horas", "003", 2, 50000.0, 45, 1, true));

        // EmpleadoComision: nombre, identificacion, aniosEnEmpresa, salarioBase, porcentajeComision, ventasMes
        listaEmpleados.add(new EmpleadoComision("Diana Comision", "004", 3, 1200000.0, 5.0, 25000000.0));

        CalculadoraNomina calculadora = new CalculadoraNomina();
        calculadora.procesarNomina(listaEmpleados);

        System.out.println("Simulación finalizada.");
    }
}