package dominio.lectorCSV;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class CSV {

    public void leerArchivoCSV(String path) throws IOException {

        FileInputStream archivoDeEntrada = null;
        InputStreamReader lectorDeCaracteres = null;
        BufferedReader lectorDeLineas = null;

        try{
            archivoDeEntrada = new FileInputStream(path);
            lectorDeCaracteres = new InputStreamReader(archivoDeEntrada);
            lectorDeLineas = new BufferedReader(lectorDeCaracteres);

            String linea;
            while((linea = lectorDeLineas.readLine()) != null){
                String[] datos = linea.split(",");

                procesarDatos(datos);
            }

        } catch (Exception e) {
            System.out.println(" no se pudo leer o econtrar el archivo ");
        } finally {
            if (lectorDeLineas != null) {
                lectorDeLineas.close();
            }
            if (lectorDeCaracteres != null) {
                lectorDeCaracteres.close();
            }
            if (archivoDeEntrada != null) {
                archivoDeEntrada.close();
            }
        }
    }

    // por el momento solo imprime por pantalla
    public void procesarDatos(String[] lineaCSV){
        for (String dato : lineaCSV) {
            System.out.print(dato + " ");
        }
        System.out.println();
    }

}
