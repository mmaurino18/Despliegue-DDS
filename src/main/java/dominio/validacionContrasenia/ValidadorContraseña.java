package dominio.validacionContrasenia;

public class ValidadorContraseña {
    public CambiarContraseña cambiarContraseña;
    public ValidacionContraseña validacionContraseña;

    public boolean validarContraseña(String contraseña){
        return validacionContraseña.validar(contraseña);
    }

    public boolean cambioContraseña(String contra_nueva,String contra_actual){
        return cambiarContraseña.cambioDeContraseña(contra_nueva,contra_actual);
    }
}
