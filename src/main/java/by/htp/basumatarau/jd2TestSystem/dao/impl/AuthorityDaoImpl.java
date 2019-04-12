package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AuthorityDao;
import by.htp.basumatarau.jd2TestSystem.model.Authority;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AuthorityDaoImpl implements AuthorityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(Authority authority) {
        sessionFactory.getCurrentSession().save(authority);
    }
}
