package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Authority;

public interface AuthorityDao
        extends BaseDao<Authority, Integer> {
    Authority findByName(String name);
}
