package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;

public interface UserDao {
    User findUserByEmail(String email) throws DaoException;
    User getUserByUserId(Integer id) throws DaoException;
    List<User> getUsers(int idAfter, int amount) throws DaoException;
    void persist(User newUser) throws DaoException;
    void update(User user) throws DaoException;
    void delete(User user) throws DaoException;
}
