package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;

public interface AssignmentDao
        extends BaseDao<Assignment, Integer> {
    List<Assignment> getAssignmentsForAssignee(User assignee, int idAfter, int entries);
    List<Assignment> getAssignmentsForAssigner(User assigner, int idAfter, int entries);
    long getNumberOfManagedAssignments(User assigner);
    long getNumberOfAssignmentsForAssignee(User assignee);
    Assignment getAssignmentDetailed(Integer id);
    Assignment getAssignmentAndSubmittedQuestions(Integer id);
}
