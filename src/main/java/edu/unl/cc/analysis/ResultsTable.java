package edu.unl.cc.analysis;

import edu.unl.cc.instrumentation.SortMetrics;

public class ResultsTable {

    public static void printHeader() {
        System.out.println("\n=========================================");
        System.out.println(" COMPARACIÓN DE RESULTADOS DE ORDENACIÓN ");
        System.out.println("=========================================");
        System.out.printf("%-12s %-12s %-12s %-12s %-18s%n",
                "Algoritmo", "Compar.", "Swaps", "Tiempo(ns)", "n");
        System.out.println("---------------------------------------------------------------");
    }

    public static void printRow(String algoritmo, SortMetrics m, int n) {
        System.out.printf("%-12s %-12d %-12d %-12d %-12d%n",
                algoritmo, m.comparisons, m.swaps, m.timeNs, n);
    }

    public static void printFooter() {
        System.out.println("===============================================================");
    }
}
