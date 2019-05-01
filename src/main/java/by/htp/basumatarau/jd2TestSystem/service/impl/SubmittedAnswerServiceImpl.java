package by.htp.basumatarau.jd2TestSystem.service.impl;

import by.htp.basumatarau.jd2TestSystem.dao.SubmittedAnswerDao;
import by.htp.basumatarau.jd2TestSystem.model.SubmittedAnswer;
import by.htp.basumatarau.jd2TestSystem.service.SubmittedAnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class SubmittedAnswerServiceImpl implements SubmittedAnswerService {

    @Autowired
    private SubmittedAnswerDao submittedAnswerDao;

    @Transactional
    @Override
    public void persistNewSubmission(SubmittedAnswer answer) {
        submittedAnswerDao.save(answer);
    }
}
