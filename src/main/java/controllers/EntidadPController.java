package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.EntidadPrestadoraRepository;
import models.dataBase.repositorios.EntidadRepository;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dominio.entidades.EntidadPrestadora;
import models.dominio.entidades.OrganismoDeControl;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EntidadPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repositorioOrganismoDeControl;
    private EntidadPrestadoraRepository repositorioEntidadPrestadora;

    public EntidadPController(OrganismoControlRepository repositorioOrganismo, EntidadPrestadoraRepository repositorioEntidadPrestadora){
        this.repositorioOrganismoDeControl = repositorioOrganismo;
        this.repositorioEntidadPrestadora = repositorioEntidadPrestadora;
    }

    public void indexTest(Context context) {
        context.render("entidadesP.hbs");
        //context.render("editP/edit_entidadP.hbs"); edicion
    }

    @Override
    public void index(Context context) {
        OrganismoDeControl organismo = (OrganismoDeControl) this.repositorioOrganismoDeControl.findById(Long.parseLong(context.pathParam("idODC")));
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("idEP")));
        Map<String,Object> model = new HashMap<>();
        model.put("organismo",organismo);
        model.put("entidadPrestadora",entidadPrestadora);
        model.put("entidad",entidadPrestadora.getEntidades());
        context.render("entidadesP.hbs",model);
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
