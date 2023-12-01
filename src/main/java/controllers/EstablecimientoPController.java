package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.EntidadPrestadoraRepository;
import models.dataBase.repositorios.EntidadRepository;
import models.dataBase.repositorios.EstablecimientoRepository;
import models.dataBase.repositorios.OrganismoControlRepository;
import models.dominio.entidades.Entidad;
import models.dominio.entidades.EntidadPrestadora;
import models.dominio.entidades.OrganismoDeControl;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EstablecimientoPController extends Controller implements ICrudViewsHandler {

    private OrganismoControlRepository repositorioOrganismoDeControl;
    private EntidadPrestadoraRepository repositorioEntidadPrestadora;
    private EntidadRepository repositorioEntidad;

    public EstablecimientoPController(EntidadRepository repositorioEntidad,OrganismoControlRepository repositorioOrganismo, EntidadPrestadoraRepository repositorioEntidadPrestadora){
        this.repositorioOrganismoDeControl = repositorioOrganismo;
        this.repositorioEntidadPrestadora = repositorioEntidadPrestadora;
        this.repositorioEntidad = repositorioEntidad;


    }

    public void indexTest(Context context) {
        context.render("establecimientosP.hbs");
    }

    @Override
    public void index(Context context) {
        OrganismoDeControl organismo = (OrganismoDeControl) this.repositorioOrganismoDeControl.findById(Long.parseLong(context.pathParam("idODC")));
        EntidadPrestadora entidadPrestadora = (EntidadPrestadora) this.repositorioEntidadPrestadora.findById(Long.parseLong(context.pathParam("idEP")));
        Entidad entidad = (Entidad) this.repositorioEntidad.findById(Long.parseLong(context.pathParam("idE")));

        Map<String,Object> model = new HashMap<>();
        model.put("organismo",organismo);
        model.put("entidadPrestadora",entidadPrestadora);
        model.put("entidad",entidad);
        model.put("establecimiento",entidad.getEstablecimientos());


        context.render("establecimientosP.hbs",model);
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
