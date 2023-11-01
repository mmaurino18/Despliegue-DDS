package apiCalculadoraGradoDeConfianza;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface GradoDeConfianzaService {

    @POST("gradoDeConfianza/usuario")
    Call<UsuarioApiResponse> obtenerGradoDeConfianzaUsuario(@Body UsuarioApiRequest usuarioRequest);

    @POST("gradoDeConfianza/comunidad")
   Call<ComunidadApiResponse> obtenerGradoDeConfianzaComunidad(@Body ComunidadApiRequest comunidadRequest);
}
