package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.TestDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class TestDaoImpl implements TestDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Test> getTestsForUser(User user) throws DaoException {
        Query<Test> query = sessionFactory.getCurrentSession()
                .createQuery("from Test t " +
                        "where t.author.id=:id ", Test.class);

        query.setParameter("id", user.getId());
        List<Test> result = null;
        try {
            result = query.getResultList();
        } catch (NoResultException e) {
            throw new DaoException("failed to fetch tests for user with id=" + user.getId() + "...", e);
        }
        return result;
    }

    @Override
    public List<Test> getTests(int start, int amount) throws DaoException {
        Query<Test> query
                = sessionFactory
                        .getCurrentSession()
                        .createQuery("from Test ", Test.class);
        List<Test> result;
        try {
            result = query.setMaxResults(amount)
                    .setFirstResult(start)
                    .getResultList();
        }catch (NoResultException e){
            throw new DaoException("failed to fetch tests", e);
        }
        return result;
    }

    @Override
    public Test getTestById(int id) throws DaoException {
        Test result;
        Query<Test> query = sessionFactory
                .getCurrentSession()
                .createQuery("from Test t " +
                        "where t.id=:id ", Test.class);
        query.setParameter("id", id);
        try {
            result = query.getSingleResult();
        } catch (NoResultException e) {
            throw new DaoException("test with id=" + id + "...", e);
        }
        return result;
    }

    @Override
    public void createNewTest(Test test) {
        sessionFactory.getCurrentSession().persist(test);
    }

    @Override
    public void merge(Test test) {
        sessionFactory.getCurrentSession().merge(test);
    }

    @Override
    public void delete(Test test) {
        sessionFactory.getCurrentSession().delete(test);
    }

    @Override
    public long getTotalNumberOfTests() throws DaoException {
        long result = 0;
        try {
            result = (long) sessionFactory.getCurrentSession()
                    .createQuery("select count (*) from Test")
                    .uniqueResult();
        }catch (Exception e){
            throw new DaoException("failed to obtain tatal count of tests", e);
        }
        return result;
    }
}
