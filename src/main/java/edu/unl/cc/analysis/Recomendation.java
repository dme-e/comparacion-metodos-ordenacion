package edu.unl.cc.analysis;

import edu.unl.cc.instrumentation.SortMetrics;

/**
 * Genera recomendaciones sobre qué algoritmo de ordenamiento es más
 * apropiado para un conjunto de datos dado basándose en métricas
 * (comparaciones e intercambios) recogidas durante la ejecución.
 */
public class Recomendation {

    /**
     * Analiza métricas de Bubble, Insertion y Selection y escribe
     * recomendaciones por consola. Solo se utiliza la longitud de
     * "datos" para heurísticas relacionadas con el tamaño del conjunto.
     *
     * @param datos     arreglo de entrada (solo se usa su longitud)
     * @param burbuja   métricas registradas para BubbleSort
     * @param insercion métricas registradas para InsertionSort
     * @param seleccion métricas registradas para SelectionSort
     */
    public static void generate(Object[] datos, SortMetrics burbuja, SortMetrics insercion, SortMetrics seleccion) {

        int n = datos.length;

        System.out.println("-----------------------------------------------------");
        System.out.println("                MATRIZ DE RECOMENDACIÓN");
        System.out.println("-----------------------------------------------------");

        if (esCasiOrdenado(burbuja.comparisons, insercion.comparisons)) {
            System.out.println("Datos parecen casi ordenados → Inserción.");
        }

        if (seleccion.swaps < burbuja.swaps && seleccion.swaps < insercion.swaps) {
            System.out.println("Si necesitas minimizar intercambios → Selección.");
        }

        if (insercion.comparisons < burbuja.comparisons &&
                insercion.comparisons < seleccion.comparisons) {
            System.out.println("Inserción realizó menos comparaciones → eficiente para este dataset.");
        }

        if (n > 500) {
            System.out.println("n es grande → Ninguno es ideal (usar Merge/Quick), pero Selección es más predecible.");
        }

        if (burbuja.swaps > ((long) n * n) / 4) {
            System.out.println("Parece inverso → Burbuja rinde mal; Selección puede ser más estable.");
        }

        System.out.println();
    }

    /**
     * Heurística simple para detectar si un arreglo está casi ordenado.
     * Devuelve true si Insertion realizó menos de un tercio de las
     * comparaciones que Bubble (indicador práctico usado aquí).
     *
     * @param compBurbuja   comparaciones registradas por BubbleSort
     * @param compInsercion comparaciones registradas por InsertionSort
     * @return true si se considera que los datos están casi ordenados
     */
    private static boolean esCasiOrdenado(long compBurbuja, long compInsercion) {
        return compInsercion < (compBurbuja / 3);
    }
}
