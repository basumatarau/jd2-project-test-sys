package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tests")
public class Test {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idtest")
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "descritption")
    private String description;

    @Column(name = "duration")
    private int duration;

    @Column(name = "isPublic")
    private boolean isPublic;

    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(
            name = "users_has_tests",
            inverseJoinColumns = {@JoinColumn(name = "users_iduser")},
            joinColumns = {@JoinColumn(name = "tests_idtest")}
    )
    private Set<User> users;

    @OneToMany(mappedBy = "masterTest")
    private Set<Assignment> assignmentSet;

    @ManyToOne
    @JoinColumn(name = "users_iduser")
    private User author;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tests_has_questions",
            joinColumns = {@JoinColumn(name = "tests_idtest")},
            inverseJoinColumns = {@JoinColumn(name = "questions_idquestions")}
    )
    private Set<Question> questionSet;

    @OneToMany(mappedBy = "masterTest")
    private Set<Assignment> submittedTestSet;

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
    }

    public Set<User> getUsers() {
        return users;
    }

    public void setUsers(Set<User> users) {
        this.users = users;
    }

    public Set<Assignment> getAssignmentSet() {
        return assignmentSet;
    }

    public void setAssignmentSet(Set<Assignment> assignmentSet) {
        this.assignmentSet = assignmentSet;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Question> getQuestionSet() {
        return questionSet;
    }

    public void setQuestionSet(Set<Question> questionSet) {
        this.questionSet = questionSet;
    }

    public Set<Assignment> getSubmittedTestSet() {
        return submittedTestSet;
    }

    public void setSubmittedTestSet(Set<Assignment> submittedTestSet) {
        this.submittedTestSet = submittedTestSet;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return id == test.id &&
                duration == test.duration &&
                isPublic == test.isPublic &&
                Objects.equals(name, test.name) &&
                Objects.equals(description, test.description) &&
                Objects.equals(author, test.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, duration, isPublic, author);
    }
}
