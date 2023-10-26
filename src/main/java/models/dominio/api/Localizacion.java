package models.dominio.api;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Localizacion {
    private String nombre;
    private TipoLocalizacion tipoLocalizacion;
    private Ubicacion ubicacion;

    public Localizacion(){

    }

}
