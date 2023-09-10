package dominio.notificaciones.strategys;

import dominio.actores.Ciudadano;
import dominio.notificaciones.adapter.AdapterJavaxMail;
import dominio.notificaciones.adapter.MailAdapter;

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
