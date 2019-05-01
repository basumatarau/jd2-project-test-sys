package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.exception.UserCredentialsNotRegistered;
import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public class UserDaoImpl
        extends BaseDaoImpl<User, Integer>
        implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    public UserDaoImpl(){
        super();
        setEntityType(User.class);
    }

    @Override
    public Set<User> getFollowers(User user) throws UserCredentialsNotRegistered {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User u " +
                        "left outer join fetch u.followers " +
                        "where u.id=:id", User.class)
                .setParameter("id", user.getId())
                .stream()
                .findAny()
                .orElseThrow(
                        ()-> new UserCredentialsNotRegistered(
                                "user with email: " + user.getEmail() + " is not registered"))
                .getFollowers();
    }

    @Override
    public Set<User> getFollowedUsers(User user) throws UserCredentialsNotRegistered {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User u " +
                        "left outer join fetch u.followedUsers " +
                        "where u.id=:id", User.class)
                .setParameter("id", user.getId())
                .stream()
                .findAny()
                .orElseThrow(
                        ()-> new UserCredentialsNotRegistered(
                                "user with email: " + user.getEmail() + " is not registered"))
                .getFollowedUsers();
    }

    @Override
    public User findUserByEmail(String email) throws UserCredentialsNotRegistered {
        return sessionFactory
                .getCurrentSession()
                .createQuery("from User u " +
                        "left outer join fetch u.roles role " +
                        "left outer join fetch role.authorities " +
                        "where u.email=:email ", User.class)
                .setParameter("email", email)
                .stream()
                .findFirst()
                .orElse(null);
    }
}
