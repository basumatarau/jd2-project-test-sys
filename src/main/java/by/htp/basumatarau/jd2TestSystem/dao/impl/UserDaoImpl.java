package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.dao.exception.UserCredentialsNotRegistered;
import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import java.util.List;
import java.util.Set;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByEmail(String email) throws DaoException{
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u " +
                        "join fetch u.roles role " +
                        "left outer join fetch role.authorities " +
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
                        "join fetch u.roles role " +
                        "left outer join fetch role.authorities " +
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
                .createQuery("from User", User.class);
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

    @Override
    public Set<User> getFollowers(User user) throws DaoException {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u " +
                        "join fetch u.followers " +
                        "where u.id=:id", User.class);
        query.setParameter("id", user.getId());
        Set<User> followers = null;
        try {
            List<User> resultList = query.getResultList();
            if(!resultList.isEmpty()){
                followers = resultList.get(0).getFollowers();
            }
        }catch (NoResultException e){
            throw new UserCredentialsNotRegistered("fail", e);
        }

        return followers;
    }

    @Override
    public Set<User> getFollowedUsers(User user) throws DaoException {
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u " +
                        "join fetch u.followedUsers " +
                        "where u.id=:id", User.class);
        query.setParameter("id", user.getId());
        Set<User> followedUsers = null;
        try {
            List<User> resultList = query.getResultList();
            if(!resultList.isEmpty()){
                followedUsers = resultList.get(0).getFollowedUsers();
            }
        }catch (NoResultException e){
            throw new UserCredentialsNotRegistered("fail", e);
        }
        return followedUsers;
    }

    @Override
    public long getNumberOfUsers() {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(*) from User ");
        return (long) query.uniqueResult();
    }

    @Override
    public void merge(User user) throws DaoException {
        sessionFactory.getCurrentSession().merge(user);
    }
}
