package dominio.notificaciones.adapter;

import dominio.actores.Ciudadano;

public interface NotificadorAdapter {

    void enviar(Ciudadano destinatario, String mensajeAEnviar);
}
