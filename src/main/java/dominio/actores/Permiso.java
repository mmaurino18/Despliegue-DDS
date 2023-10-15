package dominio.actores;


import dominio.dataBase.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "permiso")
@Getter
@Setter
public class Permiso extends Persistente {

    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    public String nombre;

    @Column(name = "descripcion", columnDefinition = "text")
    public String descripcion;

    public Permiso(){

    }
    public boolean coincideConNombreInterno(String nombre) {
        return this.nombre.equals(nombre);
    }
}
