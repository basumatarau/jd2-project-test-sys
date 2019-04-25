package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Authority;
import by.htp.basumatarau.jd2TestSystem.model.Role;

public interface RoleDao {
    Role createNewRole(String roleName);
    void addAuthority(Authority authority, Role role);
    Role findByName(String roleName);
    void save(Role role);
}
