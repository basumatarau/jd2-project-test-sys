package by.htp.basumatarau.jd2TestSystem.dto;

import java.util.Set;

public class TestDto {
    private Integer testId;
    private String testName;
    private String testDescription;

    private Set<QuestionDto> questionDtos;

    public Integer getTestId() {
        return testId;
    }

    public void setTestId(Integer testId) {
        this.testId = testId;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getTestDescription() {
        return testDescription;
    }

    public void setTestDescription(String testDescription) {
        this.testDescription = testDescription;
    }

    public Set<QuestionDto> getQuestionDtos() {
        return questionDtos;
    }

    public void setQuestionDtos(Set<QuestionDto> questionDtos) {
        this.questionDtos = questionDtos;
    }
}
