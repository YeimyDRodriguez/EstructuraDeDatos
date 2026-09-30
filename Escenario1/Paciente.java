public class Paciente {
    private String nombre; 
    private int edad; 
    private String numeroDocumento;
    private int gravedad; 

    public Paciente(String nombre, int edad, String numeroDocumento, int gravedad) {
        this.nombre = nombre;
        this.edad = edad;
        this.numeroDocumento = numeroDocumento;
        this.gravedad = gravedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getGravedad() {
        return gravedad;
    }

    public void setGravedad(int gravedad) {
        this.gravedad = gravedad;
    }

    public String getNumeroDocumento() {
        return numeroDocumento;
    }

    public void setNumeroDocumento(String numeroDocumento) {
        this.numeroDocumento = numeroDocumento;
    }

    public String toString() {
        return "Paciente{" +
                "nombre='" + nombre + '\'' +
                ", edad=" + edad +
                ", gravedad=" + gravedad +
                '}';
    }
}
