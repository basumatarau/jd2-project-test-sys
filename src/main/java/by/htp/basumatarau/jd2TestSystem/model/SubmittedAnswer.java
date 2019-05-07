package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "submittedQuestions_has_answers")
public class SubmittedAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsubmittedAnswer")
    private int id;

    //the given answer may be of any type (in this case it's boolean for simplicity)
    @Column(name = "givenAnswer")
    private boolean givenAnswer;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey,
            nullable = false,
            name = "submittedQuestions_idsubmittedQuestion",
            referencedColumnName = "idsubmittedQuestion")
    private SubmittedQuestion submittedQuestion;

    @OneToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey,
            nullable = false,
            name = "answers_idanswer",
            referencedColumnName = "idanswer")
    private Answer masterAnswer;

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

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
