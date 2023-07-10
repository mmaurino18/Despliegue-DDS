package dominio.notificaciones.adapter;

import dominio.notificaciones.Notificacion;

public interface NotificadorAdapter {

    void enviar(Notificacion notificacion);
}
