package controllers;

import models.dataBase.repositorios.CiudadanoRepository;
import models.dataBase.repositorios.PropietarioRepository;
import models.dominio.actores.*;
import models.dataBase.repositorios.UsuarioRepository;
import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class LoginController extends Controller implements ICrudViewsHandler {
    private UsuarioRepository repositorioUsuario;
    private PropietarioRepository repositorioPropietario;
    private CiudadanoRepository repositorioCiudadano;

    public LoginController(UsuarioRepository repositorio ,PropietarioRepository repositorioPropietario, CiudadanoRepository repositorioCiudadano){
        this.repositorioUsuario = repositorio;
        this.repositorioPropietario = repositorioPropietario;
        this.repositorioCiudadano = repositorioCiudadano;
    }

    @Override
    public void index(Context context) {
        context.render("iniciarSesion.hbs");
    }

    public void addSesion(Context context){
        String nombreUsuario = context.formParam("usuario");
        String contrasenia = context.formParam("contrasenia");
        Usuario usuario = repositorioUsuario.findByNombre(nombreUsuario);
        if(usuario == null) {
            System.out.println("Usuario no encontrado");
            context.render("errorLogin.hbs");
        }else {
            if ((Objects.equals(usuario.getNombre(), nombreUsuario)) && (Objects.equals(usuario.getContrasenia(), contrasenia))) {
                // asignando id a variable global
                context.sessionAttribute("id_usuario", usuario.getId());
                if(usuario.getRol().getTipo().equals(TipoRol.CIUDADANO)) {
                    context.redirect("/home");
                } else {
                    context.redirect("/homePropietario");
                }
            } else {
                System.out.println("No encontre el Usuario");
                context.render("errorLogin.hbs");
            }
        }
// EL RESULTADO DE LA BUSQUEDA SOLO DEBE SER UN USUARIO, LUEGO VALIDAR AL HACER LOS REGISTROS QUE EL NOMBRE DE USUARIO ES UNICO

    }

    @Override
    public void show(Context context) {
        Usuario usuario = super.usuarioLogueado(context);
        Map<String,Object> model = new HashMap<>();

        if(usuario.getRol().getTipo().equals(TipoRol.PROPIETARIO)){
            Propietario propietario = super.PropietarioLogueadoSuper(context);
            model.put("persona",propietario);
            model.put("usuario",usuario);
            model.put("tipoUsuario",usuario.getRol().getTipo().toString());
            model.put("tipoPropietario",propietario.getTipoPropietario().toString());

            context.render("/editP/configuracionCuentaP.hbs",model);
            
        }else if (usuario.getRol().getTipo().equals(TipoRol.CIUDADANO)){
            //TODO
        }


    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

    }

    @Override
    public void edit(Context context) {
        Usuario usuario = super.usuarioLogueado(context);
        Map<String,Object> model = new HashMap<>();

        if(usuario.getRol().getTipo().equals(TipoRol.PROPIETARIO)){
            Propietario propietario = super.PropietarioLogueadoSuper(context);
            model.put("persona",propietario);
            model.put("tipoUsuario",usuario.getRol().getTipo().toString());
            context.render("/editP/edit_configuracionCuentaP.hbs",model);

        }

        else if (usuario.getRol().getTipo().equals(TipoRol.CIUDADANO)){
            //TODO
        }
    }

    @Override
    public void update(Context context) {
        Usuario usuario = super.usuarioLogueado(context);

        if(usuario.getRol().getTipo().equals(TipoRol.PROPIETARIO)){
            Propietario propietario = super.PropietarioLogueadoSuper(context);
            this.asignarParametrosPropietarioEdit(propietario, context);
            this.repositorioPropietario.update(propietario);
            context.redirect("/homePropietario");
        }

        else if (usuario.getRol().getTipo().equals(TipoRol.CIUDADANO)){
            //TODO
        }

    }

    @Override
    public void delete(Context context) {

    }

    public void cerrarSesion(Context context){
        context.removeCookie("id_usuario");
        context.redirect("/");
    }


    public Usuario UsuarioLogueadoDirecto(Context context){
        return entityManager().find(Usuario.class, Long.parseLong(("" + context.sessionAttribute("id_usuario"))));
    }

    public void asignarParametrosPropietarioEdit(Propietario propietario, Context context){
        if(context.formParam("nombre") != null && !context.formParam("nombre").isEmpty()) {
            propietario.setNombre(context.formParam("nombre"));
        }
        if(context.formParam("apellido") != null && !context.formParam("apellido").isEmpty()) {
            propietario.setApellido(context.formParam("apellido"));
        }
        if(context.formParam("mail") != null && !context.formParam("mail").isEmpty()) {
            propietario.setMail(context.formParam("mail"));
        }
        if(context.formParam("category") != null && !context.formParam("category").isEmpty()) {
            if(context.formParam("category").equals("DeOrganismo")){
                propietario.setTipoPropietario(TipoPropietario.ORGANISMO_DE_CONTROL);
            }
            else if (context.formParam("category").equals("deEntidad")){
                propietario.setTipoPropietario(TipoPropietario.ENTIDAD_PRESTADORA);
            }

        }
    }
}
