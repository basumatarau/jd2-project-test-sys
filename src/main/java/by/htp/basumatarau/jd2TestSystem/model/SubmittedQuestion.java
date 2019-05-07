package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "submittedQuestions")
public class SubmittedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsubmittedQuestion")
    private int id;

    @Column(name = "feedback")
    private String feedback;

    @ManyToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey,
            nullable = false,
            name = "idassigned_test",
            referencedColumnName = "idassigned_test")
    private Assignment submittedTest;

    @OneToOne(optional = false)
    @JoinColumn(foreignKey = @ForeignKey,
            nullable = false,
            name = "idquestion",
            referencedColumnName = "idquestion")
    private Question masterQuestion;

    @OneToMany(mappedBy = "submittedQuestion",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<SubmittedAnswer> submittedAnswerSet;

    public void addSubmittedAnswer(SubmittedAnswer answer){
        answer.setSubmittedQuestion(this);
        getSubmittedAnswerSet().add(answer);
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public Assignment getSubmittedTest() {
        return submittedTest;
    }

    public void setSubmittedTest(Assignment submittedTest) {
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
