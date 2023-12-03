package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dataBase.repositorios.EntidadPrestadoraRepository;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dominio.actores.Propietario;
import models.dominio.actores.TipoPropietario;
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
        Propietario propietario = super.PropietarioLogueadoSuper(context);
        Map<String,Object> model = new HashMap<>();

        if(propietario.getTipoPropietario().equals(TipoPropietario.ORGANISMO_DE_CONTROL)){

            OrganismoDeControl organismo = propietario.getOrganismosDeControl().get(0);
            model.put("entidadPrestadora",organismo.getEntidadesPrestadoras());
            model.put("propietarioOrganismo","ok");
            context.render("entidadesPrestadorasP.hbs",model);

        } else if (propietario.getTipoPropietario().equals(TipoPropietario.ENTIDAD_PRESTADORA)) {

            if(!propietario.getEntidadesPrestadoras().isEmpty()) {
                EntidadPrestadora entidadPrestadora = propietario.getEntidadesPrestadoras().get(0);
                model.put("entidadPrestadora", entidadPrestadora);
                model.put("propietarioEntidad", "ok");
                context.render("entidadesPrestadorasP.hbs", model);
            }
            else{
                model.put("vacio","ok");
                context.render("entidadesPrestadorasP.hbs",model);
            }
        }

    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        context.render("/editP/create_entidadPrestadora.hbs");
    }

    @Override
    public void save(Context context) throws IOException {
        EntidadPrestadora entidadPrestadora = new EntidadPrestadora();
        Propietario propietario = super.PropietarioLogueadoSuper(context);

        if(propietario.getTipoPropietario().equals(TipoPropietario.ORGANISMO_DE_CONTROL)){

            this.asignarParametrosCreate(entidadPrestadora, context);
            OrganismoDeControl organismo = propietario.getOrganismosDeControl().get(0);
            organismo.agregarEntidadPrestadora(entidadPrestadora);
            this.repositorioOrganismo.update(organismo);

            context.status(HttpStatus.CREATED);
            context.redirect("/entidadesPrestadorasP");

        } else if (propietario.getTipoPropietario().equals(TipoPropietario.ENTIDAD_PRESTADORA)) {
            //TODO
            //solo puede tener un entidad
        }
    }



    @Override
    public void edit(Context context) {
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("id")));

        Map<String,Object> model = new HashMap<>();
        model.put("entidadPrestadora",entidadPrestadora);

        context.render("/editP/edit_entidadPrestadoraP.hbs",model);

    }

    @Override
    public void update(Context context) {

        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("id")));
        this.asignarParametrosEdit(entidadPrestadora,context);
        this.repositorioEntidadPrestadora.update(entidadPrestadora);

        context.redirect("/entidadesPrestadorasP");
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
