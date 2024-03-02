package controllers;

import io.javalin.http.Context;
import models.dataBase.repositorios.CiudadanoRepository;
import models.dataBase.repositorios.ComunidadRepository;
import models.dataBase.repositorios.IncidenteRepository;
import models.dominio.entidades.Entidad;
import models.dominio.rankings.*;
import models.dominio.servicios.Incidente;
import server.utils.ICrudViewsHandler;

import java.io.IOException;
import java.util.*;

public class RankingController extends Controller implements ICrudViewsHandler {

    private IncidenteRepository incidenteRepository;
    private CalculadoraRankings calculadoraRankings = new CalculadoraRankings();
    private GeneradorPDF generadorPDF = new GeneradorPDF();
    @Override
    public void index(Context context) {
        context.render("ranking.hbs");
    }

    @Override
    public void show(Context context) throws IOException {
        List <Incidente> incidentes = incidenteRepository.findAll();
        calculadoraRankings.setIncidentes(incidentes);
        List <Tupla> tuplas = new ArrayList<>();
        switch (context.formParam("ranking")){
            case "0":
                tuplas = calculadoraRankings.generarRanking(new EntidadesConMasIncidentes());
                generadorPDF.pdfRakingMasIncidentes(tuplas, "C:\\Users\\Usuario\\Downloads\\ranking_mas_incidentes.pdf");
                break;
            case "1":
               tuplas = calculadoraRankings.generarRanking(new EntidadesConMayorPromedioDeCierre());
                generadorPDF.pdfRakingMayorPromedioCierre(tuplas, "C:\\Users\\Usuario\\Downloads\\ranking_mayor_promedio_cierre.pdf");
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
    public RankingController(IncidenteRepository incidenteRepository ){
        this.incidenteRepository= incidenteRepository;
    }
}
