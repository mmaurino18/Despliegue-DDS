package dominio.validacionContrasenia;

public class ValidacionContraseña {
    public boolean validar(String contra_nueva) {
        ValidacionCaracteres validacion = new ValidacionCaracteres();
        PeoresContras peores_contras = new PeoresContras();
        if (validacion.validar(contra_nueva) && !peores_contras.estaContenida(contra_nueva)) {
            return true;
        } else {
            System.out.println("Las contraseña contiene los siguientes errores:");
            validacion.errores(contra_nueva);
            peores_contras.error(contra_nueva);
            return false;
        }
    }
}
