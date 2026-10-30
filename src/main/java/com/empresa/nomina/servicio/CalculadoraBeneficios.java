package com.empresa.nomina.servicio;

/**
 * Clase encargada de calcular los beneficios adicionales
 * que aplican a los empleados permanentes de la empresa.
 */
public class CalculadoraBeneficios {

    // Bono de alimentación mensual para empleados permanentes
    public static final double BONO_ALIMENTACION = 1_000_000;

    /**
     * Calcula el bono de alimentación.
     * Solo aplica para empleados permanentes (Asalariado y Comisión).
     *
     * @param esPermanente true si el empleado es de tipo permanente
     * @return el valor del bono o 0 si no aplica
     */
    public static double calcularBonoAlimentacion(boolean esPermanente) {
        if (esPermanente) {
            return BONO_ALIMENTACION;
        }
        return 0;
    }
}
