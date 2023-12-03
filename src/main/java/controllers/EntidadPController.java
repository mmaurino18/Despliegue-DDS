package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dataBase.repositorios.EntidadPrestadoraRepository;
import models.dataBase.repositorios.EntidadRepository;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dominio.actores.Propietario;
import models.dominio.actores.TipoPropietario;
import models.dominio.entidades.Entidad;
import models.dominio.entidades.EntidadPrestadora;
import models.dominio.entidades.OrganismoDeControl;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EntidadPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repositorioOrganismoDeControl;
    private EntidadPrestadoraRepository repositorioEntidadPrestadora;
    private EntidadRepository repositorioEntidad;


    public EntidadPController(OrganismoControlRepository repositorioOrganismo, EntidadPrestadoraRepository repositorioEntidadPrestadora, EntidadRepository repositorioEntidad){
        this.repositorioOrganismoDeControl = repositorioOrganismo;
        this.repositorioEntidadPrestadora = repositorioEntidadPrestadora;
        this.repositorioEntidad = repositorioEntidad;

    }

    public void indexTest(Context context) {
        context.render("entidadesP.hbs");
        //context.render("editP/edit_entidadP.hbs"); edicion
    }

    @Override
    public void index(Context context) {

        Map<String,Object> model = new HashMap<>();
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("idEP")));
        model.put("entidadPrestadora",entidadPrestadora);
        model.put("entidad",entidadPrestadora.getEntidades());
        context.render("entidadesP.hbs",model);

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("idEP")));
        Map<String,Object> model = new HashMap<>();
        model.put("entidadPrestadora",entidadPrestadora);

        context.render("/editP/create_entidadP.hbs",model);
    }

    @Override
    public void save(Context context) throws IOException {

        Entidad entidad = new Entidad();
        this.asignarParametrosCreate(entidad,context);
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("idEP")));
        entidadPrestadora.agregarEntidad(entidad);
        this.repositorioEntidadPrestadora.update(entidadPrestadora);

        String idEntidadPrestadora = entidadPrestadora.getId().toString();

        context.status(HttpStatus.CREATED);
        context.redirect("/entidadesPrestadorasP/"+idEntidadPrestadora+"/entidadesP");

    }

    @Override
    public void edit(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("idEP")));
        Entidad entidad = (Entidad) this.repositorioEntidad.findById(Long.parseLong(context.pathParam("id")));

        Map<String,Object> model = new HashMap<>();
        model.put("entidadPrestadora",entidadPrestadora);
        model.put("entidad",entidad);

        context.render("/editP/edit_entidadP.hbs",model);
    }

    @Override
    public void update(Context context) {
        Entidad entidad = (Entidad) this.repositorioEntidad.findById(Long.parseLong(context.pathParam("id")));
        this.asignarParametrosEdit(entidad, context);
        this.repositorioEntidad.update(entidad);

        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("idEP")));
        String idEntidadPrestadora = entidadPrestadora.getId().toString();

        context.redirect("/entidadesPrestadorasP/"+idEntidadPrestadora+"/entidadesP");
    }

    @Override
    public void delete(Context context) {

    }

    private void asignarParametrosCreate(Entidad entidad, Context context){
        entidad.setNombre(context.formParam("nombre"));
    }

    private void asignarParametrosEdit(Entidad entidad, Context context){
        if(context.formParam("nombre") != null && !context.formParam("nombre").isEmpty()) {
            entidad.setNombre(context.formParam("nombre"));
        }
    }
}
