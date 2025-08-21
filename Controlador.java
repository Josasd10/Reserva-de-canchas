package controlador;
import java.util.ArrayList;
import java.util.List;
import java.util.Iterator;

public class Controlador {

    private final List<Cancha> canchas = new ArrayList<>();
    private final List<Reserva> reservas = new ArrayList<>();
    private final List<Reserva> listaEspera = new ArrayList<>();
    private final List<Encargado> encargados = new ArrayList<>();

   
    public void agregarCancha(Cancha cancha) {
        if (cancha != null) canchas.add(cancha);
    }

    public void agregarEncargado(Encargado encargado) {
        if (encargado != null) encargados.add(encargado);
    }

    public List<Cancha> getCanchas() { return canchas; }
    public List<Reserva> getReservas() { return reservas; }
    public List<Reserva> getListaEspera() { return listaEspera; }
    public List<Encargado> getEncargados() { return encargados; }

    
    public boolean procesarReserva(Reserva reserva) {
        if (reserva == null || !reserva.horarioValido() || !reserva.depositoValido()) {
            return false;
        }

        for (Cancha cancha : canchas) {
            if (!cancha.isDisponible()) continue;
            if (!reserva.respetaCapacidad(cancha)) continue;

            boolean conflicto = false;
            for (Reserva r : reservas) {
                if (cancha.equals(r.getCanchaAsignada()) && !r.isCancelada() && reserva.traslapaCon(r)) {
                    conflicto = true;
                    break;
                }
            }

            if (!conflicto) {
                reserva.setCanchaAsignada(cancha);
                double costo = reserva.calcularCosto(cancha);
                reserva.setCostoTotal(costo);
                reservas.add(reserva);
                return true;
            }
        }

        listaEspera.add(reserva);
        return false;
    }

  
    public boolean cancelarReserva(Reserva reserva) {
        if (reserva == null || reserva.isCancelada()) return false;

        reserva.setCancelada(true);

     
        Iterator<Reserva> it = listaEspera.iterator();
        while (it.hasNext()) {
            Reserva espera = it.next();
            boolean asignada = procesarReserva(espera);
            if (asignada) {
                it.remove();
                break;
            }
        }

        return true;
    }

    
    public String canchaMasDemandada() {
        if (reservas.isEmpty()) return "No hay reservas";
        Cancha masPopular = null;
        int max = 0;
        for (Cancha cancha : canchas) {
            int count = (int) reservas.stream()
                    .filter(r -> cancha.equals(r.getCanchaAsignada()))
                    .count();
            if (count > max) {
                max = count;
                masPopular = cancha;
            }
        }
        return masPopular != null ? masPopular.toString() : "No hay reservas asignadas";
    }
}
