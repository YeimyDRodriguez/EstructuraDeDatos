package IteradorPropio;

public class Main {

    public static void main(String[] args) {

        CarritoGenerico<Producto> carrito = new CarritoGenerico<>();

        // Agregar productos
        carrito.agregarProducto(new Producto("Laptop", 3500000));
        carrito.agregarProducto(new Producto("Mouse", 80000));
        carrito.agregarProducto(new Producto("Teclado", 150000));
        carrito.agregarProducto(new Producto("Audífonos", 250000));

        // Recorrido utilizando nuestro iterador
        System.out.println("PRODUCTOS DEL CARRITO:");

        for (Producto producto : carrito) {
            System.out.println(producto);
        }

        // Producto de mayor precio
        Producto mayor = carrito.obtenerProductoMayorPrecio();

        System.out.println("\nPRODUCTO MÁS CARO:");
        System.out.println(mayor);

        // Precio total
        System.out.println("\nPRECIO TOTAL:");
        System.out.println("$" + carrito.calcularPrecioTotal());
    }
}