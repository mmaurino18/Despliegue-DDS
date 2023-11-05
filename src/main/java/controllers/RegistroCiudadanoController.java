package controllers;

import lombok.Getter;
import models.dataBase.repositorios.PropietarioRepository;
import models.dataBase.repositorios.UsuarioRepository;
import models.dominio.actores.*;
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

    PropietarioRepository repositoryPropietario;
    UsuarioRepository repositorioUsuario;
    public RegistroCiudadanoController(CiudadanoRepository repositorioCiudadano, UsuarioRepository repositorioUsuario, PropietarioRepository repositoryPropietario){
        this.repositorioCiudadano = repositorioCiudadano;
        this.repositoryPropietario = repositoryPropietario;
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
        boolean esValida = true;//validador.validar(contrasenia);

        if(esValida && usuarioValido) {
            registroUsuario(context);
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
        Rol rol = new Rol();
        rol.setTipo(TipoRol.CIUDADANO);
        usuario.setRol(rol);
        ciudadano.setUsuario(usuario);
    }

    private void asignarParametros(Propietario propietario, Context context) {
        propietario.setNombre(context.formParam("nombre"));
        propietario.setApellido(context.formParam("apellido"));
        propietario.setMail(context.formParam("email"));
        Usuario usuario = new Usuario();
        usuario.setNombre(context.formParam("usuario"));
        usuario.setContrasenia(context.formParam("contrasenia"));
        Rol rol = new Rol();
        rol.setTipo(TipoRol.PROPIETARIO);
        usuario.setRol(rol);
        propietario.setUsuario(usuario);
    }

    private void registroUsuario(Context context){
        String categoria = context.formParam("category");
        if(categoria.equals("Ciudadano")) {
            Ciudadano ciudadano = new Ciudadano();
            this.asignarParametros(ciudadano, context);
            this.repositorioCiudadano.save(ciudadano);
        }else {
            Propietario propietario = new Propietario();
            this.asignarParametros(propietario, context);
            this.repositoryPropietario.save(propietario);
        }
    }


    private boolean usuarioValido (String nombreUsuario){
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario);
        return (usuario == null) ? true : false;
    }

    private String mensajeDeUsaurio (boolean bool) {
        return bool ? "" : "El nombre de usuario ya existe";
    }
}
