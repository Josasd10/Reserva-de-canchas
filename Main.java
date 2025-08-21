package controlador;

public class Main {
    public static void main(String[] args) {
        Controlador controlador = new Controlador();
        Vista vista = new Vista();

    inicializarCanchas(controlador);

    boolean salir = false;
    while (!salir) {
        int opcion = vista.mostrarMenu();
        switch (opcion) {
            case 1:
                Reserva nueva = vista.solicitarDatosReserva();
                boolean asignada = controlador.procesarReserva(nueva);
                if (asignada) {
                    vista.mostrarMensaje("Reserva asignada exitosamente.");
                } else {
                    vista.mostrarMensaje("Reserva en lista de espera.");
                }
                break;

            case 2:
                vista.mostrarTodasLasReservas(controlador.getReservas());
                break;

            case 3:
                vista.mostrarMensaje("Ingrese el número de índice de la reserva a cancelar:");
                for (int i = 0; i < controlador.getReservas().size(); i++) {
                    System.out.println(i + " -> " + controlador.getReservas().get(i));
                }
                int indice = new java.util.Scanner(System.in).nextInt();
                if (indice >= 0 && indice < controlador.getReservas().size()) {
                    boolean cancelada = controlador.cancelarReserva(controlador.getReservas().get(indice));
                    vista.mostrarMensaje(cancelada ? "Reserva cancelada." : "No se pudo cancelar.");
                }
                break;

            case 4:
                vista.mostrarMensaje("Cancha más demandada: " + controlador.canchaMasDemandada());
                break;

            case 0:
                salir = true;
                vista.mostrarMensaje("¡Gracias por usar el sistema!");
                break;

            default:
                vista.mostrarMensaje("Opción no válida.");
                break;
        }
    }
    
    }

private static void inicializarCanchas(Controlador controlador) {
    controlador.agregarCancha(new Cancha("Fútbol", 1, true, 22, 150.0));
    controlador.agregarCancha(new Cancha("Baloncesto", 2, true, 10, 100.0));
    controlador.agregarCancha(new Cancha("Tenis", 3, true, 4, 80.0));
        controlador.agregarCancha(new Cancha("Fútbol", 4, true, 22, 160.0));
    }
    }
