package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dataBase.repositorios.EntidadPrestadoraRepository;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dominio.entidades.EntidadPrestadora;
import models.dominio.entidades.OrganismoDeControl;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EntidadPrestadoraPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repositorioOrganismo;
    private EntidadPrestadoraRepository repositorioEntidadPrestadora;

    public EntidadPrestadoraPController(OrganismoControlRepository repositorioOrganismo, EntidadPrestadoraRepository repositorioEntidadPrestadora){
        this.repositorioOrganismo = repositorioOrganismo;
        this.repositorioEntidadPrestadora = repositorioEntidadPrestadora;
    }

    public void indexTest(Context context) {
        context.render("entidadesPrestadorasP.hbs");
    }

    @Override
    public void index(Context context) {
        OrganismoDeControl organismo = (OrganismoDeControl) this.repositorioOrganismo.findById(Long.parseLong(context.pathParam("idODC")));
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
        OrganismoDeControl organismo = (OrganismoDeControl) this.repositorioOrganismo.findById(Long.parseLong(context.pathParam("idODC")));
        Map<String,Object> model = new HashMap<>();
        model.put("organismo",organismo);
        context.render("/editP/create_entidadPrestadora.hbs", model);
    }

    @Override
    public void save(Context context) throws IOException {
        EntidadPrestadora entidadPrestadora = new EntidadPrestadora();
        this.asignarParametrosCreate(entidadPrestadora, context);
        OrganismoDeControl organismo = (OrganismoDeControl) this.repositorioOrganismo.findById(Long.parseLong(context.pathParam("idODC")));
        organismo.agregarEntidadPrestadora(entidadPrestadora);
        this.repositorioOrganismo.update(organismo);

        context.status(HttpStatus.CREATED);
        String idOrganismo = organismo.getId().toString();
        context.redirect("/organismosDeControlP/" +idOrganismo+ "/entidadesPrestadorasP");
    }

    @Override
    public void edit(Context context) {
        OrganismoDeControl organismo = (OrganismoDeControl) this.repositorioOrganismo.findById(Long.parseLong(context.pathParam("idODC")));
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("id")));

        Map<String,Object> model = new HashMap<>();
        model.put("organismo",organismo);
        model.put("entidadPrestadora",entidadPrestadora);

        context.render("/editP/edit_entidadPrestadoraP.hbs",model);

    }

    @Override
    public void update(Context context) {
        OrganismoDeControl organismo = (OrganismoDeControl) this.repositorioOrganismo.findById(Long.parseLong(context.pathParam("idODC")));
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("id")));
        this.asignarParametrosEdit(entidadPrestadora,context);
        this.repositorioEntidadPrestadora.update(entidadPrestadora);

        String idOrganismo = organismo.getId().toString();

        context.redirect("/organismosDeControlP/" +idOrganismo+ "/entidadesPrestadorasP");
    }

    @Override
    public void delete(Context context) {

    }

    private void asignarParametrosEdit(EntidadPrestadora entidadPrestadora, Context context){
        if(context.formParam("nombre_edit") != null && !context.formParam("nombre_edit").isEmpty()) {
            entidadPrestadora.setNombre(context.formParam("nombre_edit"));
        }
    }

    private void asignarParametrosCreate(EntidadPrestadora entidadPrestadora, Context context){
            entidadPrestadora.setNombre(context.formParam("nombre"));
    }
}
