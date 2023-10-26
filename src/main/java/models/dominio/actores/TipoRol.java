package dominio.actores;

import io.javalin.security.RouteRole;

public enum TipoRol implements RouteRole {
    CIUDADANO,
    AFECTADO,
    OBSERVADOR,
    PROPIETARIO
}
