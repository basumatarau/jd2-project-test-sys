package by.htp.basumatarau.jd2TestSystem.service;

import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;

import java.util.List;
import java.util.Set;

public interface UserService {
    User getUserByUserId(int id) throws UserServiceException;
    User getUserByUserEmail(String email) throws UserServiceException;
    void createNewUser(User newUser) throws UserServiceException;
    void update(User user) throws UserServiceException;
    void delete(User user) throws UserServiceException;

    Set<User> getFollowers(User user)throws UserServiceException;
    Set<User> getFollowedUsers(User user)throws UserServiceException;
    List<User> getUsers(int start, int amount) throws UserServiceException;

    long getTotalUsersCount();
    void addFollowedUser(User user, User followedUser) throws UserServiceException;
    void addFollower(User user, User follower) throws UserServiceException;

    void removeFollowedUser(User user, User followedUser) throws UserServiceException;
    void removeFollower(User user, User follower) throws UserServiceException;

}
