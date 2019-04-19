package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.SubmittedQuestionDao;
import by.htp.basumatarau.jd2TestSystem.model.SubmittedQuestion;
import by.htp.basumatarau.jd2TestSystem.service.SubmittedQuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubmittedQuestionServiceImpl implements SubmittedQuestionService {

    @Autowired
    private SubmittedQuestionDao submittedQuestionDao;

    @Transactional
    @Override
    public void persistNewSubmission(SubmittedQuestion question) {
        submittedQuestionDao.persist(question);
    }
}
