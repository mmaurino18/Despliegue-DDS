package models.dominio.rankings;

import lombok.Getter;
import lombok.Setter;
import models.dominio.comunidad.Comunidad;
import models.dominio.entidades.Entidad;
import models.dominio.servicios.Incidente;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
@Setter
@Getter
public class CalculadoraRankings {

    public  List<Incidente> incidentes;

    public List<Tupla> generarRanking(Criterio criterio){
        return criterio.generarRanking(incidentes);
    }
    /*public void cambiarCriterio(Criterio criterio){

    }*/

}
