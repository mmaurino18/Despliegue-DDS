package dominio.entidades;


import dominio.dataBase.Persistente;
import dominio.servicios.PrestacionDeServicio;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "establecimiento")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class Establecimiento extends Persistente {

    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    public String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    public String descripcion;

    @OneToMany(mappedBy = "establecimiento")
    public List<PrestacionDeServicio> serviciosPrestados;
    //public Localizacion localizacion;

    @Transient
    private Double coordenadax;

    @Transient
    private Double coordenaday;

    public abstract String getNombre();

    public static double getCoordenadaX() {
        return 0;
    }

    public static double getCoordenaY() {
        return 0;
    }

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

    public void agregarServiciosPrestados(PrestacionDeServicio prestacionDeServicio){
        this.serviciosPrestados.add(prestacionDeServicio);
        prestacionDeServicio.setEstablecimiento(this);
    }

}
