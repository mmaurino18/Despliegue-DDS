package dominio.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("transporte")
@Getter
@Setter
public class Transporte extends Entidad{

    @OneToMany
    @JoinColumn(name = "transporte_id")
    private List<Estacion> recorrido;





    public Transporte() {
        this.recorrido = new ArrayList<>();
    }
}
