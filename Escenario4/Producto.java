package Escenario4;

public class Producto implements Comparable<Producto> {

    private int idProducto;
    private String nombre;
    private int precio;
    private int inventario;

    public Producto(int idProducto, String nombre, int precio, int inventario) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.precio = precio;
        this.inventario = inventario;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public int getInventario() {
        return inventario;
    }

    @Override
    public int compareTo(Producto otro) {

        int cmp = Integer.compare(this.precio, otro.precio);

        if (cmp != 0) {
            return cmp;
        }

        return Integer.compare(this.idProducto, otro.idProducto);
    }

    @Override
    public String toString() {
        return "Producto{id=" + idProducto
                + ", " + nombre
                + ", $" + precio
                + ", stock=" + inventario + "}";
    }
}