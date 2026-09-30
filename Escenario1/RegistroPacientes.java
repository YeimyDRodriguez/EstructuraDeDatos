import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.PriorityQueue;

public class RegistroPacientes {

    private HashMap<String, Paciente> pacientes;

    private LinkedHashMap<String, Paciente> ordenLlegada;

    private PriorityQueue<Paciente> prioridad;

    public RegistroPacientes() {

        pacientes = new HashMap<>();

        ordenLlegada = new LinkedHashMap<>();

        prioridad = new PriorityQueue<>(
                (paciente1, paciente2) -> Integer.compare(
                        paciente1.getGravedad(),
                        paciente2.getGravedad()));
    }

    public boolean registrarPaciente(Paciente paciente) {

        // Evitar los duplicados del paciente mediante el número de documento
        if (pacientes.containsKey(paciente.getNumeroDocumento())) {
            return false;
        }

        // Registrar el paciente
        pacientes.put(
                paciente.getNumeroDocumento(),
                paciente);

        // Orden de llegada
        ordenLlegada.put(
                paciente.getNumeroDocumento(),
                paciente);

        // Agregar a la cola de prioridad
        prioridad.offer(paciente);

        return true;
    }

    // Buscar paciente
    public Paciente buscarPaciente(String numeroDocumento) {
        return pacientes.get(numeroDocumento);
    }

    public void mostrarOrdenLlegada() {
        for (Paciente paciente : ordenLlegada.values()) {
            System.out.println(paciente);
        }
    }

    public void mostrarPrioridad() {

        PriorityQueue<Paciente> copiaPrioridad = new PriorityQueue<>(prioridad);

        while (!copiaPrioridad.isEmpty()) {

            Paciente paciente = copiaPrioridad.poll();

            System.out.println(paciente);
        }
    }
}
