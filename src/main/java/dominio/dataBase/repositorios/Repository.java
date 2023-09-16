package dominio.dataBase.repositorios;

import java.util.List;

public interface Repository<T> {

    void save(T t);
    List<T> findAll();
    T findById(Long id);
    void delete(T t);

}
