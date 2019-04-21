package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.AnswerDao;
import by.htp.basumatarau.jd2TestSystem.model.Answer;
import by.htp.basumatarau.jd2TestSystem.service.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AnswerServiceImpl implements AnswerService {

    @Autowired
    private AnswerDao answerDao;

    @Transactional
    @Override
    public void createNewAnswer(Answer answer) {
        answerDao.persist(answer);
    }
}
