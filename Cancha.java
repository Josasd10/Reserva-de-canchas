package controlador;

public class Cancha {
public class Cancha {

    private String tipo;          
    private int numero;          
    private boolean disponibilidad;
    private int capacidad;        
    private double costoHora;    

    public Cancha() { }

    public Cancha(String tipo, int numero, boolean disponibilidad, int capacidad, double costoHora) {
        this.tipo = tipo;
        this.numero = numero;
        this.disponibilidad = disponibilidad;
        this.capacidad = capacidad;
        this.costoHora = costoHora;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public int getNumero() { return numero; }
    public void setNumero(int numero) { this.numero = numero; }

    public boolean isDisponible() { return disponibilidad; }
    public void setDisponibilidad(boolean disponibilidad) { this.disponibilidad = disponibilidad; }

    public int getCapacidad() { return capacidad; }
    public void setCapacidad(int capacidad) { this.capacidad = capacidad; }

    public double getCostoHora() { return costoHora; }
    public void setCostoHora(double costoHora) { this.costoHora = costoHora; }

    @Override
    public String toString() {
        return "Cancha{#" + numero + ", tipo='" + tipo + "', cap=" + capacidad +
               ", costoHora=" + costoHora + ", operativa=" + disponibilidad + "}";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Cancha cancha = (Cancha) obj;
        return numero == cancha.numero;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(numero);
    }
}


}

