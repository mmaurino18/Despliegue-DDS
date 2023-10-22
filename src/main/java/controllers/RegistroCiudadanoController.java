package controllers;

import dominio.actores.Ciudadano;
import dominio.actores.Usuario;
import dominio.dataBase.repositorios.CiudadanoRepository;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import server.utils.ICrudViewsHandler;

import java.util.Objects;

public class RegistroCiudadanoController extends Controller implements ICrudViewsHandler {

    CiudadanoRepository repositorioCiudadano;
    public RegistroCiudadanoController(CiudadanoRepository repositorioCiudadano){
        this.repositorioCiudadano = repositorioCiudadano;
    }

    @Override
    public void index(Context context) {
        context.render("registrarUsuario.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        Ciudadano ciudadano = new Ciudadano();
        this.asignarParametros(ciudadano, context);
        this.repositorioCiudadano.save(ciudadano);
        context.status(HttpStatus.CREATED);
        context.render("registroOk.hbs");
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

    private void asignarParametros(Ciudadano ciudadano, Context context) {
        if(!Objects.equals(context.formParam("usuario"), "")) {
            ciudadano.setNombre(context.formParam("nombre"));
            ciudadano.setApellido(context.formParam("apellido"));
            ciudadano.setMail(context.formParam("email"));

            Usuario usuario = new Usuario();
            usuario.setNombre(context.formParam("usuario"));
            usuario.setContrasenia(context.formParam("contrasenia"));

            ciudadano.setUsuario(usuario);

            // tendriamos que lanzar una excepcion si el usuario o la contrasenia ya existen
            // y en esa excepcion redirigir a otra ruta o avisar que fallo
        }
    }
}
