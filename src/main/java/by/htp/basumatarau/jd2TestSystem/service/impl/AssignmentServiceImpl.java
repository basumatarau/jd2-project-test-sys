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
    AssignmentDao assignmentDao;

    @Transactional
    @Override
    public List<Assignment> getAssignmentsForAssigner(User assigner) {
        return assignmentDao.getAssignmentsForAssigner(assigner);
    }

    @Transactional
    @Override
    public List<Assignment> getAssignmentsForAssignee(User assignee) {
        return assignmentDao.getAssignmentsForAssignee(assignee);
    }
}
