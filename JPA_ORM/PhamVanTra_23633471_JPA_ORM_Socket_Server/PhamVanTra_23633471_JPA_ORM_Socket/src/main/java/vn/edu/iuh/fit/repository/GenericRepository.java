package vn.edu.iuh.fit.repository;

import java.util.List;

public interface GenericRepository<T, ID> {

    T create(T entity);

    T update(T entity);

    boolean delete(ID id);

    T findById(ID id);

    List<T> loadAll();

}
