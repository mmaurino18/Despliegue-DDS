package dominio.entidades;

import dominio.entidades.EntidadPrestadora;

import java.util.ArrayList;
import java.util.List;

public class OrganismoDeControl {

    public String nombre;
    public List<EntidadPrestadora> entidadesPrestadoras;
    public OrganismoDeControl(){
        this.entidadesPrestadoras = new ArrayList<>();
    }

}
