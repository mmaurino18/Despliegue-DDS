package apiCalculadoraGradoDeConfianza;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
public class ComunidadApi {
    public List<UsuarioApi> miembros;
    public String gradoDeConfianza;
}
