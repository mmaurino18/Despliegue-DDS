package apiCalculadoraGradoDeConfianza;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws IOException {
        UsuarioApi usuario = new UsuarioApi();
        UsuarioApi otroUsuario = new UsuarioApi();
        UsuarioApi buenUsuario = new UsuarioApi();

        otroUsuario.setId(2);
        usuario.setId(0);
        buenUsuario.setId(3);
        usuario.setPuntosDeConfianza(5);
        otroUsuario.setPuntosDeConfianza(5);
        buenUsuario.setPuntosDeConfianza(5);

        IncidenteApi incidente = new IncidenteApi();
        incidente.setServicioAfectado(new PrestacionDeServicioApi());
        incidente.getServicioAfectado().setId(2);
        incidente.setUsuarioReportador(otroUsuario);
        incidente.setUsuarioAnalizador(usuario);
        incidente.setId(1);
        incidente.setFechaApertura(LocalDateTime.of(2023, 1, 1, 0, 0, 0));
        incidente.setFechaCierre(LocalDateTime.of(2023, 1, 1, 0, 5, 0));

        IncidenteApi otroIncidente = new IncidenteApi();
        otroIncidente.setServicioAfectado(new PrestacionDeServicioApi());
        otroIncidente.getServicioAfectado().setId(2);
        otroIncidente.setUsuarioReportador(otroUsuario);
        otroIncidente.setUsuarioAnalizador(usuario);
        otroIncidente.setId(2);
        otroIncidente.setFechaApertura(LocalDateTime.of(2023, 1, 1, 0, 6, 0));
        otroIncidente.setFechaCierre(LocalDateTime.of(2023, 1, 2, 0, 1, 0));

        List<IncidenteApi> incidentes = new ArrayList<>();
        incidentes.add(incidente);
        incidentes.add(otroIncidente);

        usuario.setIncidentes(incidentes);
        GradoDeConfiazaApi api = GradoDeConfiazaApi.instancia();
        UsuarioApi usuarioApi = api.usuarioDevuelto(usuario);
        if(usuarioApi != null){
             System.out.println(usuarioApi.puntosDeConfianza);
        }else{
            System.out.println("ERROR, NO HAY USUARIOs");
        }
    }
}
