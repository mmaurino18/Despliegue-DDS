package models.dominio.lectorCSV;

import models.dominio.actores.Propietario;

public interface LectorCSVAdapter {

    public void procesarArchivoCSV(Propietario propietario, String path);

}
