package dominio.api;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import java.io.IOException;
import java.util.Comparator;
import retrofit2.Call;
import retrofit2.Response;
import java.util.Collections;


public class GeorefApi {
    private static GeorefApi instancia = null;
    private static int max = 1000;
    private static final String urlApi = "https://apis.datos.gob.ar/georef/api/";
    private Retrofit retrofit;

    private GeorefApi() {
        this.retrofit = new Retrofit.Builder()
                .baseUrl(urlApi)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    public static GeorefApi instancia() {
        if (instancia == null) {
            instancia = new GeorefApi();
        }
        return instancia;
    }

    public UbicacionResponse obtenerUbicacion(double latitud, double longitud) throws IOException {
        GeorefApiService georefService = this.retrofit.create(GeorefApiService.class);
        Call<UbicacionResponse> requestUbicacion = georefService.obtenerUbicacion(latitud, longitud);
        Response<UbicacionResponse> responseUbicacion = requestUbicacion.execute();
        UbicacionResponse ubicacionResponse = responseUbicacion.body();

        //System.out.println(ubicacionResponse);
        return ubicacionResponse;
    }
    public ListaProvincias listadoDeProvincias() throws IOException {
        GeorefApiService georefService = this.retrofit.create(GeorefApiService.class);
        Call<ListaProvincias> requestProvinciasArgentinas = georefService.provincias();
        Response<ListaProvincias> responseProvinciasArgentinas = requestProvinciasArgentinas.execute();
        return OrdenarProvinciasId(responseProvinciasArgentinas.body());
    }

    public ListaMunicipios listadoDeMunicipiosDeProvincia(int id) throws IOException {
        GeorefApiService georefService = this.retrofit.create(GeorefApiService.class);
        Call<ListaMunicipios> requestListadoDeMunicipios = georefService.municipios(id,"id,nombre",max);
        Response<ListaMunicipios> responseListadoDeMunicipios = requestListadoDeMunicipios.execute();
        return OrdenarMunicipiosAlf(responseListadoDeMunicipios.body());
    }

    private ListaProvincias OrdenarProvinciasId(ListaProvincias provincias) {
        provincias.getProvincias().sort((p1,p2)->p1.getId() >= p2.getId()? 1:-1);
        return provincias;
    }
    private ListaMunicipios OrdenarMunicipiosAlf(ListaMunicipios municipios){
        Collections.sort(municipios.getMunicipios(), Comparator.comparing(Municipio::getNombre));
        return municipios;
    }
}
