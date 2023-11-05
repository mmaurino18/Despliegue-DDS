package controllers;

import io.javalin.http.Context;
import io.javalin.http.UploadedFile;
import models.dataBase.repositorios.PropietarioRepository;
import models.dominio.actores.Propietario;
import models.dominio.lectorCSV.CSV;
import models.dominio.lectorCSV.LectorCSVAdapter;
import server.utils.ICrudViewsHandler;

import java.io.InputStream;
import io.javalin.http.UploadedFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

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

    public  void carga(Context context) throws IOException {
        LectorCSVAdapter servicio = new CSV();
        UploadedFile uploadedFile = context.uploadedFile("CSVfile");
        if(uploadedFile != null){
            String directorio = System.getProperty("user.dir");
            String path = "C:\\Users\\Usuario\\Desktop\\diseño de sistemas\\2023-tpa-mama-grupo-16\\src\\main\\java\\models\\dominio\\archivos";
            this.guardarArchivo(uploadedFile,uploadedFile.filename(),path);
            Propietario propietario = super.PropietarioLogueado(context);
            servicio.procesarArchivoCSV(propietario,path);
            propietarioRepository.save(propietario);
        }
        context.render("cargaMasiva.hbs");
    }

    public void guardarArchivo(UploadedFile uploadedFile, String nombre, String ruta) {
        try {
            Path targetPath = Paths.get(ruta, nombre);
            Files.copy(uploadedFile.content(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
