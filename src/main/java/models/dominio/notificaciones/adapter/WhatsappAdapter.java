package dominio.notificaciones.adapter;

import dominio.actores.Ciudadano;

public interface WhatsappAdapter {

    void enviar(Ciudadano destinatario, String mensajeAEnviar);
}
