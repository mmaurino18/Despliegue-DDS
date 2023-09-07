package dominio.entidades;

import dominio.entidades.EntidadPrestadora;

import java.util.ArrayList;
import java.util.List;

public class OrganismoDeControl {
    public String nombre;
    //hacer getter y setter
    public List<EntidadPrestadora> entidadesPrestadoras;
    public OrganismoDeControl(){
        this.entidadesPrestadoras = new ArrayList<>();
    }

}
