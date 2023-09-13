package dominio.entidades;

import dominio.api.Localizacion;
import dominio.dataBase.Persistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo")
public abstract class  Entidad extends Persistente {

    @Column(name = "nombre")
    public String nombre;

    @OneToMany
    @JoinColumn(name = "entidad_id")
    public List<Establecimiento> establecimientos;

    @Transient
    public Localizacion localizacion;

    public Entidad(){
        this.establecimientos = new ArrayList<>();
    }
    public void ObtenerInforme(){

    }

}
