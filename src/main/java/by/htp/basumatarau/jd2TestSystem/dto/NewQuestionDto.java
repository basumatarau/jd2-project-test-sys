package by.htp.basumatarau.jd2TestSystem.dto;

import java.util.Objects;
import java.util.Set;

public class NewQuestionDto {
    private String questionBody;
    private Set<NewAnswerDto> answers;

    public String getQuestionBody() {
        return questionBody;
    }

    public void setQuestionBody(String questionBody) {
        this.questionBody = questionBody;
    }

    public Set<NewAnswerDto> getAnswers() {
        return answers;
    }

    public void setAnswers(Set<NewAnswerDto> answers) {
        this.answers = answers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewQuestionDto that = (NewQuestionDto) o;
        return Objects.equals(questionBody, that.questionBody) &&
                Objects.equals(answers, that.answers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionBody, answers);
    }
}
