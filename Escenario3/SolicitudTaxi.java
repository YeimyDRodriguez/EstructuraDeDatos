package Escenario3;

public class SolicitudTaxi {

    private int id;
    private String cliente;
    private String origen;
    private String destino;

    public SolicitudTaxi(int id, String cliente, String origen, String destino) {
        this.id = id;
        this.cliente = cliente;
        this.origen = origen;
        this.destino = destino;
    }

    public int getId() {
        return id;
    }

    public String getCliente() {
        return cliente;
    }

    public String getOrigen() {
        return origen;
    }

    public String getDestino() {
        return destino;
    }

    @Override
    public String toString() {
        return "Solicitud{" +
                "id=" + id +
                ", cliente='" + cliente + '\'' +
                ", origen='" + origen + '\'' +
                ", destino='" + destino + '\'' +
                '}';
    }
}
