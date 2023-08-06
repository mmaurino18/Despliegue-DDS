package dominio.entidades;

public abstract class Establecimiento {

    private Double coordenadax;
    private Double coordenaday;

    public static double getCoordenadaX() {
        return 0;
    }

    public static double getCoordenaY() {
        return 0;
    }

    public abstract String  getnombre();
    //private String nombre;

}
