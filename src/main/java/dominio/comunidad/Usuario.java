package dominio.comunidad;

import dominio.validacionContrasenia.ValidacionCaracteres;
import dominio.validacionContrasenia.ValidadorContraseña;

public class Usuario {
    public String nombre;
    private String contraseña;
    public Rol rol;

    public Usuario() {

    }
    public boolean contraseñaValida(String contraseña , ValidadorContraseña validadorContraseña){
        return validadorContraseña.validarContraseña(contraseña);
    }
    public boolean cambiarContraseña(String contra_nueva, ValidadorContraseña validadorContraseña) {
       return validadorContraseña.cambioContraseña(contra_nueva, this.contraseña);
    }

    public String getContraseña() {
        return contraseña;
    }

}
