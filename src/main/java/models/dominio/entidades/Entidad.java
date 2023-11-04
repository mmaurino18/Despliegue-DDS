package models.dominio.entidades;

import lombok.Getter;
import lombok.Setter;
import models.dominio.api.Localizacion;
import models.dataBase.Persistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "entidad")
@Getter
@Setter
public class Entidad extends Persistente {

    @Column(name = "nombre")
    public String nombre;

    @OneToMany(mappedBy = "entidad")
    public List<Establecimiento> establecimientos;

    @Transient
    public Localizacion localizacion;

    public Entidad(){
        this.establecimientos = new ArrayList<>();
    }

}
