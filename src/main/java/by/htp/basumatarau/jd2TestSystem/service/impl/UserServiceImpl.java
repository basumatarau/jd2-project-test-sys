package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AuthorityDao;
import by.htp.basumatarau.jd2TestSystem.dao.RoleDao;
import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.dao.exception.UserCredentialsNotRegistered;
import by.htp.basumatarau.jd2TestSystem.dto.UserDto;
import by.htp.basumatarau.jd2TestSystem.model.Authority;
import by.htp.basumatarau.jd2TestSystem.model.Role;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserCredentialsOccupied;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

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
    public void registerNewUser(UserDto accountDetailsDto) throws UserServiceException{
        try {
            if (userDao.findUserByEmail(accountDetailsDto.getEmail()) != null) {
                throw new UserCredentialsOccupied("email is occupied");
            }
        }catch (UserCredentialsNotRegistered e){
            //log proceed with the registration;
        }catch (DaoException e){
            throw new UserServiceException(e);
        }

        Role student = roleDao.findByName("ROLE_STUDENT");
        User newUser = new User();
        newUser.setFirstName(accountDetailsDto.getFirstName());
        newUser.setLastName(accountDetailsDto.getLastName());
        newUser.setEmail(accountDetailsDto.getEmail());
        newUser.setEnabled(true);
        newUser.setPasswordHash(passwordEncoder.encode(accountDetailsDto.getPassword()));
        newUser.setRoles(Set.of(student));

        try {
            userDao.persist(newUser);
        }catch (DaoException e) {
            throw new UserServiceException("failed to create new user", e);
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

    @Transactional
    @Override
    public Set<User> getFollowers(User user) throws UserServiceException {
        try {
            return userDao.getFollowers(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to load set of followers", e);
        }
    }

    @Transactional
    @Override
    public Set<User> getFollowedUsers(User user) throws UserServiceException {
        try {
            return userDao.getFollowedUsers(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to load set of followed users", e);
        }
    }

    @Transactional
    @Override
    public List<User> getUsers(int start, int amount) throws UserServiceException{
        List<User> users = null;
        try {
            users = userDao.getUsers(start, amount);
        } catch (DaoException e) {
            throw new UserServiceException("failed to fetch user list", e);
        }
        return users;
    }

    @Transactional
    @Override
    public long getTotalUsersCount() {
        return userDao.getNumberOfUsers();
    }

    @Transactional
    @Override
    public void addFollowedUser(User user, User followedUser) throws UserServiceException {
        Set<User> followedUsers = getFollowedUsers(user);
        if(followedUsers == null){
            followedUsers = new HashSet<>();
        }
        followedUsers.add(followedUser);
        user.setFollowedUsers(followedUsers);
        try {
            userDao.merge(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to update followed users list", e);
        }
    }

    @Transactional
    @Override
    public void addFollower(User user, User follower) throws UserServiceException {
        Set<User> followers = getFollowers(user);
        if(followers == null){
            followers = new HashSet<>();
        }
        followers.add(follower);
        user.setFollowers(followers);
        try {
            userDao.merge(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to update followers list", e);
        }
    }

    @Transactional
    @Override
    public void removeFollowedUser(User user, User followedUser) throws UserServiceException {
        Set<User> followedUsers = getFollowedUsers(user);
        if(followedUsers == null){
            followedUsers = new HashSet<>();
        }
        followedUsers.remove(followedUser);
        user.setFollowedUsers(followedUsers);
        try {
            userDao.merge(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to update followed users list", e);
        }
    }

    @Transactional
    @Override
    public void removeFollower(User user, User follower) throws UserServiceException {
        Set<User> followers = getFollowers(user);
        if(followers == null){
            followers = new HashSet<>();
        }
        followers.remove(follower);
        user.setFollowers(followers);
        try {
            userDao.merge(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to update followers list", e);
        }
    }
}
