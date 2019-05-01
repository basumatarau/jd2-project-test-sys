package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AuthorityDao;
import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.model.Authority;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public class AuthorityDaoImpl
        extends BaseDaoImpl<Authority, Integer>
        implements AuthorityDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Authority findByName(String authorityName) {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from Authority a where a.authority=:name", Authority.class)
                .setParameter("name", authorityName)
                .stream()
                .findAny()
                .orElse(null);
    }
}
