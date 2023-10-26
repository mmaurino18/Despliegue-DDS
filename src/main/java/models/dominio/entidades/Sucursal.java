package models.dominio.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("sucursal")
public class Sucursal extends Establecimiento{

    public Sucursal(String nombre) {
        this.nombre = nombre;
    }

    public Sucursal() {

    }

    @Override
    public String getNombre() {
        return this.nombre;
    }
}
