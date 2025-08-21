//Vista

import modelo.Reserva;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Vista {

    private final Scanner scanner = new Scanner(System.in);

    // ----------- Mostrar menú principal -----------
    public int mostrarMenu() {
        System.out.println("\n--- SISTEMA DE RESERVAS ---");
        System.out.println("1. Registrar nueva reserva");
        System.out.println("2. Mostrar todas las reservas");
        System.out.println("3. Cancelar una reserva");
        System.out.println("4. Ver cancha más demandada");
        System.out.println("0. Salir");
        System.out.print("Seleccione opción: ");
        while (!scanner.hasNextInt()) {
            System.out.print("Por favor, ingrese un número: ");
            scanner.next();
        }
        int opcion = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer después de nextInt
        return opcion;
    }

    // ----------- Solicitar datos de reserva -----------
    public Reserva solicitarDatosReserva() {
        System.out.print("Responsable: ");
        String responsable = scanner.nextLine();

        System.out.print("Nombre del evento: ");
        String evento = scanner.nextLine();

        System.out.print("Tipo de evento (Entrenamiento/Campeonato): ");
        String tipo = scanner.nextLine();

        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Fecha (YYYY-MM-DD): ");
            String fechaStr = scanner.nextLine();
            try {
                fecha = LocalDate.parse(fechaStr);
            } catch (Exception e) {
                System.out.println("Formato de fecha inválido.");
            }
        }

        LocalTime inicio = null;
        while (inicio == null) {
            System.out.print("Hora inicio (HH:MM): ");
            String inicioStr = scanner.nextLine();
            try {
                inicio = LocalTime.parse(inicioStr);
            } catch (Exception e) {
                System.out.println("Formato de hora inválido.");
            }
        }

        LocalTime fin = null;
        while (fin == null) {
            System.out.print("Hora fin (HH:MM): ");
            String finStr = scanner.nextLine();
            try {
                fin = LocalTime.parse(finStr);
            } catch (Exception e) {
                System.out.println("Formato de hora inválido.");
            }
        }

        int jugadores = -1;
        while (jugadores < 0) {
            System.out.print("Jugadores estimados: ");
            if (scanner.hasNextInt()) {
                jugadores = scanner.nextInt();
                scanner.nextLine(); // Limpiar buffer
            } else {
                System.out.println("Ingrese un número válido.");
                scanner.next();
            }
        }

        Boolean deposito = null;
        while (deposito == null) {
            System.out.print("¿Depósito pagado? (true/false): ");
            String dep = scanner.nextLine();
            if (dep.equalsIgnoreCase("true") || dep.equalsIgnoreCase("false")) {
                deposito = Boolean.parseBoolean(dep);
            } else {
                System.out.println("Ingrese 'true' o 'false'.");
            }
        }

        return new Reserva(responsable, evento, tipo, fecha, inicio, fin, jugadores, deposito);
    }

    // ----------- Mostrar mensajes -----------
    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarReserva(String info) {
        System.out.println(info);
    }

    public void mostrarTodasLasReservas(java.util.List<Reserva> reservas) {
        if (reservas.isEmpty()) {
            System.out.println("No hay reservas registradas.");
        } else {
            reservas.forEach(r -> System.out.println(r.toString()));
        }
    }
}