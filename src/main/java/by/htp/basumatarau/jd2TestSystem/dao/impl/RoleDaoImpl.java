package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.RoleDao;
import by.htp.basumatarau.jd2TestSystem.model.Authority;
import by.htp.basumatarau.jd2TestSystem.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Set;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void save(Role role) {
        sessionFactory.getCurrentSession().save(role);
    }

    @Override
    public Role findByName(String roleName) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Role r " +
                        "join fetch r.authorities " +
                        "where r.name=:name", Role.class)
                .setParameter("name", roleName).getSingleResult();
    }

    @Override
    public void addAuthority(Authority authority, Role role) {

        Set<Authority> authorities = role.getAuthorities();
        authorities.add(authority);
        role.setAuthorities(authorities);
        sessionFactory.getCurrentSession().merge(role);
    }

    @Override
    public Role createNewRole(String roleName) {
        Role role = new Role();
        role.setName(roleName);
        Serializable id = sessionFactory.getCurrentSession().save(role);
        return sessionFactory.getCurrentSession().load(Role.class, id);
    }
}
