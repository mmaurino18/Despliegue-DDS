package apiCalculadoraGradoDeConfianza;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Date;

@Getter
@Setter
public class IncidenteApi {
    public long id;
    public String fechaApertura;
    public UsuarioApi usuarioReportador;
    public String fechaCierre ;
    public UsuarioApi usuarioAnalizador  ;
    public PrestacionDeServicioApi servicioAfectado;
}
