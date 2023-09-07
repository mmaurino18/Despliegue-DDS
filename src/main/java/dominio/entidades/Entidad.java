package dominio.entidades;

import java.util.ArrayList;
import java.util.List;

public abstract class  Entidad {
    public String nombre;
    public List<Establecimiento> establecimientos;
    //public Localizacion localizacion;
    public Entidad(){
        this.establecimientos = new ArrayList<>();
    }
    public void ObtenerInforme(){

    }

}
