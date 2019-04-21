package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.QuestionDao;
import by.htp.basumatarau.jd2TestSystem.model.Question;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDaoImpl implements QuestionDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void persist(Question question) {
        sessionFactory.getCurrentSession().save(question);
    }
}
