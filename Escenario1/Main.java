public class Main {

    public static void main(String[] args) {

        RegistroPacientes registro = new RegistroPacientes();

        System.out.println("\n--- MEDICIÓN DE RENDIMIENTO ---");

        int[] cantidades = { 100, 1000, 10000, 100000 };

        for (int cantidad : cantidades) {

            RegistroPacientes registroPrueba = new RegistroPacientes();

            // Medir memoria inicial
            Runtime runtime = Runtime.getRuntime();

            runtime.gc();

            long memoriaInicial = runtime.totalMemory() - runtime.freeMemory();

            // Iniciar medición de tiempo
            long inicio = System.nanoTime();

            // Registrar pacientes
            for (int i = 1; i <= cantidad; i++) {

                Paciente paciente = new Paciente(
                        "Paciente " + i,
                        20 + (i % 60),
                        String.valueOf(i),
                        1 + (i % 5));

                registroPrueba.registrarPaciente(paciente);
            }

            // Finalizar medición de tiempo
            long fin = System.nanoTime();

            // Medir memoria final
            long memoriaFinal = runtime.totalMemory() - runtime.freeMemory();

            long tiempo = fin - inicio;

            long memoriaUsada = memoriaFinal - memoriaInicial;

            System.out.println("\nCantidad de pacientes: " + cantidad);
            System.out.println("Tiempo de ejecución: " + tiempo + " ns");
            System.out.println("Memoria aproximada: " + memoriaUsada + " bytes");
        }

        // Crear pacientes
        Paciente paciente1 = new Paciente(
                "Juan Jose Herrera",
                24,
                "1001",
                2);

        Paciente paciente2 = new Paciente(
                "Estefania Montoya",
                25,
                "1002",
                5);

        Paciente paciente3 = new Paciente(
                "Yeimy Rodriguez",
                19,
                "1003",
                3);

        Paciente paciente4 = new Paciente(
                "Florerio Cleotelio",
                30,
                "1004",
                1);

        // Registrar pacientes
        registro.registrarPaciente(paciente1);
        registro.registrarPaciente(paciente2);
        registro.registrarPaciente(paciente3);
        registro.registrarPaciente(paciente4);

        // Intentar registrar un paciente duplicado
        System.out.println("¿Paciente duplicado registrado?");

        boolean registrado = registro.registrarPaciente(
                new Paciente(
                        "Carlos",
                        35,
                        "1001",
                        2));

        System.out.println(registrado);

        // Buscar paciente por documento
        System.out.println("\n--- BUSCAR PACIENTE ---");

        Paciente pacienteEncontrado = registro.buscarPaciente("1003");

        if (pacienteEncontrado != null) {
            System.out.println("Paciente encontrado:");
            System.out.println(pacienteEncontrado);
        } else {
            System.out.println("Paciente no encontrado.");
        }

        // Mostrar pacientes en orden de llegada
        System.out.println("\n--- ORDEN DE LLEGADA ---");

        registro.mostrarOrdenLlegada();

        // Mostrar pacientes por prioridad
        System.out.println("\n--- PRIORIDAD ---");

        registro.mostrarPrioridad();
    }
}
