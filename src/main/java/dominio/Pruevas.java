package dominio;

import dominio.lectorCSV.CSV;

import java.io.IOException;

public class Pruevas {

    public static void main(String[] args) throws IOException {

        CSV lectordearchivoCSV = new CSV();

        String path = "";

        lectordearchivoCSV.leerArchivoCSV(path);
    }

}
