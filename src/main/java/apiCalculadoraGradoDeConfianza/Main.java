package apiCalculadoraGradoDeConfianza;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        UsuarioApi usuario = new UsuarioApi();
        usuario.setNombre("string");
        usuario.setApellido("string");
        usuario.setId(0);
        usuario.setPuntosDeConfianza(0);


        UsuarioApi usuarioReportador = new UsuarioApi();
        usuarioReportador.setNombre("Matias");
        usuarioReportador.setApellido("Mauriño");
        usuarioReportador.setId(0);
        usuarioReportador.setPuntosDeConfianza(0);

        UsuarioApi usuarioAnalizador = new UsuarioApi();
        usuarioAnalizador.setNombre("Lionel");
        usuarioAnalizador.setApellido("Messi");
        usuarioAnalizador.setId(0);
        usuarioAnalizador.setPuntosDeConfianza(0);


        PrestacionDeServicioApi servicioAfectado = new PrestacionDeServicioApi();
        servicioAfectado.setId(0);


        IncidenteApi incidente = new IncidenteApi();
        incidente.setId(0);
        incidente.setFechaApertura("2023-11-14T21:59:07.112Z");
        incidente.setFechaCierre("2023-11-14T21:59:07.112Z");
        incidente.setUsuarioReportador(usuarioReportador);
        incidente.setUsuarioAnalizador(usuarioAnalizador);
        incidente.setServicioAfectado(servicioAfectado);


        List<IncidenteApi> incidentes = new ArrayList<>();
        incidentes.add(incidente);

        UsuarioApiRequest usuarioRequest = new UsuarioApiRequest();
        usuarioRequest.setUsuario(usuario);
        usuarioRequest.setIncidentes(incidentes);

        try {
            GradoDeConfiaza api = GradoDeConfiaza.instancia();
            UsuarioApiResponse respuesta = api.usuarioDevuelto(usuarioRequest);


            if (respuesta != null) {
                System.out.println("Respuesta de la API: " + respuesta.getNuevoPuntaje());
            } else {
                System.out.println("No se recibió respuesta de la API.");
            }
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Ocurrió un error al llamar a la API.");
        }
    }
}
