package dominio.entidades;

public class Sucursal extends Establecimiento{

    private String nombre;

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String getnombre() {
        return nombre;
    }
}
