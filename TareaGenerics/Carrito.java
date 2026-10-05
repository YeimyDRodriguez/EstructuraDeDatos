
import java.util.ArrayList;
import java.util.List;

public class Carrito<T extends Producto> {

    private List<T> productos;

    //Constructor 
    public Carrito() {
        productos = new ArrayList<>();
    }

    
    public void agregarProducto(T producto) {
        productos.add(producto);
        System.out.println("Producto agregado: " + producto.getNombre());
    }


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

    public double calcularTotal() {
        double total = 0;

        for (T producto : productos) {
            total += producto.getPrecio();
        }

        return total;
    }

    public void mostrarProductos() {
        for (T producto : productos) {
            System.out.println(producto);
        }
    }
}