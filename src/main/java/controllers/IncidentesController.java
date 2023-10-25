package controllers;

import dominio.actores.Usuario;
import dominio.dataBase.repositorios.IncidenteRepository;
import dominio.servicios.Incidente;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.exception.AccessDeniedException;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class IncidentesController extends Controller implements ICrudViewsHandler {
    private IncidenteRepository repositorioDeIncidentes;

    public IncidentesController(IncidenteRepository repositorioDeIncidentes) {
        this.repositorioDeIncidentes = repositorioDeIncidentes;
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorioDeIncidentes.findAll();
        model.put("incidentes", incidentes);
        context.render("comunidad.hbs", model);
    }

    @Override
    public void show(Context context) {
        Incidente incidente = (Incidente) this.repositorioDeIncidentes.findById(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidentes.hbs", model);
    }

    @Override
    public void create(Context context) {
        Usuario usuarioLogueado = super.usuarioLogueado(context);

        if(usuarioLogueado == null || !usuarioLogueado.getRol().tenesPermiso("abrir_incidente")) {
            throw new AccessDeniedException();
        }

        Incidente incidente = null;
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("abrirIncidente.hbs", model);
    }

    @Override
    public void save(Context context) {
        Incidente incidente = new Incidente();
        this.asignarParametros(incidente, context);
        this.repositorioDeIncidentes.save(incidente);
        context.status(HttpStatus.CREATED);
        context.redirect("/incidentes");
    }

    @Override
    public void edit(Context context) {
        Incidente incidente = (Incidente) this.repositorioDeIncidentes.findById(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidentes.hbs", model);
    }

    @Override
    public void update(Context context) {
        Incidente incidente = (Incidente) this.repositorioDeIncidentes.findById(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(incidente, context);
        this.repositorioDeIncidentes.update(incidente);
        context.redirect("/incidentes");
    }

    @Override
    public void delete(Context context) {
        Incidente incidente = (Incidente) this.repositorioDeIncidentes.findById(Long.parseLong(context.pathParam("id")));
        this.repositorioDeIncidentes.delete(incidente);
        context.redirect("/incidentes");
    }

    private void asignarParametros(Incidente incidente, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
            incidente.setNombreIcidente(context.formParam("nombre"));
        }
    }
}
