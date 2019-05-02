package by.htp.basumatarau.jd2TestSystem.dto;

import javax.validation.constraints.NotNull;

public class SubmittedAnswerDto {
    @NotNull
    private Integer id;
    private boolean answer;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
