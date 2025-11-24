package edu.unl.cc.instrumentation;

import java.util.Arrays;
import java.util.Comparator;

import edu.unl.cc.algorithms.SortAlgorithm;
//Sirve para medir correctamente los tiempos

public class Benchmark {

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

            // Copia fresca del arreglo original
            T[] arr = Arrays.copyOf(original, original.length);

            SortMetrics metrics = new SortMetrics();

            long start = System.nanoTime();
            algorithm.sort(arr, comparator, metrics);
            long end = System.nanoTime();

            metrics.timeNs = end - start;
            times[i] = metrics.timeNs;
            lastMetrics = metrics;
        }

        // Ordenamos tiempos y sacamos la mediana
        Arrays.sort(times);

        long median = times[R / 2];
        lastMetrics.timeNs = median;

        return lastMetrics;
    }
}
