package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AssignmentDao;
import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class AssignmentDaoImpl implements AssignmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Assignment> getAssignmentsForAssignee(User assignee) {
        return null;
    }

    @Override
    public List<Assignment> getAssignmentsForAssigner(User assigner) {
        Query<Assignment> query = sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.masterTest " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "where a.assigner.id=:id ", Assignment.class);
        query.setParameter("id",1 /*Integer.toString(assigner.getId())*/);
        List<Assignment> result = query.getResultList();

        return result;
    }
}
