package controlador;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.Duration;

public class Reserva {

    
    private String responsable;   
    private String nombreEvento;   
    private String tipoEvento;      
    private LocalDate fecha;
    private LocalTime horaInicio;
    private LocalTime horaFin;
    private int jugadoresEstimados; 
    private boolean depositoPagado;  
    private boolean cancelada;

    
    private Cancha canchaAsignada;  
    private double costoTotal;      

   
    public static final LocalTime HORA_APERTURA = LocalTime.of(6, 0);
    public static final LocalTime HORA_CIERRE   = LocalTime.of(22, 0);
    public static final int MINUTOS_MINIMO_BLOQUE = 60; // 1 hora

    
    public Reserva() { }

    public Reserva(String responsable, String nombreEvento, String tipoEvento,
                   LocalDate fecha, LocalTime horaInicio, LocalTime horaFin,
                   int jugadoresEstimados, boolean depositoPagado) {
        this.responsable = responsable;
        this.nombreEvento = nombreEvento;
        this.tipoEvento = tipoEvento;
        this.fecha = fecha;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.jugadoresEstimados = jugadoresEstimados;
        this.depositoPagado = depositoPagado;
        this.cancelada = false;
    }

   
    public String getResponsable() { return responsable; }
    public String getNombreEvento() { return nombreEvento; }
    public String getTipoEvento() { return tipoEvento; }
    public LocalDate getFecha() { return fecha; }
    public LocalTime getHoraInicio() { return horaInicio; }
    public LocalTime getHoraFin() { return horaFin; }
    public int getJugadoresEstimados() { return jugadoresEstimados; }
    public boolean isDepositoPagado() { return depositoPagado; }
    public boolean isCancelada() { return cancelada; }
    public Cancha getCanchaAsignada() { return canchaAsignada; }
    public double getCostoTotal() { return costoTotal; }

    public void setCancelada(boolean cancelada) { this.cancelada = cancelada; }
    public void setCanchaAsignada(Cancha canchaAsignada) { this.canchaAsignada = canchaAsignada; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }

   
    public boolean horarioValido() {
        if (horaInicio == null || horaFin == null || fecha == null) return false;
        if (horaInicio.isBefore(HORA_APERTURA) || horaFin.isAfter(HORA_CIERRE)) return false;
        if (!horaFin.isAfter(horaInicio)) return false;
        long min = Duration.between(horaInicio, horaFin).toMinutes();
        return min >= MINUTOS_MINIMO_BLOQUE;
    }

  
    public boolean depositoValido() {
        return depositoPagado;
    }

    public boolean respetaCapacidad(Cancha cancha) {
        if (cancha == null) return false;
        return jugadoresEstimados <= cancha.getCapacidad();
    }

    
    public boolean traslapaCon(Reserva otra) {
        if (otra == null) return false;
        if (!this.fecha.equals(otra.fecha)) return false;
        
        return this.horaInicio.isBefore(otra.horaFin) && otra.horaInicio.isBefore(this.horaFin);
    }

    public double calcularCosto(Cancha cancha) {
        long horas = Math.max(1, Duration.between(horaInicio, horaFin).toHours());
        return cancha.getCostoHora() * horas;
    }

    @Override
    public String toString() {
        String canchaTxt = (canchaAsignada == null) ? "SIN ASIGNAR" :
                canchaAsignada.getTipo() + " #" + canchaAsignada.getNumero();
        return "Reserva{" +
                "evento='" + nombreEvento + '\'' +
                ", responsable='" + responsable + '\'' +
                ", tipo='" + tipoEvento + '\'' +
                ", fecha=" + fecha +
                ", " + horaInicio + "-" + horaFin +
                ", jugadores=" + jugadoresEstimados +
                ", deposito=" + depositoPagado +
                ", cancha=" + canchaTxt +
                ", costo=" + costoTotal +
                ", cancelada=" + cancelada +
                '}';
    }
}


