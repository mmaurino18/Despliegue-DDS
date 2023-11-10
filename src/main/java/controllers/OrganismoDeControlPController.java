package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.OrganismoControlRepository;
import server.utils.ICrudViewsHandler;

import java.io.IOException;

public class OrganismoDeControlPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repository;

    public OrganismoDeControlPController(OrganismoControlRepository repositorio){
        this.repository = repositorio;
    }

    @Override
    public void index(Context context) {
        context.render("organismoDeControlP.hbs");
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
