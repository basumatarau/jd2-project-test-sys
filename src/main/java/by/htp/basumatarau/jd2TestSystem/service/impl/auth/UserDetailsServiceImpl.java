package by.htp.basumatarau.jd2TestSystem.service.impl.auth;

import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.dao.exception.DaoException;
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

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = null;
        try {
            user = userDao.findUserByEmail(email);
        } catch (DaoException e) {
            throw new UsernameNotFoundException("failed to fetch user by name(email) for authentication", e);
        }

        CustomUser customUser = new CustomUser(
                user.getFirstName() + " " + user.getLastName(),
                user.getPasswordHash(),
                user.isEnabled(),
                true,
                true,
                true,
                buildUserAuthorities(user));

        customUser.setEmail(user.getEmail());
        customUser.setId(user.getId());
        customUser.setCurrentUser(user);

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
