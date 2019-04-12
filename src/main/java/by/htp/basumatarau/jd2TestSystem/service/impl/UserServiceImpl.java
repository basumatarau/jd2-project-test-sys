package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AuthorityDao;
import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.model.Authority;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private AuthorityDao authorityDao;

    @Transactional
    @Override
    public User getUserByUserEmail(String email) throws UserServiceException {
        User result;
        try {
            result = userDao.findUserByEmail(email);
        }catch (DaoException e) {
            throw new UserServiceException("failed to fetch user with email: " + email + "...", e);
        }
        return result;
    }

    @Transactional
    @Override
    public User getUserByUserId(int id) throws UserServiceException {
        User result;
        try {
            result = userDao.getUserByUserId(id);
        }catch (DaoException e) {
            throw new UserServiceException("failed to fetch user with id=" + id + "...", e);
        }
        return result;
    }

    @Transactional
    @Override
    public void createNewUser(User newUser) throws UserServiceException{
        try {
            userDao.persist(newUser);
        }catch (DaoException e) {
            throw new UserServiceException("failed to create new user", e);
        }

        for (Authority authority : newUser.getAuthoritySet()) {
            authorityDao.persist(authority);
        }

    }

    @Transactional
    @Override
    public void update(User user) throws UserServiceException{
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to update user", e);
        }
    }

    @Transactional
    @Override
    public void delete(User user) throws UserServiceException{
        try {
            userDao.delete(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to delete user", e);
        }
    }
}
