package dominio.comunidad;

import java.util.ArrayList;
import java.util.List;

public class Rol {
    public String nombre;
    public List<Permiso> permisos;
    public Rol(){
        this.permisos = new ArrayList<>();
    }
    public boolean TenesPermiso(Permiso permiso){
        return true;
    }
}
