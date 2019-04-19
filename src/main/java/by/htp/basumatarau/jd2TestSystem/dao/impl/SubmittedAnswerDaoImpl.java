package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.SubmittedAnswerDao;
import by.htp.basumatarau.jd2TestSystem.model.SubmittedAnswer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubmittedAnswerDaoImpl implements SubmittedAnswerDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(SubmittedAnswer answer) {
        sessionFactory.getCurrentSession().save(answer);
    }
}
