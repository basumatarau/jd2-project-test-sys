package by.htp.basumatarau.jd2TestSystem.service;

import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;

public interface AssignmentService {
    List<Assignment> getAssignmentsForAssigner(User user);
    List<Assignment> getAssignmentsForAssignee(User user);
}
