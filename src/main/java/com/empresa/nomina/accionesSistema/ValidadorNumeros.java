package com.empresa.nomina.accionesSistema;

public class ValidadorNumeros {

    public static int validarNegativo(int numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Error: el valor no debe ser negativo");
        }
        return numero;
    }

    public static double validarNegativo(double numero) {
        if (numero < 0) {
            throw new IllegalArgumentException("Error: el valor no debe ser negativo");
        }
        return numero;
    }

    public static int validarFueraRango(int numero, int min, int max) {
        if (numero < min  || numero > max) {
            throw new IllegalArgumentException(
                    "El valor debe estar entre " + min + " y " + max);
        }
        return numero;
    }


}
