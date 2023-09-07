package dominio.notificaciones.adapter;

import dominio.comunidad.Ciudadano;

public interface NotificadorAdapter {

    void enviar(Ciudadano destinatario, String mensajeAEnviar);
}
