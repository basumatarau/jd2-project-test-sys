package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
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

    @OneToMany(mappedBy = "masterTest",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Assignment> assignmentSet;

    @ManyToOne(optional = false)
    @JoinColumn(name = "users_iduser")
    private User author;

    //todo cascade.ALL for test-question relation should be replaced with something narrower
    //so the questions are not necessarily deleted together with tests
    @ManyToMany(
            cascade = {CascadeType.ALL})
    @JoinTable(
            name = "tests_has_questions",
            joinColumns = {@JoinColumn(name = "tests_idtest")},
            inverseJoinColumns = {@JoinColumn(name = "questions_idquestions")})
    private Set<Question> questionSet = new LinkedHashSet<>();

    @OneToMany(mappedBy = "masterTest",
            orphanRemoval = true,
            cascade = CascadeType.ALL)
    private Set<Assignment> assignments;

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean aPublic) {
        isPublic = aPublic;
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

    public Set<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(Set<Assignment> assignments) {
        this.assignments = assignments;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
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

    public void addQuestion(Question question){
        getQuestionSet().add(question);
        question.getTestSet().add(this);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return duration == test.duration &&
                isPublic == test.isPublic &&
                Objects.equals(name, test.name) &&
                Objects.equals(description, test.description) &&
                Objects.equals(author, test.author);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, duration, isPublic, author);
    }
}
