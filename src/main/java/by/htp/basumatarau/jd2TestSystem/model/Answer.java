package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "answers")
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idanswer")
    private int id;

    @Column(name = "isFalse")
    private boolean isFalse;

    @Column(name = "body")
    private String body;

    @ManyToOne
    @JoinColumn(name = "questions_idquestion")
    private Question question;

    @OneToMany(mappedBy = "masterAnswer")
    private Set<SubmittedAnswer> submittedAnswerSet;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isFalse() {
        return isFalse;
    }

    public void setFalse(boolean flag) {
        isFalse = flag;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return id == answer.id &&
                Objects.equals(body, answer.body);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, body);
    }
}
