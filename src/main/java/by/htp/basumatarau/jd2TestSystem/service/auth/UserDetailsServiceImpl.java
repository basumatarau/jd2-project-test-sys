package by.htp.basumatarau.jd2TestSystem.service.auth;

import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        //todo loadUserByUsername!=loadUserByEmail --> to be fixed?
        User user = userDao.findUserByEmail(email);
        org.springframework.security.core.userdetails.User.UserBuilder builder = null;

        if(user!=null){
            builder = org.springframework.security.core.userdetails.User.withUsername(user.getFirstName() + " " + user.getLastName());
            builder.disabled(!user.isEnabled());

            builder.password(user.getPassword());
            builder.authorities(
                    user.getAuthoritySet()
                            .stream()
                            .map(a->a.getAuthority())
                            .toArray(String[]::new)
            );

        }else{
            throw new UsernameNotFoundException("user with email: " + email + " has not been found.");
        }

        return builder.build();
    }
}
