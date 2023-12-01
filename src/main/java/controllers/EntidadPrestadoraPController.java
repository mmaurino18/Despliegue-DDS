package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.EntidadPrestadoraRepository;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dominio.entidades.OrganismoDeControl;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EntidadPrestadoraPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repository;

    public EntidadPrestadoraPController(OrganismoControlRepository repositorio){
        this.repository = repositorio;
    }

    public void indexTest(Context context) {
        context.render("entidadesPrestadorasP.hbs");
    }

    @Override
    public void index(Context context) {
        OrganismoDeControl organismo = (OrganismoDeControl) this.repository.findById(Long.parseLong(context.pathParam("idODC")));
        Map<String,Object> model = new HashMap<>();
        model.put("organismo",organismo);
        model.put("entidadPrestadora",organismo.getEntidadesPrestadoras());

        context.render("entidadesPrestadorasP.hbs",model);
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
