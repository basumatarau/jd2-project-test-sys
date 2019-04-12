package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.dao.exception.UserCredentialsNotRegistered;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByEmail(String email) throws DaoException{
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u " +
                        "join fetch u.authoritySet " +
                        "where u.email=:email ", User.class);
        query.setParameter("email", email);

        User result = null;
        try {
            result = query.getSingleResult();
        }catch (NoResultException e){
            throw new UserCredentialsNotRegistered("no user registered with email: " + email, e);
        }
        return result;
    }

    @Override
    public User getUserByUserId(Integer id) throws DaoException{
        User result = null;
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u " +
                        "join fetch u.authoritySet " +
                        "where u.id=:id ", User.class);
        query.setParameter("id", id);

        try {
            result = query.getSingleResult();
        }catch (NoResultException e){
            throw new DaoException("no user found with id=" + id +"...", e);
        }

        return result;
    }

    //todo
    @Override
    public List<User> getUsers(int first, int entries) throws DaoException{
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u ", User.class);
        List<User> result = null;
        try {
            result = query.setFirstResult(first)
                    .setMaxResults(entries)
                    .getResultList();
        }catch (NoResultException e){
            throw new DaoException("failed to fetch list of users", e);
        }
        return result;
    }

    @Override
    public void persist(User newUser) {
        sessionFactory.getCurrentSession().save(newUser);
    }

    @Override
    public void update(User user) {
        sessionFactory.getCurrentSession().update(user);
    }

    @Override
    public void delete(User user) {
        sessionFactory.getCurrentSession().delete(user);
    }
}
