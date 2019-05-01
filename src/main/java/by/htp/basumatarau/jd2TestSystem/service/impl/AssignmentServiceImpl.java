package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AssignmentDao;
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

    @Transactional
    @Override
    public Assignment getAssignmentAndSubmittedQuestions(Integer id) {
        return assignmentDao.getAssignmentAndSubmittedQuestions(id);
    }

    @Transactional
    @Override
    public void updateAssignment(Assignment assignment) {
        assignmentDao.saveOrUpdate(assignment);
    }

    @Transactional
    @Override
    public Assignment getAssignmentDetailed(Integer id) {
        return assignmentDao.getAssignmentDetailed(id);
    }

    @Transactional
    @Override
    public void createNewAssignment(Assignment assignment) {
        assignmentDao.save(assignment);
    }

    @Transactional
    @Override
    public void deleteAssignment(Assignment assignment) {
        assignmentDao.delete(assignment);
    }

    @Transactional
    @Override
    public Assignment getAssignmentById(int id) {
        return assignmentDao.findById(id);
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
    public List<Assignment> getAssignmentsForAssigner(User assigner, int idAfter, int entries) {
        return assignmentDao.getAssignmentsForAssigner(assigner, idAfter, entries);
    }

    @Transactional
    @Override
    public List<Assignment> getAssignmentsForAssignee(User assignee, int idAfter, int entries) {
        return assignmentDao.getAssignmentsForAssignee(assignee, idAfter, entries);
    }
}
