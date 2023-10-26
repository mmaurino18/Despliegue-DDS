package models.dominio.entidades;

import models.dataBase.Persistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entidad_prestadora")
public class EntidadPrestadora extends Persistente {

    @Column(name = "nombre")
    public String nombre;

    @OneToMany
    @JoinColumn(name = "entidad_prestadora_id", referencedColumnName = "id")
    public List<Entidad> entidades;
    public EntidadPrestadora(){
        this.entidades = new ArrayList<>();
    }

}
