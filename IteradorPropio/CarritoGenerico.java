package IteradorPropio;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CarritoGenerico<T extends Producto> implements Iterable<T> {

    private List<T> productos;

    public CarritoGenerico() {
        productos = new ArrayList<>();
    }

    // Agregar productos
    public void agregarProducto(T producto) {
        productos.add(producto);
    }

    // Obtener el producto de mayor precio
    public T obtenerProductoMayorPrecio() {

        if (productos.isEmpty()) {
            return null;
        }

        T mayor = productos.get(0);

        for (T producto : productos) {
            if (producto.getPrecio() > mayor.getPrecio()) {
                mayor = producto;
            }
        }

        return mayor;
    }

    // Calcular precio total
    public double calcularPrecioTotal() {

        double total = 0;

        for (T producto : productos) {
            total += producto.getPrecio();
        }

        return total;
    }

    // Iterador propio
    @Override
    public Iterator<T> iterator() {

        return new Iterator<T>() {

            private int posicion = 0;

            @Override
            public boolean hasNext() {
                return posicion < productos.size();
            }

            @Override
            public T next() {
                return productos.get(posicion++);
            }
        };
    }
}