package models.dominio.entidades;

import models.dataBase.Persistente;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organismo_de_control")
public class OrganismoDeControl extends Persistente {

    @Column(name = "nombre")
    public String nombre;

    @OneToMany
    @JoinColumn(name = "organismo_control_id")
    public List<EntidadPrestadora> entidadesPrestadoras;
    public OrganismoDeControl(){
        this.entidadesPrestadoras = new ArrayList<>();
    }

}
