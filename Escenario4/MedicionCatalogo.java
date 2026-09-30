package Escenario4;

public class MedicionCatalogo {

    public static void main(String[] args) {

        int[] tamanos = { 100, 1000, 10000, 100000 };

        Runtime rt = Runtime.getRuntime();

        for (int n : tamanos) {

            System.gc();

            long memAntes = rt.totalMemory() - rt.freeMemory();

            long inicio = System.nanoTime();

            CatalogoProductos catalogo = new CatalogoProductos();

            for (int i = 0; i < n; i++) {

                catalogo.insertar(
                        new Producto(
                                i,
                                "Producto" + i,
                                1000 + (i % 5000),
                                10));
            }

            long fin = System.nanoTime();

            long memDespues = rt.totalMemory() - rt.freeMemory();

            System.out.println(
                    "N=" + n
                            + " | tiempo: "
                            + (fin - inicio) / 1_000_000.0
                            + " ms"
                            + " | memoria: "
                            + (memDespues - memAntes) / 1024
                            + " KB"
                            + " | productos: "
                            + catalogo.total());

            System.out.println(
                    "Ejemplo: " + catalogo.buscarPorId(123));
        }
    }
}