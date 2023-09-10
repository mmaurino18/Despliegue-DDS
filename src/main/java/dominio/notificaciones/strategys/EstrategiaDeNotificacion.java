package dominio.notificaciones.strategys;

import dominio.actores.Ciudadano;

public interface EstrategiaDeNotificacion {

    void enviar(Ciudadano destinatario, String mensajeAEnviar);
}
