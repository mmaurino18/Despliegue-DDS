package dominio;

import dominio.lectorCSV.CSV;

import java.io.IOException;

public class Pruevas {

    public static void main(String[] args) throws IOException {

        CSV lectordearchivoCSV = new CSV();

        String path = "C:\\Users\\Usuario\\Desktop\\diseño de sistemas\\2023-tpa-mama-grupo-16\\src\\main\\java\\dominio\\archivos\\archivo.csv";

        lectordearchivoCSV.leerArchivoCSV(path);
    }

}
