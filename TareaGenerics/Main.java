
public class Main {
    public static void main(String[] args) {

        Carrito<Producto> carrito = new Carrito<>();

        Producto p1 = new Producto("Portátil", 2500000);
        Producto p2 = new Producto("Mouse", 80000);
        Producto p3 = new Producto("Teclado", 150000);

        // Agregar productos
        carrito.agregarProducto(p1);
        carrito.agregarProducto(p2);
        carrito.agregarProducto(p3);

        // Mostrar productos
        System.out.println("\nProductos en el carrito:");
        carrito.mostrarProductos();

        // Producto de mayor precio
        Producto mayor = carrito.obtenerProductoMayorPrecio();

        if (mayor != null) {
            System.out.println("\nProducto más costoso:");
            System.out.println(mayor);
        }

        // Precio total
        System.out.println("\nTotal de la compra: $" + carrito.calcularTotal());
    }
}