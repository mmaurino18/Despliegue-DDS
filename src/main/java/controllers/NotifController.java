package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.CiudadanoRepository;
import models.dataBase.repositorios.UsuarioRepository;
import models.dominio.actores.Ciudadano;
import models.dominio.actores.Usuario;
import models.dominio.comunidad.CuandoNotificar;
import models.dominio.comunidad.MedioDeNotificaion;
import server.utils.ICrudViewsHandler;

public class NotifController extends Controller implements ICrudViewsHandler {

    private CiudadanoRepository ciudadanoRepository;

    @Override
    public void index(Context context) {
        context.render("gestionUsuario.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {
        Ciudadano ciudadano = ciudadanoRepository.findByUsuarioID(context.sessionAttribute("id_usuario"));
        switch (context.formParam("medio")){
            case "whatsapp": ciudadano.setMedioDeNotificaion(MedioDeNotificaion.WHATSAPP); break;
            case "mail": ciudadano.setMedioDeNotificaion(MedioDeNotificaion.MAIL); break;
        }

        switch (context.formParam("forma")){
            case "cuandoSuceden" : ciudadano.setFormadenotificacion(CuandoNotificar.CUANDOSUCEDEN); break;
            case "sinApuro" : ciudadano.setFormadenotificacion(CuandoNotificar.SINAPUROS); break;
        }
        ciudadanoRepository.update(ciudadano);
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

    public NotifController(CiudadanoRepository ciudadanoRepository){
        this.ciudadanoRepository = ciudadanoRepository;
    }
}
