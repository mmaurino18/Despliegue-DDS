package controllers;

import io.javalin.http.Context;
import models.dominio.actores.Propietario;
import models.dominio.actores.TipoPropietario;
import models.dominio.entidades.EntidadPrestadora;
import models.dominio.entidades.OrganismoDeControl;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SubidosPController extends Controller implements ICrudViewsHandler {

    @Override
    public void index(Context context) {
        Propietario propietario = super.PropietarioLogueadoSuper(context);

        if(propietario.getTipoPropietario().equals(TipoPropietario.ORGANISMO_DE_CONTROL)){
            context.redirect("/organismosDeControlP");
        } else if (propietario.getTipoPropietario().equals(TipoPropietario.ENTIDAD_PRESTADORA)) {
            context.redirect("/entidadesPrestadorasP");
        } else if(propietario.getTipoPropietario().equals(TipoPropietario.SIN_TIPO)) {
            context.render("/editP/sinTipoDePropietario.hbs");
        }

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
