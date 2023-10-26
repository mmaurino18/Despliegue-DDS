package dominio.actores;


import dominio.dataBase.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

// desarrollodor
@Entity
@Table(name = "permiso")
@Getter
@Setter
public class Permiso extends Persistente {

    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    public String nombre;

    @Column(name = "nombreInterno",columnDefinition = "VARCHAR(55)")
    private String nombreInterno;

    @Column(name = "descripcion", columnDefinition = "text")
    public String descripcion;

    public Permiso(){

    }
    public boolean coincideConNombreInterno(String nombre) {
        return this.nombreInterno.equals(nombre);
    }
}
