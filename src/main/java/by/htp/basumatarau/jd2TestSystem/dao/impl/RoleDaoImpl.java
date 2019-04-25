package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Role;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class RoleDaoImpl implements RoleDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void createIfNotExists(Role role) {
        Role persistedRole = sessionFactory.getCurrentSession().createQuery(
                "from Role r where r.name=:name", Role.class)
                .setParameter("name", role.getName()).getSingleResult();
        if(persistedRole==null){
            sessionFactory.getCurrentSession().save(role);
        }
    }
}
