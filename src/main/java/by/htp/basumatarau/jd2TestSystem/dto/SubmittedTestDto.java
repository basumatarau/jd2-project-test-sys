package by.htp.basumatarau.jd2TestSystem.dto;

import java.util.Set;

public class SubmittedTestDto {
    private Integer id;
    private Set<SubmittedTestDto> submittedTestDtos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Set<SubmittedTestDto> getSubmittedTestDtos() {
        return submittedTestDtos;
    }

    public void setSubmittedTestDtos(Set<SubmittedTestDto> submittedTestDtos) {
        this.submittedTestDtos = submittedTestDtos;
    }
}
