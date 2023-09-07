package dominio.entidades;

import java.util.List;

public abstract class Establecimiento {
    public String nombre;
    //public Localizacion localizacion;
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

    public static Establecimiento encontrarCoordenadaMasCercana(List<Establecimiento> establecimientos, double x, double y) {
        Establecimiento establecimientoMasCercano = null;
        double distanciaMinima = Double.MAX_VALUE;

        for (Establecimiento establecimiento : establecimientos) {
            double x2= Establecimiento.getCoordenadaX();
            double y2 = Establecimiento.getCoordenaY();
            double distancia = calcularDistancia(x, y,x2,y2);
            if (distancia < distanciaMinima) {
                distanciaMinima = distancia;
                establecimientoMasCercano = establecimiento;
            }
        }
        return establecimientoMasCercano;
    }
    public static double calcularDistancia(double x1, double y1, double x2, double y2) {
        return Math.sqrt(Math.pow(x2 - x1, 2) + Math.pow(y2 - y1, 2));
    }

}
