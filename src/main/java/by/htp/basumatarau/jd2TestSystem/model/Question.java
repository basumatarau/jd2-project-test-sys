package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "questions")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idquestion")
    private int id;

    @Column(name = "body")
    private String body;

    @Column(name = "isOfMultipleChoice")
    private boolean isOfMultipleChoice;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "tests_has_questions",
            inverseJoinColumns = {@JoinColumn(name = "tests_idtest")},
            joinColumns = {@JoinColumn(name = "questions_idquestions")}
    )
    private Set<Test> testSet;

    @ManyToOne
    @JoinColumn(name = "users_iduser")
    private User author;

    @OneToMany(mappedBy = "question")
    private Set<Answer> answerSet;

    @ManyToOne
    @JoinColumn(name = "subcategories_idsubcategory")
    private Subcategory subcategory;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(
            name = "questions_has_tags",
            inverseJoinColumns = {@JoinColumn(name = "tags_idtag")},
            joinColumns = {@JoinColumn(name = "questions_idquestions")}
    )
    private Set<Tag> tagSet;

    @OneToMany(mappedBy = "masterQuestion")
    private Set<SubmittedQuestion> submittedQuestionSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
