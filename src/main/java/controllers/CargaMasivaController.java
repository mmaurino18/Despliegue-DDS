package controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import models.dataBase.repositorios.PropietarioRepository;
import models.dominio.actores.Propietario;
import models.dominio.actores.Usuario;
import models.dominio.lectorCSV.CSV;
import models.dominio.lectorCSV.LectorCSVAdapter;
import server.utils.ICrudViewsHandler;

import java.io.InputStream;
import io.javalin.http.UploadedFile;

import javax.persistence.NoResultException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.HashMap;
import java.util.Map;

public class CargaMasivaController extends Controller implements ICrudViewsHandler {

    private PropietarioRepository propietarioRepository;

    public CargaMasivaController (PropietarioRepository propietarioRepository){
        this.propietarioRepository = propietarioRepository;
    }

    @Override
    public void index(Context context) {
        context.render("cargaMasiva.hbs");
    }

    @Override
    public void show(Context context) {

    }

    @Override
    public void create(Context context) {

    }

    @Override
    public void save(Context context) throws IOException {
        LectorCSVAdapter servicio = new CSV();
        UploadedFile uploadedFile = context.uploadedFile("CSVfile");
        Map<String, Object> model = new HashMap<>();
        String notif;

        if(uploadedFile != null){
            System.out.println("ENTRE1");
            Propietario propietario = this.propietarioLogueadoDirecto(context);
            System.out.println("nombre Usaurio -> " + propietario.getNombre());
            String path = "src\\main\\java\\models\\dominio\\archivos";
            this.guardarArchivo(uploadedFile,uploadedFile.filename(),path);

            String pathM = path + "\\" + uploadedFile.filename();
            servicio.procesarArchivoCSV(propietario,pathM);
            propietarioRepository.save(propietario);
            notif = "Correcto";
            model.put("notic", notif);
            context.render("cargaMasiva.hbs",model);
        }
        else {
            notif = "Incorrecto";
            model.put("notif", notif);
            context.render("cargaMasiva.hbs",model);
        }
    }

    @Override
    public void edit(Context context) {
        context.render("estructuraCsv.hbs");
    }

    @Override
    public void update(Context context) {

    }

    @Override
    public void delete(Context context) {

    }

    public void guardarArchivo(UploadedFile uploadedFile, String nombre, String ruta) {
        try {
            Path targetPath = Paths.get(ruta, nombre);
            Files.copy(uploadedFile.content(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Usuario UsuarioLogueadoDirecto(Context context){
        return entityManager().find(Usuario.class, Long.parseLong(("" + context.sessionAttribute("id_usuario"))));
    }

    public Propietario propietarioLogueadoDirecto(Context context){
        try {
            String hql = "SELECT c FROM Propietario c JOIN c.usuario u WHERE u.id = :usuarioId";
            return entityManager().createQuery(hql, Propietario.class)
                    .setParameter("usuarioId",  Long.parseLong(("" + context.sessionAttribute("id_usuario"))))
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }
}

