package com.empresa.nomina.accionesSistema;

public final class CalculadoraArl {

    private static final double SALARIO_MINIMO = 1750905; // salario Minimo del 2026
    private static final double FACTOR_IBC = 0.40;

    private CalculadoraArl() {}

    /**
     * Valor de ARL que se descuenta al contratista.
     * Riesgos 4 y 5 los asume el contratante, por eso retornan 0.
     */
    public static double calcularArl(int nivelArl, double valorMensual) {
        double tarifa = switch (nivelArl) {
            case 1 -> 0.00522;
            case 2 -> 0.01044;
            case 3 -> 0.02436;
            case 4, 5 -> 0;
            default -> throw new IllegalArgumentException(
                    "El nivel de riesgo ARL debe estar entre 1 y 5");
        };

        double ibc = Math.max(valorMensual * FACTOR_IBC, SALARIO_MINIMO);
        return ibc * tarifa;
    }
}