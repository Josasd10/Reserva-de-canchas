package controlador;

public class Encargado {

 
    private String nombre;
    private String puesto;
    private int id;

   
    public Encargado() { }

    public Encargado(String nombre, String puesto, int id) {
        this.nombre = nombre;
        this.puesto = puesto;
        this.id = id;
    }

    
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getPuesto() { return puesto; }
    public void setPuesto(String puesto) { this.puesto = puesto; }

    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    
    @Override
    public String toString() {
        return "Encargado{id=" + id + ", nombre='" + nombre + "', puesto='" + puesto + "'}";
    }
}

