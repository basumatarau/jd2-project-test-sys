package by.htp.basumatarau.jd2TestSystem.dto;

import java.util.Set;

public class QuestionDto {
    private Integer id;
    private String body;
    private Set<AnswerDto> answerDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public Set<AnswerDto> getAnswerDtos() {
        return answerDtos;
    }

    public void setAnswerDtos(Set<AnswerDto> answerDtos) {
        this.answerDtos = answerDtos;
    }
}
