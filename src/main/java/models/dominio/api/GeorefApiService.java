package dominio.api;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GeorefApiService {


    @GET("provincias")
    Call<ListaProvincias> provincias();

    @GET("provincias")
    Call<ListaProvincias> provincias(@Query("campos") String campos);

    @GET("municipios")
    Call<ListaMunicipios> municipios(@Query("provincia") int idProvincia);

    @GET("municipios")
    Call<ListaMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos);

    @GET("municipios")
    Call<ListaMunicipios> municipios(@Query("provincia") int idProvincia, @Query("campos") String campos, @Query("max") int max);

    @GET("ubicacion")
    Call<UbicacionResponse> obtenerUbicacion(@Query("lat") double latitud, @Query("lon") double longitud);

}