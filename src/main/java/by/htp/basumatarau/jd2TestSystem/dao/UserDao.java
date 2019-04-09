package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;

public interface UserDao {
    User findUserByEmail(String email);
    User getUserByUserId(Integer id);
    List<User> getUsers(int idAfter, int amount);
    void persist(User newUser);
    void update(User user);
    void delete(User user);
}
