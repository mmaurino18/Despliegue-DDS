package models.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import models.dataBase.Persistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entidad_prestadora")
@Getter
@Setter
public class EntidadPrestadora extends Persistente {

    @Column(name = "nombre")
    public String nombre;

    @OneToMany
    @JoinColumn(name = "entidad_prestadora_id", referencedColumnName = "id")
    public List<Entidad> entidades;

    public EntidadPrestadora(){
        this.entidades = new ArrayList<>();
    }

    public void agregarEntidad(Entidad entidad){
        this.entidades.add(entidad);
    }

    public boolean entidadesVacia(){
        return this.entidades.isEmpty();
    }

}
