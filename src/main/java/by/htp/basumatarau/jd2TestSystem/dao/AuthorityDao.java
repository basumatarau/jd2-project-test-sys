package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Authority;

public interface AuthorityDao {
    Authority createNewAuthority(Authority authority);
    Authority findByName(String authorityName);
}
