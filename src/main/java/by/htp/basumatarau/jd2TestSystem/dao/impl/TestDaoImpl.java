package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.dao.TestDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class TestDaoImpl
        extends BaseDaoImpl<Test, Integer>
        implements TestDao {

    @Autowired
    private SessionFactory sessionFactory;

    public TestDaoImpl() {
        super();
        setEntityType(Test.class);
    }

    @Override
    public List<Test> getTestsForUser(User user){
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Test t " +
                "where t.author.id=:id ", Test.class)
                .setParameter("id", user.getId())
                .getResultList();
    }
}
