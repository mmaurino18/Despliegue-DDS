package models.dominio.notificaciones.strategys;

import models.dominio.actores.Ciudadano;
import models.dominio.notificaciones.adapter.AdapterTwilio;
import models.dominio.notificaciones.adapter.WhatsappAdapter;

public class Whatsapp implements EstrategiaDeNotificacion{

    private WhatsappAdapter adapter;

    public Whatsapp(String cuentaTwilio, String tokenTwilio, String numeroTwilio){
        this.adapter = new AdapterTwilio(cuentaTwilio, tokenTwilio, numeroTwilio);
    }

    @Override
    public void enviar(Ciudadano destinatario, String mensajeAEnviar) {
        this.adapter.enviar(destinatario, mensajeAEnviar);
    }
}
