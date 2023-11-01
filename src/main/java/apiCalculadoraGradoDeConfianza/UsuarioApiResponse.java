package apiCalculadoraGradoDeConfianza;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioApiResponse {
    public UsuarioApi usuario;
    public float nuevoPuntaje;
    public String gradoDeConfianzaActual;
}
