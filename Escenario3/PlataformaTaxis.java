package Escenario3;

import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;

public class PlataformaTaxis {

    private LinkedHashMap<Integer, SolicitudTaxi> solicitudes;

    private ArrayDeque<Integer> colaSolicitudes;

    private HashMap<Integer, SolicitudTaxi> solicitudesPorId;

    private HashSet<Integer> canceladas;

    public PlataformaTaxis() {

        solicitudes = new LinkedHashMap<>();

        colaSolicitudes = new ArrayDeque<>();

        solicitudesPorId = new HashMap<>();

        canceladas = new HashSet<>();
    }

    // Registrar solicitud
    public boolean registrarSolicitud(SolicitudTaxi solicitud) {

        if (solicitudesPorId.containsKey(solicitud.getId())) {
            return false;
        }

        solicitudesPorId.put(solicitud.getId(), solicitud);

        solicitudes.put(solicitud.getId(),solicitud);

        colaSolicitudes.addLast(solicitud.getId());

        return true;
    }

    // Atender solicitud más antigua
    public SolicitudTaxi atenderSolicitud() {

        while (!colaSolicitudes.isEmpty()) {

            Integer id = colaSolicitudes.pollFirst();

            // Si fue cancelada, se ignora
            if (canceladas.contains(id)) {
                continue;
            }

            SolicitudTaxi solicitud =
                    solicitudesPorId.get(id);

            if (solicitud != null) {

                solicitudes.remove(id);

                solicitudesPorId.remove(id);

                return solicitud;
            }
        }

        return null;
    }

    // Cancelar solicitud
    public boolean cancelarSolicitud(int id) {

        if (!solicitudesPorId.containsKey(id)) {
            return false;
        }

        canceladas.add(id);

        solicitudes.remove(id);

        solicitudesPorId.remove(id);

        return true;
    }

    // Mostrar solicitudes pendientes
    public void mostrarSolicitudesPendientes() {

        for (Integer id : colaSolicitudes) {

            if (!canceladas.contains(id)) {

                SolicitudTaxi solicitud = solicitudesPorId.get(id);

                if (solicitud != null) {
                    System.out.println(solicitud);
                }
            }
        }
    }

    // Cantidad de solicitudes pendientes
    public int cantidadSolicitudesPendientes() {
        return solicitudesPorId.size();
    }
}
