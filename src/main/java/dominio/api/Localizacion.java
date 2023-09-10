package dominio.api;


import dominio.servicios.Ubicacion;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacion {
    public String nombre;
    public TipoLocalizacion tipoLocalizacion;
    public Ubicacion ubicacion;

    public Localizacion(){

    }

}
