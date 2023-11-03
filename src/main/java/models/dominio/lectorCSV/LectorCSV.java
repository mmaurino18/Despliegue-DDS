package models.dominio.lectorCSV;

import models.dominio.actores.Propietario;

public interface LectorCSV {

    public void procesarArchivoCSV(Propietario propietario, String path);

}
