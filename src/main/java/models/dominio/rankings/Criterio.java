package models.dominio.rankings;


import models.dominio.servicios.Incidente;

import java.time.LocalDateTime;
import java.util.List;

public interface Criterio {
    default List<Tupla> generarRanking( List<Incidente> incidentes){return null;}
}
