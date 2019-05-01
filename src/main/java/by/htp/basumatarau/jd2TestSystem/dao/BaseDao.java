package by.htp.basumatarau.jd2TestSystem.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, Id extends Serializable> {
    Serializable save(T entity);
    void saveOrUpdate(T entity);
    void delete(T entity);
    List<T> findAll();
    List<T> getPaginated(int first, int pageLength);
    Long getTotalCount();
    T findById(Id id);

    void clear();
    void flush();
}
