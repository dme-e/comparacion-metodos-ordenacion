package edu.unl.cc.run;

import java.util.Comparator;
import java.util.Scanner;

import edu.unl.cc.dataset.CSVloader;
import edu.unl.cc.model.Citas;
import edu.unl.cc.model.Inventario;
import edu.unl.cc.model.Pacientes;

import edu.unl.cc.algorithms.BubbleSort;
import edu.unl.cc.algorithms.InsertionSort;
import edu.unl.cc.algorithms.SelectionSort;
import edu.unl.cc.algorithms.SortAlgorithm;

import edu.unl.cc.instrumentation.Benchmark;
import edu.unl.cc.instrumentation.SortMetrics;

public class Main {

    private static final Scanner sc = new Scanner(System.in);

    public static void iniciar() {

        while (true) {
            System.out.println("\n-----------------------------------------------------");
            System.out.println("             COMPARADOR DE ORDENAMIENTO             ");
            System.out.println("-----------------------------------------------------");
            System.out.println("Seleccione un archivo para cargar:");
            System.out.println("-----------------------------------------------------");
            System.out.println("1. citas_100.csv");
            System.out.println("2. citas_100_casi_ordenado.csv");
            System.out.println("3. inventario_500_inverso.csv");
            System.out.println("4. pacientes_500.csv");
            System.out.println("0. Salir");
            System.out.println("-----------------------------------------------------");
            System.out.print("Opción: ");

            int option = sc.nextInt();
            sc.nextLine();

            String path;

            switch (option) {
                case 1 -> path = "citas_100.csv";
                case 2 -> path = "citas_100_casi_ordenado.csv";
                case 3 -> path = "inventario_500_inverso.csv";
                case 4 -> path = "pacientes_500.csv";
                case 0 -> {
                    System.out.println("Saliendo...");
                    return;
                }
                default -> {
                    System.out.println("Opción inválida");
                    continue;
                }
            }

            System.out.println("\nCargando archivo: " + path);

            try {
                procesarArchivo(path);
                System.out.println("-----------------------------------------------------\n");

            } catch (Exception error) {
                System.out.println("Error: " + error.getMessage());
            }
        }
    }

    /**
     * Carga y procesa un archivo CSV, generando comparativas de rendimiento
     * entre los tres algoritmos de ordenamiento.
     */
    private static void procesarArchivo(String path) throws Exception {

        Object[] datos = cargarDatosDinamico(path);

        if (datos.length == 0) {
            System.out.println("Error: archivo vacío o formato incorrecto.");
            return;
        }

        System.out.println("Archivo cargado correctamente: " + datos.length + " registros.");

        Comparator<Object> comparator = crearComparator(datos[0]);

        ejecutarComparaciones(datos, comparator);
    }

    /**
     * Carga datos del archivo CSV identificando dinámicamente su tipo
     * basándose en el nombre del archivo.
     */
    private static Object[] cargarDatosDinamico(String path) throws Exception {

        if (path.contains("citas"))
            return CSVloader.cargar(path, Citas::fromCSV, Citas[]::new);

        if (path.contains("inventario"))
            return CSVloader.cargar(path, Inventario::fromCSV, Inventario[]::new);

        if (path.contains("pacientes"))
            return CSVloader.cargar(path, Pacientes::fromCSV, Pacientes[]::new);

        throw new RuntimeException("No se reconoce el tipo de archivo.");
    }

    /**
     * Retorna un Comparator específico del tipo de objeto,
     * determinado mediante pattern matching instanceof.
     */
    private static Comparator<Object> crearComparator(Object obj) {

        if (obj instanceof Citas)
            return Comparator.comparing(c -> ((Citas) c).getFechaHora());

        if (obj instanceof Inventario)
            return Comparator.comparing(i -> Integer.parseInt(((Inventario) i).getStock()));

        if (obj instanceof Pacientes)
            return Comparator.comparing(p -> ((Pacientes) p).getApellido());

        throw new RuntimeException("No existe comparator para este tipo.");
    }

    /**
     * Ejecuta Bubble Sort, Insertion Sort y Selection Sort con R iteraciones,
     * registra métricas de desempeño y muestra comparativa de resultados en tabla formateada.
     */
    private static void ejecutarComparaciones(Object[] datos, Comparator<Object> cmp) {

        System.out.println("\n-----------------------------------------------------");
        System.out.println("                EJECUTANDO ALGORITMOS                ");
        SortAlgorithm<Object> bubble = new BubbleSort<>();
        SortAlgorithm<Object> insertion = new InsertionSort<>();
        SortAlgorithm<Object> selection = new SelectionSort<>();

        int R = 10;

        SortMetrics mb = Benchmark.run(bubble, datos, cmp, R);
        SortMetrics mi = Benchmark.run(insertion, datos, cmp, R);
        SortMetrics ms = Benchmark.run(selection, datos, cmp, R);

        System.out.println("-----------------------------------------------------");
        System.out.println("                   RESULTADOS                       ");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-13s %-15s %-10" +
                "s %-15s\n", "ALGORITMO", "COMPARACIONES", "SWAPS", "TIEMPO(ns)");
        System.out.println("-----------------------------------------------------");
        System.out.printf("%-13s %-15d %-10d %-15d\n", "Burbuja", mb.comparisons, mb.swaps, mb.timeNs);
        System.out.printf("%-13s %-15d %-10d %-15d\n", "Inserción", mi.comparisons, mi.swaps, mi.timeNs);
        System.out.printf("%-13s %-15d %-10d %-15d\n", "Selección", ms.comparisons, ms.swaps, ms.timeNs);
    }

    public static void main(String[] args) {
        iniciar();
    }

}
