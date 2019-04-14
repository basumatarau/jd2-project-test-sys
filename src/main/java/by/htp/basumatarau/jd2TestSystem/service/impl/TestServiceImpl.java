package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.TestDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.TestService;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class TestServiceImpl implements TestService {

    @Autowired
    private TestDao testDao;

    @Override
    public Test getTestById(int id) throws ServiceException {
        Test result;
        try {
            result = testDao.getTestById(id);
        } catch (DaoException e) {
            throw new ServiceException("failed to fetch test with id=" + id + "...", e);
        }
        return result;
    }

    @Override
    public List<Test> getTestForUsers(User user) throws ServiceException {
        List<Test> result;
        try {
            result = testDao.getTestsForUser(user);
        } catch (DaoException e) {
            throw new ServiceException("failed to fetch tests for user with id="+user.getId()+"...", e);
        }
        return result;
    }

    @Override
    public List<Test> getTests(int start, int amount) throws ServiceException {
        List<Test> result;
        try {
            result = testDao.getTests(start, amount);
        } catch (DaoException e) {
            throw new ServiceException("failed to fetch tests", e);
        }
        return result;
    }

    @Override
    public void createNewTest(Test newTest) throws ServiceException {
        try {
            testDao.createNewTest(newTest);
        } catch (Exception e) {
            throw new ServiceException("failed to create new test...", e);
        }
    }

    @Override
    public long getTotalNumberOfTests() throws ServiceException {
        long result = 0;
        try {
            result = testDao.getTotalNumberOfTests();
        } catch (DaoException e) {
            throw  new ServiceException("failed to obtain total count of tests", e);
        }
        return result;
    }
}
