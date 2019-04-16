package by.htp.basumatarau.jd2TestSystem.dto;

import by.htp.basumatarau.jd2TestSystem.model.Answer;
import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.Question;
import by.htp.basumatarau.jd2TestSystem.model.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Set;

public class TestAndQuestions {
    private Test test;
    private Set<Question> questions;

    public TestAndQuestions(Assignment assignment){
        this.test = assignment.getMasterTest();
        this.questions = assignment.getMasterTest().getQuestionSet();
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }
}
