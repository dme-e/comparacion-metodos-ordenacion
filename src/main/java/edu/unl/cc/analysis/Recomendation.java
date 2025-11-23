package edu.unl.cc.analysis;

import edu.unl.cc.instrumentation.SortMetrics;

public class Recomendation {

    //Dice recomendaciones cual es mejor usar
    public static void generate (Object[] datos, SortMetrics burbuja, SortMetrics insercion, SortMetrics seleccion) {

        int n = datos.length;

        System.out.println("\n-----------------------------------------------------");
        System.out.println("                MATRIZ DE RECOMENDACIÓN");
        System.out.println("-----------------------------------------------------");


        // ----------  Datos casi ordenados ----------
        if (esCasiOrdenado(burbuja.comparisons, insercion.comparisons)) {
            System.out.println("** Datos parecen casi ordenados → Inserción es la mejor opción.");
        }

        // ---------- Minimizar swaps ----------
        if (seleccion.swaps < burbuja.swaps && seleccion.swaps < insercion.swaps) {
            System.out.println("** Si necesitas minimizar intercambios → Selección.");
        }

        // ---------- Minimizar comparaciones ----------
        if (insercion.comparisons < burbuja.comparisons &&
                insercion.comparisons < seleccion.comparisons) {

            System.out.println("** Inserción realizó menos comparaciones → eficiente para este dataset.");
        }

        // ---------- Dataset grande ----------
        if (n > 500) {
            System.out.println("** n es grande → Ninguno es ideal (usar Merge/Quick), pero Selección es más predecible.");
        }

        // ---------- Totalmente inverso ----------
        if (burbuja.swaps > (n * n) / 4) {
            System.out.println("** Parece inverso → Burbuja rinde mal; Selección puede ser más estable.");
        }

        System.out.println("-----------------------------------------------------\n");
    }

    // Detección simple de datos casi ordenados
    private static boolean esCasiOrdenado(long compBurbuja, long compInsercion) {
        // Si InsertionSort hace varias comparaciones menos que el BubbleSort
        return compInsercion < (compBurbuja / 3);
    }
}
