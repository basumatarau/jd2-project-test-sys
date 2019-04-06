package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "submittedQuestions")
public class SubmittedQuestion {

    //todo: nullable?
    @Column(name = "feedback")
    private String feedback;

    @ManyToOne
    @JoinColumn(name = "idassigned_test")
    private SubmittedTest submittedTest;

    @ManyToOne
    @JoinColumn(name = "idquestion")
    private Question masterQuestion;

    @OneToMany(mappedBy = "submittedQuestion")
    private Set<SubmittedAnswer> submittedAnswerSet;

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public SubmittedTest getSubmittedTest() {
        return submittedTest;
    }

    public void setSubmittedTest(SubmittedTest submittedTest) {
        this.submittedTest = submittedTest;
    }

    public Question getMasterQuestion() {
        return masterQuestion;
    }

    public void setMasterQuestion(Question masterQuestion) {
        this.masterQuestion = masterQuestion;
    }

    public Set<SubmittedAnswer> getSubmittedAnswerSet() {
        return submittedAnswerSet;
    }

    public void setSubmittedAnswerSet(Set<SubmittedAnswer> submittedAnswerSet) {
        this.submittedAnswerSet = submittedAnswerSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmittedQuestion that = (SubmittedQuestion) o;
        return Objects.equals(submittedTest, that.submittedTest) &&
                Objects.equals(masterQuestion, that.masterQuestion);
    }

    @Override
    public int hashCode() {
        return Objects.hash(submittedTest, masterQuestion);
    }
}
