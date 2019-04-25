package by.htp.basumatarau.jd2TestSystem.config;

import by.htp.basumatarau.jd2TestSystem.model.*;
import by.htp.basumatarau.jd2TestSystem.model.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import static org.hibernate.cfg.AvailableSettings.*;

import java.util.Properties;

@Configuration
@PropertySource("classpath:db.properties")
@EnableTransactionManagement
@ComponentScans(value =
        {@ComponentScan("by.htp.basumatarau.jd2TestSystem.service"),
        @ComponentScan("by.htp.basumatarau.jd2TestSystem.service.impl.auth"),
        @ComponentScan("by.htp.basumatarau.jd2TestSystem.dao")})
public class AppConfig {

    @Autowired
    private Environment environment;

    @Bean
    public LocalSessionFactoryBean getSessionFactory(){
        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
        Properties properties = new Properties();

        properties.put(DRIVER, environment.getProperty("mysql.driver"));
        properties.put(URL, environment.getProperty("mysql.jdbcUrl"));
        properties.put(USER, environment.getProperty("mysql.username"));
        properties.put(PASS, environment.getProperty("mysql.password"));

        properties.put(SHOW_SQL, environment.getProperty("hibernate.show_sql"));
        //todo schema validation fix
        //properties.put(HBM2DDL_AUTO, environment.getProperty("hibernate.hbm2ddl.auto"));

        properties.put(C3P0_MIN_SIZE, environment.getProperty("hibernate.c3p0.min_size"));
        properties.put(C3P0_MAX_SIZE, environment.getProperty("hibernate.c3p0.max_size"));
        properties.put(C3P0_ACQUIRE_INCREMENT, environment.getProperty("hibernate.c3p0.acquire_increment"));
        properties.put(C3P0_TIMEOUT, environment.getProperty("hibernate.c3p0.timeout"));
        properties.put(C3P0_MAX_STATEMENTS, environment.getProperty("hibernate.c3p0.max_statements"));

        factoryBean.setHibernateProperties(properties);
        factoryBean.setAnnotatedClasses(
                Answer.class,
                Authority.class,
                Category.class,
                Question.class,
                Subcategory.class,
                Tag.class,
                Test.class,
                User.class,
                Assignment.class,
                SubmittedQuestion.class,
                SubmittedAnswer.class,
                Role.class
        );

        return factoryBean;
    }

    @Bean
    public HibernateTransactionManager getTransactionManager(){
        HibernateTransactionManager transactionManager = new HibernateTransactionManager();
        transactionManager.setSessionFactory(getSessionFactory().getObject());
        return transactionManager;
    }
}
