package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "users_has_assigned_tests")
public class SubmittedTest {

    @Column(name = "idassigned_test")
    private int id;

    @Column(name = "isSubmitted")
    private boolean isSubmitted;

    @ManyToOne
    @JoinColumn(name = "users_iduser")
    private User assignee;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SubmittedTest that = (SubmittedTest) o;
        return id == that.id &&
                isSubmitted == that.isSubmitted;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, isSubmitted);
    }
}
