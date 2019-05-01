package by.htp.basumatarau.jd2TestSystem.dao.impl;

import by.htp.basumatarau.jd2TestSystem.dao.BaseDaoImpl;
import by.htp.basumatarau.jd2TestSystem.dao.QuestionDao;
import by.htp.basumatarau.jd2TestSystem.model.Question;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class QuestionDaoImpl
        extends BaseDaoImpl<Question, Integer>
        implements QuestionDao {

    @Autowired
    private SessionFactory sessionFactory;

    public QuestionDaoImpl(){
        setEntityType(Question.class);
    }

    //generic implementation is yet sufficient enough
}
