package controllers;

import io.javalin.http.Context;
import models.dominio.api.GeorefApi;
import models.dominio.api.mapeo.ListaMunicipios;
import models.dominio.api.mapeo.ListaProvincias;
import models.dominio.api.mapeo.Municipio;
import models.dominio.api.mapeo.Provincia;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LocalizacionPController extends Controller implements ICrudViewsHandler {

    public LocalizacionPController(){

    }

    @Override
    public void index(Context context) {
        context.render("edit_localizacionTipoP.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws IOException {

    }

    @Override
    public void edit(Context context) {

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    public void provincia(Context context) throws IOException {
        GeorefApi servicio = GeorefApi.instancia();
        ListaProvincias listaDeProvincias = servicio.listadoDeProvincias();
        Map<String,Object> model = new HashMap<>();
        model.put("provincia", listaDeProvincias.getProvincias());

        for(Provincia provincia : listaDeProvincias.getProvincias()){
            System.out.println( "ID: " + provincia.getId() + " PROVINCIA -> " + provincia.getNombre());
            System.out.println("UBICACION -> LAT: " + provincia.getCentroide().getLat() + " LON: " + provincia.getCentroide().getLon() );
        }
        context.render("edit_localizacionProvincia.hbs",model);
    }

    public void tipoLocalizacion(Context context) throws IOException {
        String tipoLocalizacion = context.formParam("tipo");

        GeorefApi servicio = GeorefApi.instancia();
        ListaProvincias listaDeProvincias = servicio.listadoDeProvincias();
        Map<String,Object> model = new HashMap<>();
        model.put("provincia", listaDeProvincias.getProvincias());

        for(Provincia provincia : listaDeProvincias.getProvincias()){
            System.out.println( "ID: " + provincia.getId() + " PROVINCIA -> " + provincia.getNombre());
            System.out.println("UBICACION -> LAT: " + provincia.getCentroide().getLat() + " LON: " + provincia.getCentroide().getLon() );
        }

        switch (Objects.requireNonNull(tipoLocalizacion)){
            case "Provincia":
                model.put("tipoProvincia","ok");
                context.render("edit_localizacionProvincia.hbs",model);
                break;
            case "Municipio":
                model.put("tipoMunicipio","ok");
                context.render("edit_localizacionProvincia.hbs",model);
                break;
            case "Departamento":
                model.put("tipoDepartamento","ok");
                context.render("edit_localizacionProvincia.hbs",model);
               break;
            default:
                System.out.println("no se eligio ninguna opcion");
        }

    }

    public void mostrarMunicipios(Context context) throws IOException {

        System.out.println( "ENTRE");

        GeorefApi servicio = GeorefApi.instancia();
        ListaMunicipios listaDeMunicipios = servicio.listadoDeMunicipiosPorIDProvincia(Integer.parseInt(context.pathParam("idProvincia")));
        Map<String,Object> model = new HashMap<>();
        model.put("municipio", listaDeMunicipios.getMunicipios());

        for(Municipio municipio : listaDeMunicipios.getMunicipios()){
            System.out.println( "ID: " + municipio.getId() + " MUNICIPIO -> " + municipio.getNombre());
            System.out.println("UBICACION -> LAT: " + municipio.getCentroide().getLat() + " LON: " + municipio.getCentroide().getLon() );
        }

        context.render("edit_localizacionMunicipio.hbs",model);

    }
}
