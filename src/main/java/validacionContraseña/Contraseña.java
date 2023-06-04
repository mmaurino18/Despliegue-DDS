package validacionContraseña;
import java.util.ArrayList;

public class Contraseña {
    private String contraseña;
    private ArrayList<String> contraseña_antiguas = new ArrayList<String>();
    public Contraseña(String contra,ArrayList<String> contra_antiguas){
        this.contraseña= contra;
        this.contraseña_antiguas = contra_antiguas;
    }


}
