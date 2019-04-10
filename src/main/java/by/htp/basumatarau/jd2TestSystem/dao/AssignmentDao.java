package by.htp.basumatarau.jd2TestSystem.dao;

import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;

public interface AssignmentDao {
    List<Assignment> getAssignmentsForAssignee(User assignee, int idAfter, int entries);
    List<Assignment> getAssignmentsForAssigner(User assigner, int idAfter, int entries);
    List<Assignment> getAllAssignmentsForAssignee(User assignee);
    List<Assignment> getAllAssignmentsForAssigner(User assigner);
    long getNumberOfManagedAssignments(User assigner);
    long getNumberOfAssignmentsForAssignee(User assignee);

    Assignment getAssignmentById(int id);
    void deleteAssignment(Assignment assignment);
}
