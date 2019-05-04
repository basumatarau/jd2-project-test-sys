package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.RoleDao;
import by.htp.basumatarau.jd2TestSystem.model.Role;
import by.htp.basumatarau.jd2TestSystem.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleDao roleDao;

    @Transactional
    @Override
    public List<Role> getAllRoles() {
        return roleDao.findAll();
    }

    @Transactional
    @Override
    public Role getRoleByName(String roleName) {
        return roleDao.findByName(roleName);
    }
}
