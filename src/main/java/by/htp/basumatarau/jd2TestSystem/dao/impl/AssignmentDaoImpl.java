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
    public Assignment getAssignmentById(int id) {
        Query<Assignment> query = sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.masterTest " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "where a.id=:id ", Assignment.class);
        query.setParameter("id", id);
        Assignment singleResult = query.getSingleResult();

        return singleResult;
    }

    @Override
    public void deleteAssignment(Assignment assignment) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("delete from Assignment a " +
                        "where a.id=:id ");
        query.setParameter("id", assignment.getId());
        query.executeUpdate();
    }

    @Override
    public List<Assignment> getAssignmentsForAssignee(User assignee, int first, int entries) {
        Query<Assignment> query = sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.masterTest " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "where a.assignee.id=:id ", Assignment.class);
        query.setParameter("id", assignee.getId());
        List<Assignment> result
                = query.setFirstResult(first)
                    .setMaxResults(entries)
                    .getResultList();

        return result;
    }

    @Override
    public List<Assignment> getAssignmentsForAssigner(User assigner, int first, int entries) {
        Query<Assignment> query = sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.masterTest " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "where a.assigner.id=:id ", Assignment.class);
        query.setParameter("id", assigner.getId());
        List<Assignment> result
                = query.setFirstResult(first)
                .setMaxResults(entries)
                .getResultList();

        return result;
    }

    @Override
    public List<Assignment> getAllAssignmentsForAssignee(User assignee) {
        Query<Assignment> query = sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.masterTest " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "where a.assignee.id=:id ", Assignment.class);
        query.setParameter("id", assignee.getId());
        List<Assignment> result = query.getResultList();

        return result;
    }

    @Override
    public List<Assignment> getAllAssignmentsForAssigner(User assigner) {
        Query<Assignment> query = sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.masterTest " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "where a.assigner.id=:id ", Assignment.class);
        query.setParameter("id", assigner.getId());
        List<Assignment> result = query.getResultList();

        return result;
    }

    @Override
    public long getNumberOfManagedAssignments(User assigner) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Assignment a " +
                        "where a.assigner.id=:id ");
        query.setParameter("id", assigner.getId());
        long result = (long) query.uniqueResult();
        return result;
    }

    @Override
    public long getNumberOfAssignmentsForAssignee(User assignee) {
        Query query = sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Assignment a " +
                        "where a.assignee.id=:id ");
        query.setParameter("id", assignee.getId());
        long result = (long) query.uniqueResult();
        return result;
    }
}
