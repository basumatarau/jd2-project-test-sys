package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "answeredQuestions_has_answers")
public class SubmittedAnswer {

    //the given answer may be of any type (in this case it's boolean for simplicity)
    @Column(name = "givenAnswer")
    private boolean givenAnswer;

    @ManyToOne
    @JoinColumns(
            value = {@JoinColumn(name = "submittedQuestions_idassigned_test"),
            @JoinColumn(name = "submittedQuestions_idquestion")})
    private SubmittedQuestion submittedQuestion;

    @ManyToOne
    @JoinColumn(name = "answers_idanswer")
    private Answer masterAnswer;

    public boolean isGivenAnswer() {
        return givenAnswer;
    }

    public void setGivenAnswer(boolean givenAnswer) {
        this.givenAnswer = givenAnswer;
    }

    public SubmittedQuestion getSubmittedQuestion() {
        return submittedQuestion;
    }

    public void setSubmittedQuestion(SubmittedQuestion submittedQuestion) {
        this.submittedQuestion = submittedQuestion;
    }

    public Answer getMasterAnswer() {
        return masterAnswer;
    }

    public void setMasterAnswer(Answer masterAnswer) {
        this.masterAnswer = masterAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmittedAnswer that = (SubmittedAnswer) o;
        return Objects.equals(submittedQuestion, that.submittedQuestion) &&
                Objects.equals(masterAnswer, that.masterAnswer);
    }

    @Override
    public int hashCode() {
        return Objects.hash(submittedQuestion, masterAnswer);
    }
}
