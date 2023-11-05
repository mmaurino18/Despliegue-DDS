package controllers;

import io.javalin.http.Context;
import server.utils.ICrudViewsHandler;

public class HomePropietarioController extends Controller implements ICrudViewsHandler {

    public HomePropietarioController(){

    }
    @Override
    public void index(Context context) {
        context.render("homePropietario.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) {

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
