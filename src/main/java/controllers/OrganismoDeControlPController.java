package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dataBase.repositorios.PropietarioRepository;
import models.dominio.actores.Propietario;
import models.dominio.entidades.OrganismoDeControl;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.*;

public class OrganismoDeControlPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repository;

    public OrganismoDeControlPController(OrganismoControlRepository repositorio){
        this.repository = repositorio;
    }

    public void indexTest(Context context){
        context.render("organismoDeControlP.hbs");
    }

    @Override
    public void index(Context context) {
        PropietarioRepository repoPropietario = new PropietarioRepository();
        Propietario propietario = repoPropietario.findById(Long.parseLong("1"));

        Map<String, Object> model = new HashMap<>();
        model.put("organismoDeControl", propietario.getOrganismosDeControl());
        context.render("organismoDeControlP.hbs",model);
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
        OrganismoDeControl organismoDeControl = (OrganismoDeControl) this.repository.findById(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("organismoDeControl", organismoDeControl);
        context.render("/editP/edit_organismoDeControlP.hbs",model);

    }

    @Override
    public void update(Context context) {
        OrganismoDeControl organismoDeControl = (OrganismoDeControl) this.repository.findById(Long.parseLong(context.pathParam("id")));
        this.asignarParametroEdit(organismoDeControl, context);
        repository.update(organismoDeControl);
        context.redirect("/organismosDeControlP");
    }

    @Override
    public void delete(Context context) {

    }

    private void asignarParametroEdit(OrganismoDeControl organismoDeControl,Context context){

        if(context.formParam("nombre_edit") != null) {
            organismoDeControl.setNombre(context.formParam("nombre_edit"));
        }
    }
}
