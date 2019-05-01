package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.dao.exception.UserCredentialsNotRegistered;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.Set;

public interface UserDao extends BaseDao<User, Integer> {
    Set<User> getFollowers(User user) throws UserCredentialsNotRegistered;
    Set<User> getFollowedUsers(User user) throws UserCredentialsNotRegistered;
    User findUserByEmail(String email) throws UserCredentialsNotRegistered;
}
