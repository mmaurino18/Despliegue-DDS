package apiCalculadoraGradoDeConfianza;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class UsuarioApiRequest {
    public UsuarioApi usuario;
    public List<IncidenteApi> incidentes;
}
