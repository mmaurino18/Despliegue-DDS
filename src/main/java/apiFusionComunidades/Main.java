package apiFusionComunidades;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        FusionComunidades fusionComunidades = new FusionComunidades();
        CiudadanoApi ciudadano = new CiudadanoApi();
        CiudadanoApi ciudadano2 = new CiudadanoApi();
        CiudadanoApi ciudadano3 = new CiudadanoApi();

        ciudadano.setNombre("Matias");
        ciudadano2.setNombre("Messi");
        ciudadano3.setNombre("CR7");

        ServicioApi servicio = new ServicioApi();
        servicio.setNombre("Servicio1");
        EstablecimientoApi establecimiento = new EstablecimientoApi();
        establecimiento.setNombre("Establecimiento1");

        ComunidadApi comunidad1 = new ComunidadApi();
        comunidad1.setNombre("COMUNIDAD1");
        comunidad1.setMiembros(Arrays.asList(ciudadano2, ciudadano3));
        comunidad1.setEstablecimientos(Arrays.asList(establecimiento));
        comunidad1.setServicios(Arrays.asList(servicio));

        ComunidadApi comunidad2 = new ComunidadApi();
        comunidad2.setNombre("COMUNIDAD2");
        comunidad2.setMiembros(Arrays.asList(ciudadano3,ciudadano));
        comunidad2.setEstablecimientos(Arrays.asList(establecimiento));
        comunidad2.setServicios(Arrays.asList(servicio));
        //ComunidadApi comunidadFusionada = fusionComunidades.fusion(comunidad1,comunidad2);
        //System.out.println(comunidadFusionada.getNombre());
        List <ComunidadApi> comunidades = new ArrayList<>();
        comunidades.add(comunidad1);
        comunidades.add(comunidad2);
        List <ComunidadApi> propuestas_fusion = new ArrayList<>();
        propuestas_fusion = fusionComunidades.propuestas(comunidad1,comunidades);
        for (ComunidadApi comunidad : propuestas_fusion) {
            System.out.println(comunidad.getNombre());
        }

    }
}
