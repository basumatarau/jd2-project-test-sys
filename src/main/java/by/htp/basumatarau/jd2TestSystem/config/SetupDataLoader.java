package by.htp.basumatarau.jd2TestSystem.config;

import by.htp.basumatarau.jd2TestSystem.dao.AuthorityDao;
import by.htp.basumatarau.jd2TestSystem.dao.RoleDao;
import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
import by.htp.basumatarau.jd2TestSystem.model.Authority;
import by.htp.basumatarau.jd2TestSystem.model.Role;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Component
public class SetupDataLoader implements ApplicationListener<ContextRefreshedEvent> {

    private static final String ADMIN_ROLE_NAME = "ADMIN";
    private static final String TEACHER_ROLE_NAME = "TEACHER";
    private static final String STUDENT_ROLE_NAME = "STUDENT";

    private static final String ADMIN_EMAIL = "admin@mail.com";
    private static final String ADMIN_FNAME = "admin";
    private static final String ADMIN_LNAME = "admin";
    private static final String ADMIN_PWD = "admin";

    private boolean alreadySetup = false;

    @Autowired
    private AuthorityDao authorityDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    private UserDao userDao;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        if (alreadySetup) {
            return;
        }
        Authority adminAuthority = createAuthorityIfNotFound("ROLE_ADMIN");
        Authority teacherAuthority = createAuthorityIfNotFound("TEACHER_ADMIN");
        Authority studentAuthority = createAuthorityIfNotFound("STUDENT_ADMIN");

        Role adminRole = createRoleIfNotFound("ADMIN", Set.of(adminAuthority, teacherAuthority, studentAuthority));
        Role teacherRole = createRoleIfNotFound("TEACHER", Set.of(teacherAuthority, studentAuthority));
        Role studentRole = createRoleIfNotFound("STUDENT", Set.of(studentAuthority));

        try {
            createUserIfNotFound(ADMIN_EMAIL, ADMIN_FNAME, ADMIN_LNAME, ADMIN_PWD, Set.of(adminRole));
        } catch (DaoException e) {
            //log failed to setup context
        }

        alreadySetup = true;
    }

    @Transactional
    private void createUserIfNotFound(String adminEmail, String adminFname, String adminLname, String adminPwd, Set<Role> roleSet) throws DaoException {
        User newUser = new User();
        newUser.setEmail(adminEmail);
        newUser.setPasswordHash(passwordEncoder.encode(adminPwd));
        newUser.setFirstName(adminFname);
        newUser.setLastName(adminLname);
        newUser.setRoles(roleSet);

        userDao.persist(newUser);
    }

    @Transactional
    private Role createRoleIfNotFound(String name, Set<Authority> authoritySet) {
        Role role = roleDao.findByName(name);
        if(role == null){
            Role newRole = new Role();
            newRole.setName(name);
            role = roleDao.createNewRole(name);
        }
        role.setAuthorities(authoritySet);
        roleDao.save(role);
        return role;
    }

    @Transactional
    private Authority createAuthorityIfNotFound(final String name) {
        Authority authority = authorityDao.findByName(name);
        if(authority == null){
            Authority newAuthority = new Authority();
            newAuthority.setAuthority(name);
            authority = authorityDao.createNewAuthority(newAuthority);
        }
        return authority;
    }
}
