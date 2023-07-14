package dominio.notificaciones.adapter;

import dominio.comunidad.Miembro;

public interface NotificadorAdapter {

    void enviar(Miembro destinatario, String mensajeAEnviar);
}
