package models.dominio.notificaciones.strategys;

import models.dominio.actores.Ciudadano;
import models.dominio.notificaciones.adapter.AdapterJavaxMail;
import models.dominio.notificaciones.adapter.MailAdapter;

public class Mail implements EstrategiaDeNotificacion{

    private MailAdapter adapter;

    public Mail(String mailEmisor, String claveEmisor){
        this.adapter = new AdapterJavaxMail(mailEmisor, claveEmisor);
    }

    @Override
    public void enviar(Ciudadano destinatario, String mensajeAEnviar) {
        this.adapter.enviar(destinatario,mensajeAEnviar);
    }
}
