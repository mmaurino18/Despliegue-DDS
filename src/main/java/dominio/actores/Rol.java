package dominio.actores;

import dominio.actores.Permiso;
import dominio.dataBase.Persistente;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "rol")
@Getter
@Setter
public class Rol extends Persistente {

    @Column(name = "nombre", columnDefinition = "VARCHAR(55)")
    public String nombre;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo")
    private TipoRol tipo;

    @OneToMany
    @JoinColumn(name = "rol_id")
    public List<Permiso> permisos;

    public Rol(){
        this.permisos = new ArrayList<>();
    }

    public void agregarPermiso(Permiso permiso){
        this.permisos.add(permiso);
    }

    public boolean tenesPermisoEstandar(Permiso permiso) {
        return this.permisos.contains(permiso);
    }

    public boolean tenesPermiso(String nombreInterno) {
        return this.permisos.stream().anyMatch(p -> p.coincideConNombreInterno(nombreInterno));
    }
}
