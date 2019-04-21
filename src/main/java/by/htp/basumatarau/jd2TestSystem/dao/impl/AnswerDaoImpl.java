package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AnswerDao;
import by.htp.basumatarau.jd2TestSystem.model.Answer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDaoImpl implements AnswerDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(Answer answer) {
        sessionFactory.getCurrentSession().save(answer);
    }
}
