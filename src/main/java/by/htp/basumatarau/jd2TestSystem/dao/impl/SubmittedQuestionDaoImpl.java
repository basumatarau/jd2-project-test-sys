package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.dao.SubmittedQuestionDao;
import by.htp.basumatarau.jd2TestSystem.model.SubmittedQuestion;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class SubmittedQuestionDaoImpl
        extends BaseDaoImpl<SubmittedQuestion, Integer>
        implements SubmittedQuestionDao {

    @Autowired
    private SessionFactory sessionFactory;

    public SubmittedQuestionDaoImpl(){
        setEntityType(SubmittedQuestion.class);
    }

    //implementation specific methods go here
}
