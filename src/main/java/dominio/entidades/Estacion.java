package dominio.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("estacion")
public class Estacion extends Establecimiento {

    public Estacion(String nombre) {
        this.nombre = nombre;
    }

    public Estacion() {

    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
}
