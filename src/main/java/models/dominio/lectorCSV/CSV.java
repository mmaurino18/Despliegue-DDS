package models.dominio.lectorCSV;

import models.dominio.entidades.Entidad;
import models.dominio.entidades.EntidadPrestadora;
import models.dominio.entidades.Establecimiento;
import models.dominio.entidades.OrganismoDeControl;

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
        /*
        for (String dato : lineaCSV) {
            System.out.print(dato + " ");
        }
        System.out.println();
        */

        if (lineaCSV.length < 2) {
            System.out.println("Error: Datos insuficientes en la línea CSV.");
            return;
        }

        String tipo = lineaCSV[0];
        String nombre = lineaCSV[1];
        String padre = lineaCSV[2];

        switch (tipo) {
            case "OrganismoDeControl":
                OrganismoDeControl organismo = new OrganismoDeControl();
                break;
            case "EntidadPrestadora":
                EntidadPrestadora entidad = new EntidadPrestadora();
                break;
            case "Entidad":
                //Entidad entidad = new Entidad();
                break;
            case "Establecimiento":
                //Establecimiento establecimiento = new Establecimiento();
                break;
            default:
                System.out.println("Tipo de entidad no reconocido: " + tipo);
                break;
        }

    }



}
