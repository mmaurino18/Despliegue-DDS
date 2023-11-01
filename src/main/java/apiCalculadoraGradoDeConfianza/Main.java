package apiCalculadoraGradoDeConfianza;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        UsuarioApi usuario = new UsuarioApi();
        UsuarioApi otroUsuario = new UsuarioApi();

        otroUsuario.setId(2);
        usuario.setId(0);
        usuario.setPuntosDeConfianza(5);
        otroUsuario.setPuntosDeConfianza(5);

        IncidenteApi incidente = new IncidenteApi();
        incidente.setServicioAfectado(new PrestacionDeServicioApi());
        incidente.getServicioAfectado().setId(2);
        incidente.setUsuarioReportador(usuario);
        incidente.setUsuarioAnalizador(otroUsuario);
        incidente.setId(1);
        incidente.setFechaApertura("2023-01-01T00:00:00Z");
        incidente.setFechaCierre("2023-01-01T00:05:00Z");

        IncidenteApi otroIncidente = new IncidenteApi();
        otroIncidente.setServicioAfectado(new PrestacionDeServicioApi());
        otroIncidente.getServicioAfectado().setId(2);
        otroIncidente.setUsuarioReportador(usuario);
        otroIncidente.setUsuarioAnalizador(otroUsuario);
        otroIncidente.setId(2);
        otroIncidente.setFechaApertura("2023-01-01T00:06:00Z");
        otroIncidente.setFechaCierre("2023-01-02T00:01:00Z");

        List<IncidenteApi> incidentes = new ArrayList<>();
        incidentes.add(incidente);
        incidentes.add(otroIncidente);

        UsuarioApiRequest usuarioRequest = new UsuarioApiRequest();
        usuarioRequest.setIncidentes(incidentes);
        usuarioRequest.setUsuario(usuario);
        GradoDeConfiaza api = GradoDeConfiaza.instancia();
        UsuarioApiResponse usuarioApi = api.usuarioDevuelto(usuarioRequest);
       // System.out.println(usuarioApi.getNuevoPuntaje());
        if(usuarioApi != null){
             System.out.println(usuarioApi.getNuevoPuntaje());
        }else{
            System.out.println("ERROR, NO HAY USUARIOS");
        }
    }
}
