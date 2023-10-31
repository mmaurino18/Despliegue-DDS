package apiCalculadoraGradoDeConfianza;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface GradoDeConfianzaApiService {

    @POST("/gradoDeConfianza/usuario/")
   // Call<UsuarioApi> usuarioApi(@Body UsuarioApi usuario);
    Call<UsuarioApi> usuarioApi(@Query("usuario") UsuarioApi usuario);

    @POST("/gradoDeConfianza/comunidad/")
    Call<ComunidadApi> comunidadApi(@Body ComunidadApi comunidad);
}
