package controllers;

import dominio.actores.Usuario;
import dominio.dataBase.repositorios.UsuarioRepository;
import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;

import java.util.Objects;

public class LoginController extends Controller implements ICrudViewsHandler {
    private UsuarioRepository repositorioUsuario;

    public LoginController(UsuarioRepository repositorio){
        this.repositorioUsuario = repositorio;
    }

    @Override
    public void index(Context context) {
        context.render("iniciarSesion.hbs");
    }

    public void addSesion(Context context){
        String nombreUsuario = context.formParam("usuario");
        String contrasenia = context.formParam("Contrasenia");
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario);

        if( ( Objects.equals(usuario.getNombre(), nombreUsuario) ) && ( Objects.equals(usuario.getContrasenia(), contrasenia) ) ){
            context.sessionAttribute("id_usuario",usuario.getId());
            context.render("homeCiudadano.hbs");

        }
        else{
            context.render("errorLogin.hbs");

        }

// EL RESULTADO DE LA BUSQUEDA SOLO DEBE SER UN USUARIO, LUEGO VALIDAR AL HACER LOS REGISTROS QUE EL NOMBRE DE USUARIO ES UNICO

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

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


}
