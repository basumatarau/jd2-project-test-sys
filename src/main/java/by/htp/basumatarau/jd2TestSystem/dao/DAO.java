package by.htp.basumatarau.jd2TestSystem.dao;

import java.io.Serializable;
import java.util.List;

public interface DAO<T, Id extends Serializable> {
    void persist(T entity);
    T read(Id id);
    List<T> readRange(Id from, Id to);
    void update(T entity);
    void delete(T entity);
}
