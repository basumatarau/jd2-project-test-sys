package by.htp.basumatarau.jd2TestSystem.dto;

import java.util.Set;

public class SubmittedQuestionDto {
    private Integer id;
    private Set<SubmittedAnswerDto> submittedAnswerDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<SubmittedAnswerDto> getSubmittedAnswerDtos() {
        return submittedAnswerDtos;
    }

    public void setSubmittedAnswerDtos(Set<SubmittedAnswerDto> submittedAnswerDtos) {
        this.submittedAnswerDtos = submittedAnswerDtos;
    }
}
