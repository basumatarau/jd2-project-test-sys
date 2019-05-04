package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AssignmentDao;
import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class AssignmentDaoImpl
        extends BaseDaoImpl<Assignment, Integer>
        implements AssignmentDao {

    @Autowired
    private SessionFactory sessionFactory;

    public AssignmentDaoImpl() {
        setEntityType(Assignment.class);
    }

    @Override
    public long getNumberOfAssignmentsForAssignee(User assignee) {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Assignment a " +
                        "where a.assignee.id=:id ", Long.class)
                .setParameter("id", assignee.getId())
                .uniqueResult();
    }

    @Override
    public void saveOrUpdate(Assignment assignment) {
        sessionFactory.getCurrentSession().saveOrUpdate(assignment);
    }

    @Override
    public Assignment getAssignmentAndSubmittedQuestions(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "join fetch a.submittedQuestionSet subQuestion " +
                        "join fetch subQuestion.submittedAnswerSet subAnswer " +
                        "join fetch subAnswer.masterAnswer " +
                        "where a.id=:id ", Assignment.class)
                .setParameter("id", id)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public Assignment getAssignmentDetailed(Integer id) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "join fetch a.masterTest " +
                        "join fetch a.masterTest.author " +
                        "join fetch a.masterTest.questionSet question " +
                        "join fetch question.author " +
                        "join fetch question.answerSet " +
                        "where a.id=:id ", Assignment.class)
                .setParameter("id", id)
                .stream()
                .findAny()
                .orElse(null);
    }

    @Override
    public List<Assignment> getAssignmentsForAssignee(User assignee, int first, int entries) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.masterTest " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "where a.assignee.id=:id ", Assignment.class)
                .setParameter("id", assignee.getId())
                .setFirstResult(first)
                .setMaxResults(entries)
                .getResultList();
    }

    @Override
    public List<Assignment> getAssignmentsForAssigner(User assigner, int first, int entries) {
        return sessionFactory.getCurrentSession()
                .createQuery("from Assignment a " +
                        "join fetch a.masterTest " +
                        "join fetch a.assigner " +
                        "join fetch a.assignee " +
                        "where a.assigner.id=:id ", Assignment.class)
                .setParameter("id", assigner.getId())
                .setFirstResult(first)
                .setMaxResults(entries)
                .getResultList();
    }

    @Override
    public long getNumberOfManagedAssignments(User assigner) {
        return sessionFactory.getCurrentSession()
                .createQuery("select count(*) from Assignment a " +
                        "where a.assigner.id=:id ", Long.class)
                .setParameter("id", assigner.getId())
                .uniqueResult();
    }

    public void delete(Assignment assignment){
        sessionFactory.getCurrentSession()
                .createQuery("delete from Assignment a " +
                        "where a.id=:id ")
                .setParameter("id", assignment.getId())
                .executeUpdate();
    }
}
