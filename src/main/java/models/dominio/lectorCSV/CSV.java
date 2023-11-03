package models.dominio.lectorCSV;

import models.dominio.actores.Propietario;
import models.dominio.entidades.Entidad;
import models.dominio.entidades.EntidadPrestadora;
import models.dominio.entidades.Establecimiento;
import models.dominio.entidades.OrganismoDeControl;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CSV implements LectorCSV{

    public CSV() {

    }
    public List<String> lectorDeCSV(String path) throws IOException {
        FileInputStream archivoDeEntrada = null;
        InputStreamReader lectorDeCaracteres = null;
        BufferedReader lectorDeLineas = null;
        List<String> lineas = null;
        try {
            archivoDeEntrada = new FileInputStream(path);
            lectorDeCaracteres = new InputStreamReader(archivoDeEntrada);
            lectorDeLineas = new BufferedReader(lectorDeCaracteres);
            lineas = new ArrayList<>();

            String linea;
            boolean primeraLinea = true;
            while ((linea = lectorDeLineas.readLine()) != null) {
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                lineas.add(linea);
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
        return lineas;
    }


    // por el momento solo imprime por pantalla
    public void procesarDatos(String[] lineaCSV) {
        for (String dato : lineaCSV) {
            System.out.print(dato + " ");
        }
        System.out.println();

        // empieza en 1, tambien la consulta de listas
        System.out.println(lineaCSV.length);
    }

    public void Mapeo(Propietario propietario, String[] lineaCSV, OrganismoDeControl organismoDeControl, EntidadPrestadora entidadPrestadora) {
        String tipo = lineaCSV[0];
        String nombre = lineaCSV[1];
        String padre = lineaCSV[2];

        switch (tipo) {
            case "OrganismoDeControl":
                if (Objects.equals(padre, "Sin Padre")) {
                    organismoDeControl.setNombre(nombre);
                    propietario.agregarOrganismoDeControl(organismoDeControl);
                    System.out.println("se agrego organismo " + nombre);
                }
                break;
            case "EntidadPrestadora":
                if (Objects.equals(padre, "Sin Padre")) {
                    entidadPrestadora.setNombre(nombre);
                    propietario.agregarEntidadPrestadora(entidadPrestadora);
                } else {
                    entidadPrestadora.setNombre(nombre);
                    organismoDeControl.agregarEntidadPrestadora(entidadPrestadora);
                    System.out.println("se agrego entidadPrestadora " + nombre);
                }
                break;
            case "Entidad":
                System.out.println("se agrego entidad " + nombre);
                //
                break;
            case "Establecimiento":
                System.out.println("se agrego establecimiento " + nombre);
                //
                break;
            default:
                System.out.println("Tipo de entidad no reconocido: " + tipo);
                break;
        }
    }

    public void imprimirLineas(List<String> lista) {
        for (String elemento : lista) {
            System.out.println(elemento);
        }
    }

    public void mapearDatos(List<String> lista, Propietario propietario){
        OrganismoDeControl organismoDeControl = new OrganismoDeControl();
        EntidadPrestadora entidadPrestadora = new EntidadPrestadora();
        for (String linea : lista) {
            String[] datos = linea.split(",");
            this.Mapeo(propietario,datos,organismoDeControl,entidadPrestadora);
        }
    }


    @Override
    public void procesarArchivoCSV(Propietario propietario, String path) {

    }
}
