package dominio.notificaciones.adapter;

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import dominio.comunidad.Ciudadano;

public class WhatsappAdapter implements NotificadorAdapter{

    private final String ACCOUNT_SID;
    private final String AUTH_TOKEN;
    private final String PHONE_NUMBER_TWILIO;

    public WhatsappAdapter(String account_sid, String auth_token, String phone_number_twilio){
        this.ACCOUNT_SID = account_sid;
        this.AUTH_TOKEN = auth_token;
        this.PHONE_NUMBER_TWILIO = phone_number_twilio;
        Twilio.init(ACCOUNT_SID,AUTH_TOKEN);
    }

    @Override
    public void enviar(Ciudadano destinatario, String mensajeAEnviar) {
        Message message = Message.creator(
                new com.twilio.type.PhoneNumber("whatsapp:+" + destinatario.getNumeroDeTelefono()),
                new com.twilio.type.PhoneNumber("whatsapp:+" + PHONE_NUMBER_TWILIO),
                mensajeAEnviar).
                create();

        System.out.println(message.getSid());
    }
}
