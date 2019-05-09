package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.LinkedHashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idquestion")
    private int id;

    @Column(name = "body", nullable = false)
    private String body;

    @Column(name = "isOfMultipleChoice")
    private boolean isOfMultipleChoice;

    @ManyToMany(mappedBy = "questionSet")
    private Set<Test> testSet = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "users_iduser")
    private User author;

    @OneToMany(mappedBy = "question",
            cascade = CascadeType.ALL,
            orphanRemoval = true)
    private Set<Answer> answerSet = new LinkedHashSet<>();

    @ManyToOne
    @JoinColumn(name = "categories_idcategory", nullable = true)
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "questions_has_tags",
            inverseJoinColumns = {@JoinColumn(name = "tags_idtag")},
            joinColumns = {@JoinColumn(name = "questions_idquestions")}
    )
    private Set<Tag> tagSet;

    @OneToMany(mappedBy = "masterQuestion")
    private Set<SubmittedQuestion> submittedQuestionSet;

    public Set<Test> getTestSet() {
        return testSet;
    }

    public void setTestSet(Set<Test> testSet) {
        this.testSet = testSet;
    }

    public User getAuthor() {
        return author;
    }

    public void setAuthor(User author) {
        this.author = author;
    }

    public Set<Answer> getAnswerSet() {
        return answerSet;
    }

    public void setAnswerSet(Set<Answer> answerSet) {
        this.answerSet = answerSet;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Set<Tag> getTagSet() {
        return tagSet;
    }

    public void setTagSet(Set<Tag> tagSet) {
        this.tagSet = tagSet;
    }

    public Set<SubmittedQuestion> getSubmittedQuestionSet() {
        return submittedQuestionSet;
    }

    public void setSubmittedQuestionSet(Set<SubmittedQuestion> submittedQuestionSet) {
        this.submittedQuestionSet = submittedQuestionSet;
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public boolean isOfMultipleChoice() {
        return isOfMultipleChoice;
    }

    public void setOfMultipleChoice(boolean ofMultipleChoice) {
        isOfMultipleChoice = ofMultipleChoice;
    }

    public void addAnswer(Answer answer){
        getAnswerSet().add(answer);
        answer.setQuestion(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return id == question.id &&
                isOfMultipleChoice == question.isOfMultipleChoice &&
                Objects.equals(body, question.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body, isOfMultipleChoice);
    }
}
