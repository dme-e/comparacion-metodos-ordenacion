package edu.unl.cc.algorithms;

import java.util.Comparator;
import edu.unl.cc.instrumentation.SortMetrics;

public class BubbleSort<T> implements SortAlgorithm<T> {

    @Override
    public void sort(T[] array, Comparator<T> cmp, SortMetrics metrics) {

        for (int i = 0; i < array.length - 1; i++) {
            boolean swapped = false;

            for (int j = 0; j < array.length - 1 - i; j++) {

                metrics.comparisons++;

                if (cmp.compare(array[j], array[j + 1]) > 0) {

                    T temp = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temp;

                    metrics.swaps++;
                    swapped = true;
                }
            }

            if (!swapped) break;
        }
    }
}
