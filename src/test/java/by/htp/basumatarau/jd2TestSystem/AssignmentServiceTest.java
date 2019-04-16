package by.htp.basumatarau.jd2TestSystem;

import by.htp.basumatarau.jd2TestSystem.config.AppConfig;
import by.htp.basumatarau.jd2TestSystem.dto.TestAndQuestions;
import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.AssignmentService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import by.htp.basumatarau.jd2TestSystem.service.exception.UserServiceException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AssignmentServiceTest {
    private static AnnotationConfigApplicationContext context;
    static {
        context = new AnnotationConfigApplicationContext();
        context.register(AppConfig.class);
        context.refresh();
    }

    public static void main(String[] args) throws UserServiceException {
        testService(context);
    }

    public static void testService(AnnotationConfigApplicationContext context) throws UserServiceException {
        AssignmentService assignmentService = context.getBean(AssignmentService.class);

        System.out.println(assignmentService);
        Assignment assignment = assignmentService.getAssignmentById(1);
        TestAndQuestions details = assignmentService.getAssignedTestAndQuestions(assignment);
        System.out.println(details);
    }

}
