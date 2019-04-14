package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;
import java.util.Set;

public interface TestDao {

    void createNewTest(Test test);
    void merge(Test test);
    void delete(Test test);

    Test getTestById(int id) throws DaoException;
    List<Test> getTestsForUser(User user) throws DaoException;
    List<Test> getTests(int start, int amount) throws DaoException;

    long getTotalNumberOfTests() throws DaoException;
}
