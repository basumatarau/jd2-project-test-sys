package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public User getUserByUserId(int id) {
        return userDao.getUserByUserId(id);
    }
}
