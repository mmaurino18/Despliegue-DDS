package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.EntidadRepository;
import server.utils.ICrudViewsHandler;

import java.io.IOException;

public class EntidadPController extends Controller implements ICrudViewsHandler {

    private EntidadRepository repository;

    public EntidadPController(EntidadRepository repositorio){
        this.repository = repositorio;
    }

    public void indexTest(Context context) {
        context.render("entidadesP.hbs");
        //context.render("editP/edit_entidadP.hbs"); edicion
    }

    @Override
    public void index(Context context) {
        context.render("entidadesP.hbs");
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
