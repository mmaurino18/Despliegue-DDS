package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.ComunidadRepository;
import models.dominio.comunidad.Comunidad;
import models.dominio.entidades.Entidad;
import models.dominio.rankings.*;
import server.utils.ICrudViewsHandler;

import java.util.*;

public class RankingController extends Controller implements ICrudViewsHandler {
    ComunidadRepository comunidadRepository;
    CalculadoraRankings calculadoraRankings = new CalculadoraRankings();
    @Override
    public void index(Context context) {
        context.render("ranking.hbs");
    }

    @Override
    public void show(Context context) {
        System.out.println("LLEEEEEGWE primero");
        List<Comunidad> comunidades = comunidadRepository.findAll();
        calculadoraRankings.setComunidades(comunidades);
        List <Tupla> tuplas = new ArrayList<>();
        System.out.println("LLEEEEEGWE");
        switch (context.formParam("ranking")){
            case "0":
               tuplas = calculadoraRankings.generarRanking(new EntidadesConMasIncidentes());
                break;
            case "1":
                tuplas = calculadoraRankings.generarRanking(new EntidadesConMayorPromedioDeCierre());
                break;
            default: break;
        }
        Map<String, Object> model = new HashMap<>();
        model.put("ranking",tuplas);
        context.render("mostrarRanking.hbs",model);
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
