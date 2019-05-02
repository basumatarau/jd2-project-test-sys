package by.htp.basumatarau.jd2TestSystem.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.util.Set;

public class SubmittedQuestionDto {
    @NotNull
    private Integer id;

    private Set<@Valid SubmittedAnswerDto> submittedAnswerDtos;

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
