package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.dao.SubmittedAnswerDao;
import by.htp.basumatarau.jd2TestSystem.model.SubmittedAnswer;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubmittedAnswerDaoImpl
        extends BaseDaoImpl<SubmittedAnswer, Integer>
        implements SubmittedAnswerDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SubmittedAnswerDaoImpl(){
        setEntityType(SubmittedAnswer.class);
    }
}
