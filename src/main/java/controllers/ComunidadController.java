package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dataBase.repositorios.CiudadanoRepository;
import models.dataBase.repositorios.ComunidadRepository;
import models.dominio.actores.Ciudadano;
import models.dominio.comunidad.Comunidad;
import models.dominio.servicios.Incidente;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ComunidadController extends Controller implements ICrudViewsHandler {

    private ComunidadRepository comunidadRepository;
    private CiudadanoRepository ciudadanoRepository;


    @Override
    public void index(Context context) {
        List <Comunidad> comunidades = comunidadRepository.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put("Comunidad", comunidades);
        context.render("comunidad2.hbs", model);
    }

    @Override
    public void show(Context context) {

        Ciudadano ciudadano = super.CiudadanoLogueado(context);
        List<Comunidad> comunidades  = ciudadano.getComunidades();
        Map<String, Object> model = new HashMap<>();
        model.put("Comunidad", comunidades);
        context.render("misComunidades.hbs", model);
    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws IOException {
       Ciudadano ciudadano = super.CiudadanoLogueado(context);
       Comunidad comunidad = comunidadRepository.findById(Long.parseLong(context.pathParam("id")));
       boolean coincide = ciudadano.getComunidades().stream()
                .anyMatch(comunidadCiudadano -> comunidadCiudadano.getId().equals(comunidad.getId()));
        if(!coincide) {
            ciudadano.agregarComunidad(comunidad);
            ciudadanoRepository.save(ciudadano);
        }
       context.redirect("/comunidades");
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

    public ComunidadController(ComunidadRepository comunidadRepository,CiudadanoRepository ciudadanoRepository){
        this.ciudadanoRepository=ciudadanoRepository;
        this.comunidadRepository = comunidadRepository;
    }
}
