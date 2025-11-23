package edu.unl.cc.algorithms;

import java.util.Comparator;
import edu.unl.cc.instrumentation.SortMetrics;

public interface SortAlgorithm<T> {
    void sort(T[] arr, Comparator<T> cmp, SortMetrics metrics);
}
