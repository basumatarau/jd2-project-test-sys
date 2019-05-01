package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Authority;
import by.htp.basumatarau.jd2TestSystem.model.Role;

public interface RoleDao
        extends BaseDao<Role, Integer> {

    Role findByName(String name);
    void addAuthority(Authority authority, Role role);
}
