package dominio.servicios;

public abstract class Establecimiento {
    private String nombre;
    private String ubicacion;
    private List<ServicioPrestado> listaDeServiciosPrestados;

    public Establecimiento(String nombre, String ubicacion, List<ServicioPrestado> listaDeServiciosPrestados) {
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.listaDeServiciosPrestados = listaDeServiciosPrestados;
    }

    // Getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public List<ServicioPrestado> getListaDeServiciosPrestados() {
        return listaDeServiciosPrestados;
    }

    public void setListaDeServiciosPrestados(List<ServicioPrestado> listaDeServiciosPrestados) {
        this.listaDeServiciosPrestados = listaDeServiciosPrestados;
    }
}
