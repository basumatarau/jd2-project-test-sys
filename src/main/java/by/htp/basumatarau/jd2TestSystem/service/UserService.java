package by.htp.basumatarau.jd2TestSystem.service;

import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;

public interface UserService {
    User getUserByUserId(int id) throws UserServiceException;
    User getUserByUserEmail(String email) throws UserServiceException;
    void createNewUser(User newUser) throws UserServiceException;
    void update(User user) throws UserServiceException;
    void delete(User user) throws UserServiceException;
}
