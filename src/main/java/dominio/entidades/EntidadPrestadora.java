package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

public class EntidadPrestadora {
    public String nombre;
    public List<Entidad> entidades;
    public EntidadPrestadora(){
        this.entidades = new ArrayList<>();
    }

}
