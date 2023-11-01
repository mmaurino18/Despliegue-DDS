package controllers;

import lombok.Getter;
import models.dataBase.repositorios.UsuarioRepository;
import models.dominio.actores.Ciudadano;
import models.dominio.actores.Usuario;
import models.dataBase.repositorios.CiudadanoRepository;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dominio.validacionContrasenia.ValidacionCaracteres;
import models.dominio.validacionContrasenia.ValidacionContraseña;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
public class RegistroCiudadanoController extends Controller implements ICrudViewsHandler {
    private final ValidacionContraseña validador  = new ValidacionContraseña();;
    CiudadanoRepository repositorioCiudadano;
    UsuarioRepository repositorioUsuario;
    public RegistroCiudadanoController(CiudadanoRepository repositorioCiudadano, UsuarioRepository repositorioUsuario){
        this.repositorioCiudadano = repositorioCiudadano;
        this.repositorioUsuario = repositorioUsuario;
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
        boolean usuarioValido = this.usuarioValido(context.formParam("usuario"));
        String contrasenia = context.formParam("contrasenia");
        boolean esValida = validador.validar(contrasenia);

        if(esValida && usuarioValido) {
            Ciudadano ciudadano = new Ciudadano();
            this.asignarParametros(ciudadano, context);
            this.repositorioCiudadano.save(ciudadano);
            context.status(HttpStatus.CREATED);
            context.render("registroOk.hbs");
       }else{
            Map<String, Object> model = new HashMap<>();
            String errores = validador.errores(contrasenia);
            String mensajeUsuario = mensajeDeUsaurio(usuarioValido);
            model.put("error", errores);
            model.put("usuario",mensajeUsuario);
            context.render("registroError.hbs", model);
        }
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
        ciudadano.setNombre(context.formParam("nombre"));
        ciudadano.setApellido(context.formParam("apellido"));
        ciudadano.setMail(context.formParam("email"));
        Usuario usuario = new Usuario();
        usuario.setNombre(context.formParam("usuario"));
        usuario.setContrasenia(context.formParam("contrasenia"));
        ciudadano.setUsuario(usuario);
    }

    private boolean usuarioValido (String nombreUsuario){
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario);
        return (usuario == null) ? true : false;
    }

    private String mensajeDeUsaurio (boolean bool) {
        return bool ? "" : "El nombre de usuario ya existe";
    }
}
