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
        Test retrievedTest = testDao.findById(id);
        if (retrievedTest == null) {
            throw new ServiceException("failed to fetch test with id=" + id + "...");
        }
        return retrievedTest;
    }

    @Override
    public List<Test> getTestForUsers(User user) throws ServiceException {
        List<Test> retrievedTest = testDao.getTestsForUser(user);
        if (retrievedTest == null) {
            throw new ServiceException(
                    "failed to fetch tests for user with id=" + user.getId() + "...");
        }
        return retrievedTest;
    }

    @Override
    public List<Test> getTests(int start, int amount) {
        return testDao.getPaginated(start, amount);
    }

    @Override
    public void createNewTest(Test newTest) {
        testDao.save(newTest);
    }

    @Override
    public long getTotalNumberOfTests() {
        return testDao.getTotalCount();
    }
}
