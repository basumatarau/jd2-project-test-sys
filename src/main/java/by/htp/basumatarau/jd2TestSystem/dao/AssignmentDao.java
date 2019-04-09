package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;

public interface AssignmentDao {
    List<Assignment> getAssignmentsForAssignee(User assignee);
    List<Assignment> getAssignmentsForAssigner(User assigner);
}
