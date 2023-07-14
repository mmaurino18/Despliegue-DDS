package dominio.entidades;

public class Estacion extends Establecimiento {

    private String nombre;

    public Estacion(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getnombre() {
        return nombre;
    }
}
