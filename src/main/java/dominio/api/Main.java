package api;

import api.*;

import java.io.IOException;
import java.util.Scanner;
;

public class Main {
    public static void main(String[] args) throws IOException {
            GeorefApi georefApi = GeorefApi.instancia();
            ListaProvincias listaProvincias = georefApi.listadoDeProvincias();
            for(Provincia provincia:listaProvincias.getProvincias()){
                System.out.println("id:"+ provincia.getId() + ", nombre: " + provincia.getNombre());
            }
            System.out.println("Ingrese el id de la provincia para ver sus municipios ");
            Scanner scanner = new Scanner(System.in);
            int id = Integer.parseInt(scanner.next());
            ListaMunicipios listaMunicipios = georefApi.listadoDeMunicipiosDeProvincia(id);
            int a =1;
            for(Municipio municipio:listaMunicipios.getMunicipios()){
                System.out.println(a + "-" + municipio.getNombre());
                a++;
            }

    }
}

