package apiCalculadoraGradoDeConfianza;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ComunidadApiResponse {
    public ComunidadApi comunidad;
    public float nuevoPuntaje;
    public String gradoDeConfianza;
}
