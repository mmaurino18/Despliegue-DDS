package controllers;

import dominio.actores.Usuario;
import dominio.dataBase.repositorios.ServicioRepository;
import dominio.servicios.Servicio;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import server.utils.ICrudViewsHandler;
import server.exception.AccessDeniedException;

public class ServiciosController extends Controller implements ICrudViewsHandler {
    private ServicioRepository repositorioDeServicios;

    public ServiciosController(ServicioRepository repositorioDeServicios) {
        this.repositorioDeServicios = repositorioDeServicios;
    }

    public void test(){
        List<Servicio> servicios = this.repositorioDeServicios.findAll();
        for (Servicio elemento : servicios) {
            System.out.println("servicio => " + elemento.getNombre());
            System.out.println("descripcion => " + elemento.getDescripcion());
        }
    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Servicio> servicios = this.repositorioDeServicios.findAll();
        model.put("servicios", servicios);
        context.render("servicios/servicios.hbs", model);

    }

    @Override
    public void show(Context context) {
        Servicio servicio = (Servicio) this.repositorioDeServicios.findById(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("servicio", servicio);
        context.render("servicios/servicio.hbs", model);
    }

    @Override
    public void create(Context context) {

        Usuario usuarioLogueado = super.usuarioLogueado(context);

        if(usuarioLogueado == null || !usuarioLogueado.getRol().tenesPermiso("crear_servicios")) {
            throw new AccessDeniedException();
        }

        Servicio servicio = null;
        Map<String, Object> model = new HashMap<>();
        model.put("servicio", servicio);
        context.render("servicios/servicio.hbs", model);
    }

    @Override
    public void save(Context context) {
        Servicio servicio = new Servicio();
        this.asignarParametros(servicio, context);
        this.repositorioDeServicios.save(servicio);
        context.status(HttpStatus.CREATED);
        context.redirect("/servicios");
    }

    @Override
    public void edit(Context context) {
        Servicio servicio = (Servicio) this.repositorioDeServicios.findById(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("servicio", servicio);
        context.render("servicios/servicio.hbs", model);
    }

    @Override
    public void update(Context context) {
        Servicio servicio = (Servicio) this.repositorioDeServicios.findById(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(servicio, context);
        this.repositorioDeServicios.update(servicio);
        context.redirect("/servicios");
    }

    @Override
    public void delete(Context context) {
        Servicio servicio = (Servicio) this.repositorioDeServicios.findById(Long.parseLong(context.pathParam("id")));
        this.repositorioDeServicios.delete(servicio);
        context.redirect("/servicios");
    }

    private void asignarParametros(Servicio servicio, Context context) {
        if(!Objects.equals(context.formParam("nombre"), "")) {
            servicio.setNombre(context.formParam("nombre"));
        }
    }
}