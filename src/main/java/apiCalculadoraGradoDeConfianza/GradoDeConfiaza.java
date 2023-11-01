package apiCalculadoraGradoDeConfianza;
import com.google.gson.*;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;


public class GradoDeConfiaza {
    //"https://github.com/a-sandoval/servicio-entrega4-tpa-grupo5/tree/master/ServicioCalculadorGradoDeConfianza"
    private static GradoDeConfiaza instancia = null;
    private final String urlUsuario = /*"https://localhost:7295/gradoDeConfianza/usuario/"; //*/ "http://localhost:5191/gradoDeConfianza/usuario/";
    private final String urlComunidad = "https://localhost:7295/gradoDeConfianza/comunidad";
    private Retrofit retrofit;
    Gson gson = new GsonBuilder()

            .create();
    private GradoDeConfiaza() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlUsuario)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GradoDeConfiaza instancia(){
        if(instancia== null){
            instancia = new GradoDeConfiaza();
        }
        return instancia;
    }

    public ComunidadApiResponse comunidadDevuelta(ComunidadApiRequest comunidad) throws IOException{
        GradoDeConfianzaService gradoDeConfianzaService = this.retrofit.create(GradoDeConfianzaService.class);
        Call<ComunidadApiResponse> requestGradoConfianzaComunidad = gradoDeConfianzaService.obtenerGradoDeConfianzaComunidad(comunidad);
        Response<ComunidadApiResponse> responseGradoConfianzaComunidad = requestGradoConfianzaComunidad.execute();
        return responseGradoConfianzaComunidad.body();
    }

    public UsuarioApiResponse usuarioDevuelto(UsuarioApiRequest usuario) throws IOException{
        GradoDeConfianzaService gradoDeConfianzaService = this.retrofit.create((GradoDeConfianzaService.class));
        Call<UsuarioApiResponse> requestGradoConfianzaUsuario = gradoDeConfianzaService.obtenerGradoDeConfianzaUsuario (usuario);
        Response<UsuarioApiResponse> responseGradoConfianzaUsuario = requestGradoConfianzaUsuario.execute();
        return responseGradoConfianzaUsuario.body();
    }
}
