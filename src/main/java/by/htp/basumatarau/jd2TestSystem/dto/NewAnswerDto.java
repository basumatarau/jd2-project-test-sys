package by.htp.basumatarau.jd2TestSystem.dto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Objects;

public class NewAnswerDto {
    @NotNull
    @Size (max = 200)
    private String answerBody;

    private boolean isChecked;

    public String getAnswerBody() {
        return answerBody;
    }

    public void setAnswerBody(String answerBody) {
        this.answerBody = answerBody;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        this.isChecked = checked;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NewAnswerDto that = (NewAnswerDto) o;
        return Objects.equals(answerBody, that.answerBody);
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerBody);
    }
}
