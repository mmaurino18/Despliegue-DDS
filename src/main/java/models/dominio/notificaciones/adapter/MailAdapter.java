package dominio.notificaciones.adapter;

import dominio.actores.Ciudadano;

public interface MailAdapter {

    void enviar(Ciudadano destinatario, String mensajeAEnviar);
}
