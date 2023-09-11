package dominio.actores;

import dominio.actores.Permiso;
import dominio.dataBase.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol extends Persistente {

    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    public String nombre;

    @OneToMany
    @JoinColumn(name = "rol_id")
    public List<Permiso> permisos;

    public Rol(){
        this.permisos = new ArrayList<>();
    }

    public boolean TenesPermiso(Permiso permiso){
        return true;
    }
}
