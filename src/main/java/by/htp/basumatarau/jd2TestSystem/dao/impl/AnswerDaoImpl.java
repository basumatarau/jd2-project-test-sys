package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AnswerDao;
import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.model.Answer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AnswerDaoImpl
        extends BaseDaoImpl<Answer, Integer>
        implements AnswerDao {
    @Autowired
    private SessionFactory sessionFactory;

    public AnswerDaoImpl(){
        setEntityType(Answer.class);
    }
}
