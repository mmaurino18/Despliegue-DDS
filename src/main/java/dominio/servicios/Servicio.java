package dominio.servicios;

import com.fasterxml.jackson.annotation.JsonProperty;
import dominio.dataBase.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "servicio")
@Getter
@Setter
public class Servicio extends Persistente {
    @JsonProperty("nombre")
    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    private String nombre;

    @Column(name = "descripcion", columnDefinition = "TEXT")
    private String descripcion;

    public Servicio(){

    }

}
