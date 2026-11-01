package com.empresa.nomina.modelo;

import com.empresa.nomina.servicio.CalculadoraBeneficios;

/**
 * Representa un empleado que recibe un salario base
 * más un porcentaje de comisión sobre sus ventas mensuales.
 * Si las ventas superan los $20.000.000, recibe un bono adicional del 3%.
 */
public class EmpleadoComision extends Empleado {

    private static final double UMBRAL_BONO_VENTAS = 20_000_000;
    private static final double PORCENTAJE_BONO_ADICIONAL = 0.03;

    private double salarioBase;
    private double porcentajeComision;
    private double ventasMes;

    public EmpleadoComision(String nombre, String identificacion, int aniosEnEmpresa,
                            double salarioBase, double porcentajeComision, double ventasMes) {
        super(nombre, identificacion, aniosEnEmpresa);

        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        }
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            throw new IllegalArgumentException("El porcentaje de comision debe estar entre 0 y 100");
        }
        if (ventasMes < 0) {
            throw new IllegalArgumentException("Las ventas no pueden ser menores a cero");
        }

        this.salarioBase = salarioBase;
        this.porcentajeComision = porcentajeComision;
        this.ventasMes = ventasMes;
    }

    @Override
    public double calcularSalarioBruto() {
        // Comisión regular sobre las ventas del mes
        double comision = ventasMes * (porcentajeComision / 100.0);

        // Bono adicional del 3% si las ventas superan los $20.000.000
        double bonoAdicional = 0;
        if (ventasMes > UMBRAL_BONO_VENTAS) {
            bonoAdicional = ventasMes * PORCENTAJE_BONO_ADICIONAL;
        }

        return salarioBase + comision + bonoAdicional;
    }

    @Override
    public double calcularBeneficios() {
        // Bono de alimentación: aplica por ser empleado permanente
        return CalculadoraBeneficios.calcularBonoAlimentacion(true);
    }

    @Override
    public double calcularDeducciones() {
        // Deducciones obligatorias: 4% de Seguro Social y Pensión
        double salarioBruto = calcularSalarioBruto();
        return salarioBruto * 0.04;
    }

    // ==================== Getters ====================
    public double getSalarioBase() {
        return salarioBase;
    }

    public double getPorcentajeComision() {
        return porcentajeComision;
    }

    public double getVentasMes() {
        return ventasMes;
    }

    // ==================== Setters ====================
    public void setSalarioBase(double salarioBase) {
        if (salarioBase < 0) {
            throw new IllegalArgumentException("El salario base no puede ser negativo");
        }
        this.salarioBase = salarioBase;
    }

    public void setPorcentajeComision(double porcentajeComision) {
        if (porcentajeComision < 0 || porcentajeComision > 100) {
            throw new IllegalArgumentException("El porcentaje de comision debe estar entre 0 y 100");
        }
        this.porcentajeComision = porcentajeComision;
    }

    public void setVentasMes(double ventasMes) {
        if (ventasMes < 0) {
            throw new IllegalArgumentException("Las ventas no pueden ser menores a cero");
        }
        this.ventasMes = ventasMes;
    }
}
