package by.htp.basumatarau.jd2TestSystem.dto;

public class AnswerDto {
    private Integer id;
    private String body;
    private Boolean givenAnswer;

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

    public Boolean getGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(Boolean givenAnswer) {
        this.givenAnswer = givenAnswer;
    }
}
