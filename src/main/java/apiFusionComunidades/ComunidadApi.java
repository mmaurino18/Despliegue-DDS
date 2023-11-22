package apiFusionComunidades;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ComunidadApi {
    public String nombre;
    public List <CiudadanoApi> miembros;
    public float gradoDeConfianza;
    public List<EstablecimientoApi> establecimientos;
    public List <ServicioApi> servicios;
}
