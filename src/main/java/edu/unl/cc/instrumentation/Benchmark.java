package edu.unl.cc.instrumentation;

import java.util.Arrays;
import java.util.Comparator;

import edu.unl.cc.algorithms.SortAlgorithm;

public class Benchmark {

    /**
     * Ejecuta el algoritmo `R` veces sobre copias del arreglo original,
     * mide el tiempo de ejecución en nanosegundos y devuelve las métricas
     * de la última ejecución con el campo `timeNs` sustituido por la mediana
     * de todos los tiempos medidos.
     *
     * @param algorithm  implementación de SortAlgorithm a evaluar
     * @param original   arreglo de entrada (no se modifica)
     * @param comparator comparador para el tipo T
     * @param R          número de repeticiones (debe ser > 0)
     * @param <T>        tipo de los elementos del arreglo
     * @return SortMetrics de la última ejecución con timeNs = mediana de tiempos
     */
    public static <T> SortMetrics run(
            SortAlgorithm<T> algorithm,
            T[] original,
            Comparator<T> comparator,
            int R
    )
    {

        long[] times = new long[R];
        SortMetrics lastMetrics = null;

        for (int i = 0; i < R; i++) {

            // Copia fresca del arreglo original para cada ejecución
            T[] arr = Arrays.copyOf(original, original.length);

            SortMetrics metrics = new SortMetrics();

            long start = System.nanoTime();
            algorithm.sort(arr, comparator, metrics);
            long end = System.nanoTime();

            metrics.timeNs = end - start;
            times[i] = metrics.timeNs;
            lastMetrics = metrics;
        }

        // Ordenar los tiempos y tomar la mediana como valor representativo
        Arrays.sort(times);

        long median = times[R / 2];
        lastMetrics.timeNs = median;

        return lastMetrics;
    }
}
