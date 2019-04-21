package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.QuestionDao;
import by.htp.basumatarau.jd2TestSystem.model.Question;
import by.htp.basumatarau.jd2TestSystem.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class QuestionServiceImpl implements QuestionService{
    @Autowired
    private QuestionDao questionDao;

    @Transactional
    @Override
    public void createNewQuestion(Question question) {
        questionDao.persist(question);
    }
}
