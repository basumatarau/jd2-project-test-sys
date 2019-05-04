package by.htp.basumatarau.jd2TestSystem.dao;

import java.io.Serializable;
import java.util.List;

public interface BaseDao<T, Id extends Serializable> {
    Serializable save(T entity);
    void saveOrUpdate(T entity);

    //todo the method implementation is to be updated... yet idk how
    //void delete(T entity);

    void merge(T entity);
    List<T> findAll();
    List<T> getPaginated(int first, int pageLength);
    Long getTotalCount();
    T findById(Id id);

    void clear();
    void flush();
}
