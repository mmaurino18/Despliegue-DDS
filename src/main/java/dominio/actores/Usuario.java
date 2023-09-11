package dominio.actores;

import dominio.dataBase.Persistente;
import dominio.validacionContrasenia.ValidadorContraseña;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "usuario")
@Getter
@Setter
public class Usuario extends Persistente {

    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    public String nombre;

    @Column(name = "contrasenia", columnDefinition = "VARCHAR(55)")
    private String contrasenia;

    @ManyToOne
    @JoinColumn(name = "rol_id", referencedColumnName = "id")
    public Rol rol;

    public Usuario() {

    }

    public boolean contraseniaValida(String contraseña , ValidadorContraseña validadorContraseña){
        return validadorContraseña.validarContraseña(contraseña);
    }

    public boolean cambiarContrasenia(String contra_nueva, ValidadorContraseña validadorContraseña) {
       return validadorContraseña.cambioContraseña(contra_nueva, this.contrasenia);
    }


}
