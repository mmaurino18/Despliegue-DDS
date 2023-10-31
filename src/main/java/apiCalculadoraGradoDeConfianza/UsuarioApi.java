package apiCalculadoraGradoDeConfianza;


import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class UsuarioApi {
    public String nombre;
    public String apellido;
    public long id;
    public float puntosDeConfianza;
    public List<IncidenteApi> incidentes;

}
