package by.htp.basumatarau.jd2TestSystem.service;

import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;

import java.util.List;

public interface AssignmentService {
    List<Assignment> getAssignmentsForAssigner(User user, int idAfter, int entries);
    List<Assignment> getAssignmentsForAssignee(User user, int idAfter, int entries);
    long getNumberOfManagedAssignments(User assigner);
    long getNumberOfAssignmentsForAssignee(User assignee);
    void deleteAssignment(Assignment assignment);
    Assignment getAssignmentById(int id);

    void createNewAssignment(Assignment assignment);

    Assignment getAssignmentDetailed(Integer id);
    void updateAssignment(Assignment assignment);
    Assignment getAssignmentAndSubmittedQuestions(Integer id);
}
