package Escenario2;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

public class Escenario2 {

    private static final String[] CATEGORIAS = {
            "Tecnologia",
            "Hogar",
            "Deportes",
            "Libros",
            "Ropa",
            "Belleza",
            "Juguetes",
            "Alimentos",
            "Oficina",
            "Accesorios"
    };

    // Evita que el compilador descarte algunos resultados de las mediciones.
    private static volatile long resultadoMedicion;

    static class Producto {
        private final String codigo;
        private final String nombre;
        private final BigDecimal precio;
        private final String categoria;

        public Producto(
                String codigo,
                String nombre,
                BigDecimal precio,
                String categoria
        ) {
            this.codigo = codigo;
            this.nombre = nombre;
            this.precio = precio;
            this.categoria = categoria;
        }

        public String getCodigo() {
            return codigo;
        }

        public String getNombre() {
            return nombre;
        }

        public BigDecimal getPrecio() {
            return precio;
        }

        public String getCategoria() {
            return categoria;
        }

        @Override
        public String toString() {
            return codigo + " | " + nombre + " | $" + precio + " | " + categoria;
        }
    }

    static class Catalogo {

        // Búsqueda rápida por código.
        private final Map<String, Producto> productosPorCodigo =
                new HashMap<>();

        // Productos ordenados por precio. Se permiten precios repetidos.
        private final TreeMap<BigDecimal, List<Producto>> productosPorPrecio =
                new TreeMap<>();

        // Índice de categorías: categoría -> códigos de productos.
        private final Map<String, Set<String>> codigosPorCategoria =
                new HashMap<>();

        // Los productos recién agregados quedan al inicio.
        private final LinkedList<Producto> productosMasNuevosPrimero =
                new LinkedList<>();

        public boolean agregarProducto(Producto producto) {
            // Rechaza códigos repetidos.
            if (productosPorCodigo.containsKey(producto.getCodigo())) {
                return false;
            }

            productosPorCodigo.put(producto.getCodigo(), producto);

            productosPorPrecio
                    .computeIfAbsent(
                            producto.getPrecio(),
                            precio -> new LinkedList<>()
                    )
                    .add(producto);

            codigosPorCategoria
                    .computeIfAbsent(
                            producto.getCategoria(),
                            categoria -> new HashSet<>()
                    )
                    .add(producto.getCodigo());

            productosMasNuevosPrimero.addFirst(producto);

            return true;
        }

        public Producto buscarPorCodigo(String codigo) {
            return productosPorCodigo.get(codigo);
        }

        public List<Producto> filtrarPorCategoria(String categoria) {
            List<Producto> resultado = new ArrayList<>();
            Set<String> codigos = codigosPorCategoria.get(categoria);

            if (codigos == null) {
                return resultado;
            }

            for (String codigo : codigos) {
                Producto producto = productosPorCodigo.get(codigo);

                if (producto != null) {
                    resultado.add(producto);
                }
            }

            return resultado;
        }

        public void mostrarOrdenadosPorPrecio() {
            for (Map.Entry<BigDecimal, List<Producto>> entrada
                    : productosPorPrecio.entrySet()) {

                for (Producto producto : entrada.getValue()) {
                    System.out.println(producto);
                }
            }
        }

        public Producto obtenerProductoMasNuevo() {
            return productosMasNuevosPrimero.peekFirst();
        }

        public int cantidadProductos() {
            return productosPorCodigo.size();
        }
    }

    private static Producto crearProductoDePrueba(int numero) {
        long precioEnCentavos =
                1000L + ((long) numero * 7919L) % 500000L;

        BigDecimal precio =
                BigDecimal.valueOf(precioEnCentavos, 2);

        String categoria =
                CATEGORIAS[numero % CATEGORIAS.length];

        return new Producto(
                "P" + numero,
                "Producto " + numero,
                precio,
                categoria
        );
    }

    private static long memoriaUsada() {
        Runtime runtime = Runtime.getRuntime();
        return runtime.totalMemory() - runtime.freeMemory();
    }

    private static String formatearMegabytes(long bytes) {
        return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
    }

    private static void medir(int cantidadProductos) {
        Catalogo catalogo = new Catalogo();

        System.gc();
        long memoriaAntes = memoriaUsada();

        long inicioCarga = System.nanoTime();

        for (int i = 0; i < cantidadProductos; i++) {
            catalogo.agregarProducto(crearProductoDePrueba(i));
        }

        long tiempoCarga = System.nanoTime() - inicioCarga;

        System.gc();
        long memoriaDespues = memoriaUsada();
        long memoriaRetenida =
                Math.max(0, memoriaDespues - memoriaAntes);

        // Medición de búsqueda por código.
        int consultas = Math.max(
                10_000,
                Math.min(200_000, cantidadProductos * 2)
        );

        Random random = new Random(42);
        long encontrados = 0;

        long inicioBusqueda = System.nanoTime();

        for (int i = 0; i < consultas; i++) {
            int numero = random.nextInt(cantidadProductos);
            Producto producto =
                    catalogo.buscarPorCodigo("P" + numero);

            if (producto != null) {
                encontrados++;
            }
        }

        long tiempoBusqueda = System.nanoTime() - inicioBusqueda;
        resultadoMedicion = encontrados;

        // Medición del recorrido en orden de precio.
        long inicioOrden = System.nanoTime();
        long totalOrdenado = 0;

        for (Map.Entry<BigDecimal, List<Producto>> entrada
                : catalogo.productosPorPrecio.entrySet()) {
            totalOrdenado += entrada.getValue().size();
        }

        long tiempoOrden = System.nanoTime() - inicioOrden;
        resultadoMedicion = totalOrdenado;

        // Medición del filtro por categoría.
        long inicioFiltro = System.nanoTime();

        List<Producto> resultadoFiltro =
                catalogo.filtrarPorCategoria("Tecnologia");

        long tiempoFiltro = System.nanoTime() - inicioFiltro;
        resultadoMedicion = resultadoFiltro.size();

        System.out.println();
        System.out.println(
                "--- " + cantidadProductos + " productos ---"
        );

        System.out.printf(
                "Carga e indexación: %.3f ms%n",
                tiempoCarga / 1_000_000.0
        );

        System.out.printf(
                "Búsqueda por código: %.1f ns por consulta "
                        + "(%d consultas, %d encontradas)%n",
                (double) tiempoBusqueda / consultas,
                consultas,
                encontrados
        );

        System.out.printf(
                "Recorrido ordenado por precio: %.3f ms "
                        + "(%d productos)%n",
                tiempoOrden / 1_000_000.0,
                totalOrdenado
        );

        System.out.printf(
                "Filtro por categoría: %.3f ms "
                        + "(%d resultados)%n",
                tiempoFiltro / 1_000_000.0,
                resultadoFiltro.size()
        );

        System.out.println(
                "Memoria retenida aproximada: "
                        + formatearMegabytes(memoriaRetenida)
        );

        System.out.println(
                "Total en el catálogo: " + catalogo.cantidadProductos()
        );

        System.out.println(
                "Producto más nuevo: "
                        + catalogo.obtenerProductoMasNuevo()
        );
    }

    public static void main(String[] args) {
        System.out.println(
                "ESCENARIO 2 - PLATAFORMA DE VENTAS MASIVAS"
        );

        System.out.println(
                "Versión de Java: "
                        + System.getProperty("java.version")
        );

        int[] tamaños = {
                100,
                1_000,
                10_000,
                100_000
        };

        for (int tamaño : tamaños) {
            medir(tamaño);
        }

        System.out.println();
        System.out.println(
                "Los tiempos varían según el computador y la JVM."
        );
    }
}