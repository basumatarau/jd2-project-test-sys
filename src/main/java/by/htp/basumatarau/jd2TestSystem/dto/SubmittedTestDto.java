package by.htp.basumatarau.jd2TestSystem.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class SubmittedTestDto {
    @NotNull
    private Integer id;
    private Set<@Valid SubmittedQuestionDto> submittedQuestionDtos;

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
