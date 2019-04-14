package by.htp.basumatarau.jd2TestSystem;

import by.htp.basumatarau.jd2TestSystem.config.AppConfig;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.HashSet;

public class UserServiceTest {
    private static AnnotationConfigApplicationContext context;
    static {
        context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }

    public static void main(String[] args) throws UserServiceException {
        testService02(context);
    }

    public static void testService(AnnotationConfigApplicationContext context) throws UserServiceException {
        UserService userService = context.getBean(UserService.class);

        System.out.println(userService);
        User user = userService.getUserByUserEmail("testemail@mail.com");
        System.out.println(user.getFirstName());
    }


    public static void testService02(AnnotationConfigApplicationContext context) throws UserServiceException {
        UserService userService = context.getBean(UserService.class);

        System.out.println(userService);
        User user = userService.getUserByUserId(4);
        System.out.println(user.getFirstName());

        userService.addFollowedUser(user, userService.getUserByUserId(1));
    }

}
