package apiCalculadoraGradoDeConfianza;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class IncidenteApi {
    public long id;
    public LocalDateTime fechaApertura;
    public UsuarioApi usuarioReportador;
    public LocalDateTime fechaCierre ;
    public UsuarioApi usuarioAnalizador  ;
    public PrestacionDeServicioApi servicioAfectado;
}
