package controllers;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dataBase.repositorios.EntidadPrestadoraRepository;
import models.dataBase.repositorios.EntidadRepository;
import models.dataBase.repositorios.EstablecimientoRepository;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dominio.entidades.Entidad;
import models.dominio.entidades.EntidadPrestadora;
import models.dominio.entidades.Establecimiento;
import models.dominio.entidades.OrganismoDeControl;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EstablecimientoPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repositorioOrganismoDeControl;
    private EntidadPrestadoraRepository repositorioEntidadPrestadora;
    private EntidadRepository repositorioEntidad;
    private EstablecimientoRepository repositorioEstablecimiento;

    public EstablecimientoPController(EntidadRepository repositorioEntidad,OrganismoControlRepository repositorioOrganismo, EntidadPrestadoraRepository repositorioEntidadPrestadora, EstablecimientoRepository repositorioEstablecimiento){
        this.repositorioOrganismoDeControl = repositorioOrganismo;
        this.repositorioEntidadPrestadora = repositorioEntidadPrestadora;
        this.repositorioEntidad = repositorioEntidad;
        this.repositorioEstablecimiento = repositorioEstablecimiento;
    }

    public void indexTest(Context context) {
        context.render("establecimientosP.hbs");
    }

    @Override
    public void index(Context context) {
        Entidad entidad = (Entidad) this.repositorioEntidad.findById(Long.parseLong(context.pathParam("idE")));
        Map<String,Object> model = new HashMap<>();
        model.put("entidad",entidad);
        model.put("establecimiento",entidad.getEstablecimientos());

        context.render("establecimientosP.hbs",model);
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {
        Entidad entidad = (Entidad) this.repositorioEntidad.findById(Long.parseLong(context.pathParam("idE")));
        Map<String,Object> model = new HashMap<>();
        model.put("entidad",entidad);

        context.render("/editP/create_establecimientoP.hbs",model);
    }

    @Override
    public void save(Context context) throws IOException {
        Establecimiento establecimiento = new Establecimiento();
        this.asignarParametrosCreate(establecimiento, context);
        Entidad entidad = (Entidad) this.repositorioEntidad.findById(Long.parseLong(context.pathParam("idE")));
        entidad.agregarEstablecimiento(establecimiento);
        this.repositorioEntidad.update(entidad);

        String idEntidad = entidad.getId().toString();
        context.status(HttpStatus.CREATED);
        context.redirect("/entidadesP/"+idEntidad+"/establecimientosP");
    }

    @Override
    public void edit(Context context) {
        Entidad entidad = (Entidad) this.repositorioEntidad.findById(Long.parseLong(context.pathParam("idE")));
        Establecimiento establecimiento = (Establecimiento) this.repositorioEstablecimiento.findById(Long.parseLong(context.pathParam("id")));

        Map<String,Object> model = new HashMap<>();
        model.put("entidad",entidad);
        model.put("establecimiento", establecimiento);

        context.render("/editP/edit_establecimientoP.hbs",model);
    }

    @Override
    public void update(Context context) {
        Establecimiento establecimiento = (Establecimiento) this.repositorioEstablecimiento.findById(Long.parseLong(context.pathParam("id")));
        this.asignarParametrosEdit(establecimiento, context);
        this.repositorioEstablecimiento.update(establecimiento);

        Entidad entidad = (Entidad) this.repositorioEntidad.findById(Long.parseLong(context.pathParam("idE")));
        String idEntidad = entidad.getId().toString();

        context.redirect("/entidadesP/"+idEntidad+"/establecimientosP");
    }

    @Override
    public void delete(Context context) {

    }

    private void asignarParametrosCreate(Establecimiento establecimiento, Context context){
        establecimiento.setNombre(context.formParam("nombre"));
        establecimiento.setDescripcion(context.formParam("descripcion"));
    }

    private void asignarParametrosEdit(Establecimiento establecimiento, Context context){
        if(context.formParam("nombre") != null && !context.formParam("nombre").isEmpty()) {
            establecimiento.setNombre(context.formParam("nombre"));
        }
        if(context.formParam("descripcion") != null && !context.formParam("descripcion").isEmpty()) {
            establecimiento.setDescripcion(context.formParam("descripcion"));
        }
    }
}
