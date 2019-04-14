package by.htp.basumatarau.jd2TestSystem.service;

import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;

import java.util.List;

public interface TestService {
    Test getTestById(int id) throws ServiceException;
    List<Test> getTestForUsers(User user) throws ServiceException;
    List<Test> getTests(int start, int amount) throws ServiceException;

    void createNewTest(Test newTest) throws ServiceException;
    long getTotalNumberOfTests() throws ServiceException;
}
