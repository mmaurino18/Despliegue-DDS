package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dataBase.repositorios.PropietarioRepository;
import models.dominio.actores.Propietario;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.*;

public class OrganismoDeControlPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repository;

    public OrganismoDeControlPController(OrganismoControlRepository repositorio){
        this.repository = repositorio;
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

    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }
}
