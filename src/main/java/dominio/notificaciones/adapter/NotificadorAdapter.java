package dominio.notificaciones.adapter;

import dominio.servicios.Miembro;

public interface NotificadorAdapter {

    void enviar(Miembro destinatario, String mensajeAEnviar);
}
