package apiCalculadoraGradoDeConfianza;
import com.google.gson.*;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;
import okhttp3.OkHttpClient;
import retrofit2.Call;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.lang.reflect.Type;
import java.time.LocalDateTime;

public class GradoDeConfiazaApi {
    //"https://github.com/a-sandoval/servicio-entrega4-tpa-grupo5/tree/master/ServicioCalculadorGradoDeConfianza"
    private static GradoDeConfiazaApi instancia = null;
    private final String urlUsuario = "http://localhost:5191/gradoDeConfianza/usuario/";
    private final String urlComunidad = "https://localhost:7295/gradoDeConfianza/comunidad";
    private Retrofit retrofit;
    private OkHttpClient getUnsafeOkHttpClient() {
        try {
            // Create a trust manager that does not validate certificate chains
            final TrustManager[] trustAllCerts = new TrustManager[]{
                    new X509TrustManager() {
                        @Override
                        public void checkClientTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public void checkServerTrusted(java.security.cert.X509Certificate[] chain, String authType) {
                        }

                        @Override
                        public java.security.cert.X509Certificate[] getAcceptedIssuers() {
                            return new java.security.cert.X509Certificate[]{};
                        }
                    }
            };

            // Install the all-trusting trust manager
            final SSLContext sslContext = SSLContext.getInstance("SSL");
            sslContext.init(null, trustAllCerts, new java.security.SecureRandom());

            // Create an ssl socket factory with our all-trusting manager
            final SSLSocketFactory sslSocketFactory = sslContext.getSocketFactory();

            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            builder.sslSocketFactory(sslSocketFactory, (X509TrustManager) trustAllCerts[0]);
            builder.hostnameVerifier((hostname, session) -> true);

            return builder.build();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    class LocalDateTimeSerializer implements JsonSerializer<LocalDateTime> {
        @Override
        public JsonElement serialize(LocalDateTime src, Type typeOfSrc, JsonSerializationContext context) {
            return new JsonPrimitive(src.toString());
        }
    }

    class LocalDateTimeDeserializer implements JsonDeserializer<LocalDateTime> {
        @Override
        public LocalDateTime deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
                throws JsonParseException {
            return LocalDateTime.parse(json.getAsString());
        }
    }
    public class LocalDateTimeAdapter extends TypeAdapter<LocalDateTime> {
        @Override
        public void write(JsonWriter out, LocalDateTime value) throws IOException {
            out.value(value.toString());
        }

        @Override
        public LocalDateTime read(JsonReader in) throws IOException {
            return LocalDateTime.parse(in.nextString());
        }
    }
    Gson gson = new GsonBuilder()
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeSerializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeDeserializer())
            .registerTypeAdapter(LocalDateTime.class, new LocalDateTimeAdapter())
            .create();
    private GradoDeConfiazaApi() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlUsuario)
                .client(getUnsafeOkHttpClient())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();
    }

    public static GradoDeConfiazaApi instancia(){
        if(instancia== null){
            instancia = new GradoDeConfiazaApi();
        }
        return instancia;
    }

    public ComunidadApi comunidadDevuelta(ComunidadApi comunidad) throws IOException{
        GradoDeConfianzaApiService gradoDeConfianza = this.retrofit.create(GradoDeConfianzaApiService.class);
        Call<ComunidadApi> requestGradoConfianzaComunidad = gradoDeConfianza.comunidadApi(comunidad);
        Response<ComunidadApi> responseGradoConfianzaComunidad = requestGradoConfianzaComunidad.execute();
        return responseGradoConfianzaComunidad.body();
    }

    public UsuarioApi usuarioDevuelto(UsuarioApi usuario) throws IOException{
        GradoDeConfianzaApiService gradoDeConfianza5Service = this.retrofit.create((GradoDeConfianzaApiService.class));
        Call<UsuarioApi> requestGradoConfianzaUsuario = gradoDeConfianza5Service.usuarioApi(usuario);
        Response<UsuarioApi> responseGradoConfianzaUsuario = requestGradoConfianzaUsuario.execute();
        return responseGradoConfianzaUsuario.body();
    }
}
