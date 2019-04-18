package by.htp.basumatarau.jd2TestSystem.dto;

import java.util.Set;

public class SubmittedTestDto {
    private Integer id;
    private Set<SubmittedQuestionDto> submittedQuestionDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<SubmittedQuestionDto> getSubmittedQuestionDtos() {
        return submittedQuestionDtos;
    }

    public void setSubmittedQuestionDtos(Set<SubmittedQuestionDto> submittedQuestionDtos) {
        this.submittedQuestionDtos = submittedQuestionDtos;
    }
}
