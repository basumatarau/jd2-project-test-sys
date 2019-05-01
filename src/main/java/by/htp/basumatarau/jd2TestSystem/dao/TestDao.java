package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Test;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;

public interface TestDao extends BaseDao<Test, Integer> {
    List<Test> getTestsForUser(User user);
}
