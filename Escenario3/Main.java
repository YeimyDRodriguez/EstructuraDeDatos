package Escenario3;

public class Main {

    public static void main(String[] args) {

        PlataformaTaxis plataforma = new PlataformaTaxis();

        System.out.println("   MEDICIÓN DE RENDIMIENTO   ");

        int[] cantidades = { 100, 1000, 10000, 100000 };

        for (int cantidad : cantidades) {

            PlataformaTaxis prueba = new PlataformaTaxis();

            Runtime runtime = Runtime.getRuntime();

            runtime.gc();

            long memoriaInicial = runtime.totalMemory() - runtime.freeMemory();

            long inicio = System.nanoTime();

            // Registrar solicitudes
            for (int i = 1; i <= cantidad; i++) {

                SolicitudTaxi solicitud = new SolicitudTaxi( i,"Cliente " + i, "Origen " + i, "Destino " + i);

                prueba.registrarSolicitud(solicitud);
            }

            long fin = System.nanoTime();

            long memoriaFinal = runtime.totalMemory() - runtime.freeMemory();

            long tiempo = fin - inicio;

            long memoriaUsada = memoriaFinal - memoriaInicial;

            System.out.println( "\nCantidad de solicitudes: " + cantidad);

            System.out.println( "Tiempo de ejecución: " + tiempo + " ns");

            System.out.println( "Memoria aproximada: " + memoriaUsada + " bytes");
        }

        // Registrar solicitudes
        plataforma.registrarSolicitud( new SolicitudTaxi(1,"Yeimy","Génova","Armenia"));

        plataforma.registrarSolicitud(new SolicitudTaxi(2,"Juan","Barrio Centro","Universidad del Quindío"));

        plataforma.registrarSolicitud(new SolicitudTaxi(3,"Estefania","La Paz","Hospital"));

        plataforma.registrarSolicitud(new SolicitudTaxi(4,"Florerio","El Recreo","Terminal"));

        System.out.println("\n    SOLICITUDES PENDIENTES   ");

        plataforma.mostrarSolicitudesPendientes();

        // Cancelar solicitud 2
        System.out.println("\n    CANCELAR SOLICITUD 2    ");

        boolean cancelada = plataforma.cancelarSolicitud(2);

        if (cancelada) {
            System.out.println(
                    "Solicitud 2 cancelada correctamente.");
        } else {
            System.out.println(
                    "No se encontró la solicitud.");
        }

        // Mostrar pendientes después de cancelar

        System.out.println("\n   SOLICITUDES PENDIENTES   ");

        plataforma.mostrarSolicitudesPendientes();

        // Atender solicitud más antigua

        System.out.println("\n    ATENDER SOLICITUD MÁS ANTIGUA ");

        SolicitudTaxi atendida = plataforma.atenderSolicitud();

        if (atendida != null) {
            System.out.println( "Solicitud atendida:");

            System.out.println(atendida);
        } else {
            System.out.println("No hay solicitudes pendientes.");
        }

        // Mostrar nuevamente las pendientes
        System.out.println("\n   SOLICITUDES PENDIENTES   ");

        plataforma.mostrarSolicitudesPendientes();
    }

}