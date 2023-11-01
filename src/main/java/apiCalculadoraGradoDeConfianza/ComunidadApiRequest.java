package apiCalculadoraGradoDeConfianza;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ComunidadApiRequest {
    public ComunidadApi comunidad;
    public List<IncidenteApi> inicidentes;
}
