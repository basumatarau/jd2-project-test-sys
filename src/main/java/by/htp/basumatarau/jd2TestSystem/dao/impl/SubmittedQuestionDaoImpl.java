package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.SubmittedQuestionDao;
import by.htp.basumatarau.jd2TestSystem.model.SubmittedQuestion;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubmittedQuestionDaoImpl implements SubmittedQuestionDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(SubmittedQuestion question) {
        sessionFactory.getCurrentSession().save(question);
    }
}
