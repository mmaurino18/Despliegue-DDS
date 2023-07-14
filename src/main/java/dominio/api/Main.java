package api;
import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
            double lat =-31.4167;
            double lon =-64.1833;

            GeorefApi georefApi = GeorefApi.instancia();
            UbicacionResponse ubicacionResponse = georefApi.obtenerUbicacion(lat,lon);
            Municipio municipio = ubicacionResponse.getMunicipio();
            Provincia provincia = ubicacionResponse.getProvincia();
            System.out.println("Provincia: " + provincia.getNombre());
            System.out.println("Municipio: " + municipio.getNombre());
            //System.out.println(ubicacionResponse);
            /*
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
            */
    }
}

