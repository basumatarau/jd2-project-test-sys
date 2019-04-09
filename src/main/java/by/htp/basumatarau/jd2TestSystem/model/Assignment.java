package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Date;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users_has_assigned_tests")
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idassigned_test")
    private int id;

    @Column(name = "isSubmitted")
    private boolean isSubmitted;

    @Column(name = "deadline", columnDefinition = "DATETIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Date deadline;

    @Column(name = "name")
    private String name;

    @Column(name = "details")
    private String details;

    @ManyToOne
    @JoinColumn(name = "users_iduser_assignee")
    private User assignee;

    @ManyToOne
    @JoinColumn(name = "users_iduser_assigner")
    private User assigner;

    @ManyToOne
    @JoinColumn(name = "tests_idtest")
    private Test masterTest;

    @OneToMany(mappedBy = "submittedTest")
    private Set<SubmittedQuestion> submittedQuestionSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isSubmitted() {
        return isSubmitted;
    }

    public void setSubmitted(boolean submitted) {
        isSubmitted = submitted;
    }

    public User getAssigner() {
        return assigner;
    }

    public void setAssigner(User assigner) {
        this.assigner = assigner;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Test getMasterTest() {
        return masterTest;
    }

    public void setMasterTest(Test masterTest) {
        this.masterTest = masterTest;
    }

    public Set<SubmittedQuestion> getSubmittedQuestionSet() {
        return submittedQuestionSet;
    }

    public void setSubmittedQuestionSet(Set<SubmittedQuestion> submittedQuestionSet) {
        this.submittedQuestionSet = submittedQuestionSet;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Assignment that = (Assignment) o;
        return id == that.id &&
                isSubmitted == that.isSubmitted &&
                Objects.equals(deadline, that.deadline) &&
                Objects.equals(name, that.name) &&
                Objects.equals(details, that.details) &&
                Objects.equals(assignee, that.assignee) &&
                Objects.equals(assigner, that.assigner) &&
                Objects.equals(masterTest, that.masterTest);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isSubmitted, deadline, name, details, assignee, assigner, masterTest);
    }
}
