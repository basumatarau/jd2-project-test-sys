package by.htp.basumatarau.jd2TestSystem.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.security.SecureRandom;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Bean
    public BCryptPasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecureRandom secureRandom(){
        return new SecureRandom();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/loginAction")
                    .usernameParameter("custom_username")
                    .passwordParameter("custom_password")
                    .failureForwardUrl("/login?error=true")
                    .successForwardUrl("/")
                    .permitAll()
                .and()
                    .logout()
                    .logoutSuccessUrl("/login")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/login**", "/sign-up**")
                    .permitAll()
                .and()
                    .authorizeRequests()
                    .antMatchers("/assignment-manager**",
                            "/subscribers**",
                            "/subscription**",
                            "/my-assignments**")
                    .hasAnyRole("ADMIN", "USER", "GUEST")
                .and()
                    .exceptionHandling()
                    .accessDeniedPage("/access-denied")
                .and()
                    .csrf().disable();
        //todo fix the cfg
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);

        auth.authenticationProvider(provider);
    }
}
