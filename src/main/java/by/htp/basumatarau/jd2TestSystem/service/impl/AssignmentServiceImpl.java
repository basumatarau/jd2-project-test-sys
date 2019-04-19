package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AssignmentDao;
import by.htp.basumatarau.jd2TestSystem.dao.UserDao;
import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.service.AssignmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("assignmentService")
public class AssignmentServiceImpl implements AssignmentService {

    @Autowired
    private AssignmentDao assignmentDao;

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public Assignment getAssignmentAndSubmittedQuestions(Integer id) {
        return assignmentDao.getAssignmentAndSubmittedQuestions(id);
    }

    @Transactional
    @Override
    public void mergeAssignment(Assignment assignment) {
        assignmentDao.merge(assignment);
    }

    @Transactional
    @Override
    public Assignment getAssignmentDetailed(Integer id) {
        return assignmentDao.getAssignmentDetailed(id);
    }

    @Transactional
    @Override
    public void createNewAssignment(Assignment assignment) {
        assignmentDao.persistNewAssignment(assignment);
    }

    @Transactional
    @Override
    public void deleteAssignment(Assignment assignment) {
        assignmentDao.deleteAssignment(assignment);
    }

    @Transactional
    @Override
    public Assignment getAssignmentById(int id) {
        return assignmentDao.getAssignmentById(id);
    }

    @Transactional
    @Override
    public long getNumberOfManagedAssignments(User assigner) {
        return assignmentDao.getNumberOfManagedAssignments(assigner);
    }

    @Transactional
    @Override
    public long getNumberOfAssignmentsForAssignee(User assignee) {
        return assignmentDao.getNumberOfAssignmentsForAssignee(assignee);
    }

    @Transactional
    @Override
    public List<Assignment> getAllAssignmentsForAssigner(User assigner) {
        return assignmentDao.getAllAssignmentsForAssigner(assigner);
    }

    @Transactional
    @Override
    public List<Assignment> getAllAssignmentsForAssignee(User assignee) {
        return assignmentDao.getAllAssignmentsForAssigner(assignee);
    }

    @Transactional
    @Override
    public List<Assignment> getAssignmentsForAssigner(User assigner, int idAfter, int entries) {
        return assignmentDao.getAssignmentsForAssigner(assigner, idAfter, entries);
    }

    @Transactional
    @Override
    public List<Assignment> getAssignmentsForAssignee(User assignee, int idAfter, int entries) {
        return assignmentDao.getAssignmentsForAssignee(assignee, idAfter, entries);
    }
}
