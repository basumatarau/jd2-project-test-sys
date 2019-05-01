package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.dao.RoleDao;
import by.htp.basumatarau.jd2TestSystem.model.Authority;
import by.htp.basumatarau.jd2TestSystem.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class RoleDaoImpl
        extends BaseDaoImpl<Role, Integer>
        implements RoleDao {

    public RoleDaoImpl(){
        setEntityType(Role.class);
    }

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Role findByName(String roleName) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Role r " +
                        "left outer join fetch r.authorities " +
                        "where r.name=:name", Role.class)
                .setParameter("name", roleName)
                .getSingleResult();
    }

    @Override
    public void addAuthority(Authority authority, Role role) {
        Set<Authority> authorities = role.getAuthorities();
        if(authorities == null){
            authorities = new HashSet<>();
        }
        authorities.add(authority);
        role.setAuthorities(authorities);
        sessionFactory.getCurrentSession().merge(role);
    }
}
