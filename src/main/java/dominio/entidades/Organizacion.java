package dominio.entidades;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("organizacion")
public class Organizacion extends Entidad{
}
