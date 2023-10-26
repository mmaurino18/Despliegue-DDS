package models.dominio.validacionContrasenia;

public class ValidacionContraseña {
    private ValidacionCaracteres validacion = new ValidacionCaracteres();
    private PeoresContras peores_contras = new PeoresContras();
    public boolean validar(String contra_nueva) {
        if (validacion.validar(contra_nueva) && !peores_contras.estaContenida(contra_nueva)) {
            return true;
        } else {
            return false;
        }

    }
    public String errores(String contra){
        String error="";
        error = validacion.errores(contra);
        error += "\n";
        error += peores_contras.error(contra);
        return error;
    }
}
