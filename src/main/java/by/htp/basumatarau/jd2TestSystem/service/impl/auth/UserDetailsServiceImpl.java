package by.htp.basumatarau.jd2TestSystem.service.impl.auth;

import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("userDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserDao userDao;

/*    @Transactional(readOnly = true)
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

        UserDetails userDetails = builder.build();

        return userDetails;
    }*/


    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userDao.findUserByEmail(email);

        CustomUser customUser = new CustomUser(
                user.getFirstName() + user.getLastName(),
                user.getPassword(),
                user.isEnabled(),
                true,
                true,
                true,
                buildUserAuthorities(user));

        customUser.setEmail(user.getEmail());
        customUser.setId(user.getId());

        return customUser;
    }

    private Collection<? extends GrantedAuthority> buildUserAuthorities(User user) {
        List<SimpleGrantedAuthority> authorityList = user.getAuthoritySet()
                .stream()
                .map(a -> a.getAuthority())
                .map(authStr -> new SimpleGrantedAuthority(authStr))
                .collect(Collectors.toList());
        return authorityList;
    }

}
