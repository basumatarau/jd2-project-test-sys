package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.RoleDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.dao.exception.UserCredentialsNotRegistered;
import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.dto.UserDto;
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
        User result = userDao.findById(id);
        if(result == null){
            throw new UserServiceException("failed to fetch user with id=" + id + "...");
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
        }

        Role student = roleDao.findByName("ROLE_STUDENT");
        User newUser = new User();
        newUser.setFirstName(accountDetailsDto.getFirstName());
        newUser.setLastName(accountDetailsDto.getLastName());
        newUser.setEmail(accountDetailsDto.getEmail());
        newUser.setEnabled(true);
        newUser.setPasswordHash(passwordEncoder.encode(accountDetailsDto.getPassword()));
        newUser.setRoles(Set.of(student));

        userDao.save(newUser);
    }

    @Transactional
    @Override
    public void update(User user) {
        userDao.saveOrUpdate(user);
    }

    @Transactional
    @Override
    public void delete(User user) {
        userDao.delete(user);
    }

    @Transactional
    @Override
    public Set<User> getFollowers(User user) throws UserServiceException {
        try {
            return userDao.getFollowers(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to retrieve a set of followers", e);
        }
    }

    @Transactional
    @Override
    public Set<User> getFollowedUsers(User user) throws UserServiceException {
        try {
            return userDao.getFollowedUsers(user);
        } catch (DaoException e) {
            throw new UserServiceException("failed to retrieve a set of followed users: " + e.getMessage(), e);
        }
    }

    @Transactional
    @Override
    public List<User> getUsers(int start, int amount) {
        return userDao.getPaginated(start, amount);
    }

    @Transactional
    @Override
    public long getTotalUsersCount() {
        return userDao.getTotalCount();
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
        userDao.saveOrUpdate(user);
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
        userDao.saveOrUpdate(user);
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
        userDao.saveOrUpdate(user);
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
        userDao.saveOrUpdate(user);
    }
}
