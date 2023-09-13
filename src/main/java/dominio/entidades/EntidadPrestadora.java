package dominio.entidades;

import dominio.dataBase.Persistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.NavigableMap;

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
