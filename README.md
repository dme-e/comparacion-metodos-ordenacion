# Comparación de métodos de ordenación en Java

-------
Proyecto de comparación de algoritmos de ordenación (Burbuja, Selección, Inserción). Su objetivo es medir y comparar rendimiento (tiempos), número de comparaciones y número de intercambios sobre distintos datasets de ejemplo.

## Descripción general

----------------------
Este proyecto se encarga de:
- Ejecuta implementaciones de algoritmos de ordenación sobre datasets CSV.
- Mide métricas: comparaciones, intercambios (swaps) y tiempo de ejecución.
- Genera una recomendación simple sobre qué algoritmo es más adecuado según las métricas.

Algoritmos implementados
- Burbuja (BubbleSort)
- Selección (SelectionSort)
- Inserción (InsertionSort)

Qué compara el proyecto
- Tiempo de ejecución (nanosegundos)
- Número de comparaciones realizadas
- Número de intercambios (swaps)

## Objetivo de la práctica

--------------------------
- Analizar y comparar métodos de ordenación clásicos usando datasets con distintas características.
- Instrumentar los algoritmos para contar comparaciones e intercambios.
- Medir tiempo con precisión y usar repeticiones y mediana para reducir ruido.
- Evaluar comportamiento en entradas casi ordenadas, inversas y aleatorias.

## Estructura del proyecto

---------------------------
Paquete principal sugerido: edu.unl.cc (ya usado en el proyecto).

- src/main/java/edu/unl/cc/algorithms/
  - BubbleSort.java
  - SelectionSort.java
  - InsertionSort.java
  - SortAlgorithm.java (interfaz base)

- src/main/java/edu/unl/cc/instrumentation/
  - SortMetrics.java (estructura para métricas)
  - Benchmark.java (ejecuta R repeticiones y devuelve métricas representativas)

- src/main/java/edu/unl/cc/dataset/
  - CSVloader.java (carga CSV a objetos)

- src/main/java/edu/unl/cc/model/
  - Citas.java
  - Inventario.java
  - Pacientes.java

- src/main/java/edu/unl/cc/analysis/
  - Recomendation.java (genera recomendaciones simples basadas en métricas)

- src/main/java/edu/unl/cc/run/
  - Main.java (interfaz por consola / menú de ejecución)

## Datasets utilizados

----------------------
Los datasets de ejemplo incluidos en el proyecto se encuentran en `src/main/resources/` y son:
- `citas_100.csv` — 100 registros (formato: campos relacionados con citas; usado para probar tamaños pequeños).
- `citas_100_casi_ordenado.csv` — mismo esquema que `citas_100.csv` pero casi ordenado para testar rendimiento de Insertion.
- `pacientes_500.csv` — 500 registros de pacientes (ordenación por apellido u otros campos de texto).
- `inventario_500_inverso.csv` — 500 registros ordenados inversamente (caso worst-case para algunos algoritmos).

Formato y origen
- Cada CSV tiene una cabecera y filas separadas por comas. Las clases en `model` (Citas/Inventario/Pacientes) implementan un método `fromCSV(String)` para parsear cada fila.
- Los datasets pueden ser generados manualmente o por scripts; en este repositorio se incluyen ejemplos estáticos en `src/main/resources/`.

## Ejecución del proyecto

-------------------------
Requisitos previos
- JDK 17+ (o la versión compatible con el proyecto; revisar `pom.xml`).
- Maven instalado (opcional, para compilar/ejecutar desde línea de comandos).
- IDE recomendado: IntelliJ IDEA o VS Code con extensión Java.

Ejecutar desde IntelliJ/VS Code
- Abrir el proyecto como proyecto Maven.
- Importar dependencias si el IDE lo solicita.
- Ejecutar la clase `edu.unl.cc.run.Main` (Run > Main.java).

Ejecutar desde línea de comandos (Maven)
- Compilar:

```
mvn clean package
```

- Ejecutar (usando el classpath generado):

```
java -cp target/classes;target/dependency/* edu.unl.cc.run.Main
```

(NOTA: en Windows el separador de classpath es `;`. Si usas el plugin `exec-maven-plugin` puedes ejecutar `mvn exec:java -Dexec.mainClass="edu.unl.cc.run.Main"`.)

Uso del menú de ordenación
- Al iniciar `Main`, aparece un menú que permite seleccionar el dataset a cargar.
- Selecciona la opción numérica y el programa cargará el CSV, ejecutará las comparativas y mostrará la tabla `RESULTADOS` con métricas.

Generar datasets
- Actualmente los datasets incluidos son estáticos en `src/main/resources/`.
- Para generar nuevos datasets, crear un CSV con la misma estructura y colocarlo en `src/main/resources/` o modificar `CSVloader`/añadir utilidades de generación.

## Metodología de medición

--------------------------
- Contadores: Cada implementación de ordenamiento actualiza un objeto `SortMetrics` con campos `comparisons` y `swaps` durante la ejecución.
- Tiempo: Se mide con `System.nanoTime()` justo antes y después de la llamada al método `sort`.
- Repeticiones: Se ejecuta `R` repeticiones (por defecto R = 10) y se toma la mediana de los tiempos para reducir el efecto de outliers.
- Exclusión de I/O: La medición de tiempo se centra únicamente en la operación de ordenación; la lectura del fichero y el parseo no se incluyen en la medición.

## Resultados obtenidos

-----------------------
- El programa imprime una tabla `RESULTADOS` con columnas: ALGORITMO | COMPARACIONES | SWAPS | TIEMPO(ns).
- Para cada dataset se pueden comparar las filas y observar cuál algoritmo alcanza menor tiempo y/o menos intercambios.
- (Opcional) Exportar las métricas a CSV o generar gráficos con herramientas externas (Excel, Python/matplotlib) para visualizar comparaciones.

Ejemplo de análisis breve:
- En `citas_100_casi_ordenado.csv` normalmente Insertion presenta menos comparaciones y menor tiempo.
- En `inventario_500_inverso.csv` los algoritmos O(n^2) presentan tiempos mayores; Burbuja tiende a muchos swaps.

## Requisitos del sistema

-------------------------
- Java JDK 17+ (recomendado)
- IDE: IntelliJ IDEA o Visual Studio Code (extensión Java)
- Git (para control de versiones)
- Maven (opcional, para construir y ejecutar)

## Autores

--------------------------
- Doménica Rojas
- Richard Chamba
- Docente: Ing. Andrés Roberto Navas Castellanos



