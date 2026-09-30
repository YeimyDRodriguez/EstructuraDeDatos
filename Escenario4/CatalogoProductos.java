package Escenario4;

import java.util.HashMap;
import java.util.TreeSet;

public class CatalogoProductos {

    private HashMap<Integer, Producto> porId = new HashMap<>();

    private TreeSet<Producto> porPrecio = new TreeSet<>();

    public void insertar(Producto p) {
        porId.put(p.getIdProducto(), p);
        porPrecio.add(p);
    }

    public Producto buscarPorId(int id) {
        return porId.get(id);
    }

    public int recorrerOrdenadoPorPrecio() {
        int contador = 0;

        for (Producto p : porPrecio) {
            contador++;
        }

        return contador;
    }

    public int total() {
        return porId.size();
    }
}