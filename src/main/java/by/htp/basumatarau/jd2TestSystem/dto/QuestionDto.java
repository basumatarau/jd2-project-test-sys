package by.htp.basumatarau.jd2TestSystem.dto;

import java.util.List;

public class QuestionDto {
    private Integer id;
    private String body;
    private List<AnswerDto> answerDtos;

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

    public List<AnswerDto> getAnswerDtos() {
        return answerDtos;
    }

    public void setAnswerDtos(List<AnswerDto> answerDtos) {
        this.answerDtos = answerDtos;
    }
}
