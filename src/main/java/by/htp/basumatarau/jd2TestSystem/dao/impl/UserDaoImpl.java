package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public User findUserByEmail(String email) {
        User result = null;
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u " +
                        "join fetch u.authoritySet " +
                        "where u.email=:email ", User.class);
        query.setParameter("email", email);
        List<User> resultList = query.getResultList();

        if(resultList.size()!=1){
            //todo handle exception? log? or something else?
        }else{
            result = resultList.get(0);
        }
        return result;
    }

    @Override
    public User getUserByUserId(Integer id) {
        User result = null;
        Query<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User u " +
                        "join fetch u.authoritySet " +
                        "where u.id=:id ", User.class);
        query.setParameter("id", id);
        List<User> resultList = query.getResultList();

        if(resultList.size()!=1){
            //todo handle exception? log? or something else?
        }else{
            result = resultList.get(0);
        }
        return result;
    }

    @Override
    public List<User> getUsers(int idAfter, int amount) {
        return null;
    }

    @Override
    public void persist(User newUser) {

    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }
}
