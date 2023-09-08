package dominio.validacionContrasenia;
import java.util.ArrayList;
public class CambiarContraseña {
    public boolean cambioDeContraseña(String contra_nueva, String contra_actual) {
        ValidacionCaracteres validacion = new ValidacionCaracteres();
        PeoresContras peores_contras = new PeoresContras();
        if(validacion.validar(contra_nueva) && !peores_contras.estaContenida(contra_nueva) && !this.sonIguales(contra_nueva,contra_actual)) {
            return true;
        } else {
            System.out.println("Las contraseña contiene los siguientes errores:");
            validacion.errores(contra_nueva);
            peores_contras.error(contra_nueva);
            this.errores(contra_nueva,contra_actual);
            return false;
        }
    }

    private boolean sonIguales(String contra_nueva, String contra_actual){
        if(contra_nueva == contra_actual) {
            //System.out.println("* Las contraseña nueva es igual a la actual.Tienen que ser diferentes");
            return true;
        }
        else{
            return false;
        }
    }

    /*private boolean esUnaContraAntigua(String contra_nueva, ArrayList<String> contra_antiguas){
        if(contra_antiguas.contains(contra_nueva)){
            //System.out.println("* Las contraseña nueva es igual a una contraseña anterior.No se puede repetir");
            return true;
        }else {
            return false;
        }
    }*/
    private void errores(String contra_nueva, String contra_actual){
        if(this.sonIguales(contra_nueva,contra_actual)){
            System.out.println("* Las contraseña nueva es igual a la actual.Tienen que ser diferentes");
        }
    }
}