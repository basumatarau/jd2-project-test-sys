package by.htp.basumatarau.jd2TestSystem.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;

public abstract class BaseDaoImpl<T, Id extends Serializable>
        implements BaseDao<T, Id> {

    @Autowired
    private SessionFactory sessionFactory;

    private Class<T> entityType;

    public BaseDaoImpl(){}

    protected final void setEntityType(Class<T> entityType) {
        this.entityType = entityType;
    }

    //todo utilize metamodel for log output details in generic methods

    @Override
    public Serializable save(T entity) {
        return sessionFactory.getCurrentSession().save(entity);
    }

    @Override
    public void saveOrUpdate(T entity) {
        sessionFactory.getCurrentSession().saveOrUpdate(entity);
    }

    @Override
    public List<T> findAll() {
        CriteriaBuilder criteriaBuilder
                = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityType);
        Root<T> root = query.from(entityType);
        query.select(root);
        return sessionFactory
                .getCurrentSession()
                .createQuery(query)
                .getResultList();
    }

    @Override
    public Long getTotalCount() {
        CriteriaBuilder criteriaBuilder
                = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<Long> query = criteriaBuilder.createQuery(Long.class);
        query.select(criteriaBuilder.count(query.from(entityType)));

        return sessionFactory
                .getCurrentSession()
                .createQuery(query)
                .getSingleResult();
    }

    @Override
    public List<T> getPaginated(int first, int pageLength) {
        CriteriaBuilder criteriaBuilder
                = sessionFactory.getCurrentSession().getCriteriaBuilder();
        CriteriaQuery<T> query = criteriaBuilder.createQuery(entityType);
        Root<T> root = query.from(entityType);
        query.select(root);
        return sessionFactory
                .getCurrentSession()
                .createQuery(query)
                .setFirstResult(first)
                .setMaxResults(pageLength)
                .getResultList();
    }

    @Override
    public T findById(Id id) {
        return sessionFactory.getCurrentSession().get(entityType, id);
    }

    @Override
    public void merge(T entity) {
        sessionFactory.getCurrentSession().merge(entity);
    }

    @Override
    public void clear() {
        sessionFactory.getCurrentSession().clear();
    }

    @Override
    public void flush() {
        sessionFactory.getCurrentSession().flush();
    }
}
