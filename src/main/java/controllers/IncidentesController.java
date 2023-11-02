package controllers;

import DTOs.IncidenteDTO;
import models.dataBase.repositorios.*;
import models.dominio.actores.Ciudadano;
import models.dominio.actores.Usuario;
import models.dominio.servicios.Incidente;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import models.dominio.servicios.PrestacionDeServicio;
import server.exception.AccessDeniedException;
import server.utils.ICrudViewsHandler;

import java.time.LocalDateTime;
import java.util.*;

public class IncidentesController extends Controller implements ICrudViewsHandler {
    private IncidenteRepository repositorioDeIncidentes;
    private PrestacionRepository servicioRepository;

    private CiudadanoRepository usuarioRepository;

    public IncidentesController(IncidenteRepository repositorioDeIncidentes, PrestacionRepository servicioRepository, CiudadanoRepository usuarioRepository) {
        this.repositorioDeIncidentes = repositorioDeIncidentes;
        this.servicioRepository= servicioRepository;
        this.usuarioRepository = usuarioRepository;

    }

    @Override
    public void index(Context context) {
        Map<String, Object> model = new HashMap<>();
        List<Incidente> incidentes = this.repositorioDeIncidentes.findAll().stream().filter(incidente -> incidente.getEstadoIncidente().equals(true)).toList();
        List<IncidenteDTO> incidenteDTOS = this.convertirADTO(incidentes);
        model.put("incidenteDTO", incidenteDTOS);
        context.render("comunidad.hbs", model);
    }

    private List<IncidenteDTO> convertirADTO(List<Incidente> incidentes) {
        List<IncidenteDTO> convertidos= new ArrayList<>();
        incidentes.forEach(in ->
                convertidos.add(new IncidenteDTO(in.getId(),in.getServicio().getNombre()
                        ,in.getObservaciones(),in.fechaHora(),
                        in.getEstablecimiento().getNombre()))

        );
        System.out.print(convertidos.size());
        return  convertidos;
    }

    @Override
    public void show(Context context) {
        Incidente incidente = (Incidente) this.repositorioDeIncidentes.findById(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidentes.hbs", model);
    }

    @Override
    public void create(Context context) {
        Usuario usuarioLogueado = super.usuarioLogueado(context);

        //if(usuarioLogueado == null || !usuarioLogueado.getRol().tenesPermiso("abrir_incidente")) {
         //   throw new AccessDeniedException();
       // }

        List<PrestacionDeServicio> prestaciones = this.servicioRepository.findAll();
        Map<String, Object> model = new HashMap<>();
        model.put("servicio", prestaciones);
        context.render("abrirIncidente.hbs", model);
    }

    @Override
    public void save(Context context) {

            Incidente incidente = new Incidente();
            this.asignarParametros(incidente, context);
            //Ciudadano usuario = this.usuarioRepository.findByUsuarioID(context.sessionAttribute("usuario_id"));
            //usuario.getIncidentesReportados().add(incidente);
            this.repositorioDeIncidentes.save(incidente);
            //this.usuarioRepository.save(usuario);
            context.status(HttpStatus.CREATED);
            context.redirect("/incidentes");


    }

    @Override
    public void edit(Context context) {
        Incidente incidente = (Incidente) this.repositorioDeIncidentes.findById(Long.parseLong(context.pathParam("id")));
        Map<String, Object> model = new HashMap<>();
        model.put("incidente", incidente);
        context.render("incidentes/incidentes.hbs", model);
    }

    @Override
    public void update(Context context) {
        Incidente incidente = (Incidente) this.repositorioDeIncidentes.findById(Long.parseLong(context.pathParam("id")));
        this.asignarParametros(incidente, context);
        this.repositorioDeIncidentes.update(incidente);
        context.redirect("/incidentes");
    }

    @Override
    public void delete(Context context) {
        System.out.print("entre al delete");
        Incidente incidente = (Incidente) this.repositorioDeIncidentes.findById(Long.parseLong(context.pathParam("id")));
        System.out.print("encontre el incidente");
        incidente.setEstadoIncidente(false);
        incidente.setFechaCierre(LocalDateTime.now());
        this.repositorioDeIncidentes.save(incidente);
        System.out.print("cambie el incidente");
        context.redirect("/incidentes");
    }

    private void asignarParametros(Incidente incidente, Context context) {

        if(!Objects.equals(context.formParam("nombre"), "")) {
            incidente.setObservaciones(context.formParam("observaciones"));
            incidente.setNombreIncidente(context.formParam("nombre"));
            System.out.print("nombre y obs asignados");
            incidente.setPrestacionDeServicioIncidente(servicioRepository.findByNombre(context.formParam("servicio")));
            System.out.print("asignado el servicio");
        }
        System.out.print("parametros asignados");
    }
}
