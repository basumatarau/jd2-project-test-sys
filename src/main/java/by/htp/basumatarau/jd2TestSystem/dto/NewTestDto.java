package by.htp.basumatarau.jd2TestSystem.dto;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class NewTestDto {
    @NotNull
    @Size(max = 100)
    private String name;

    @NotNull
    @Size(max = 400)
    private String description;

    @NotNull
    //@Size(max = 100)
    private Integer duration;

    private boolean isPublic;

    private Set<@Valid NewQuestionDto> questions;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<NewQuestionDto> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<NewQuestionDto> questions) {
        this.questions = questions;
    }
}
