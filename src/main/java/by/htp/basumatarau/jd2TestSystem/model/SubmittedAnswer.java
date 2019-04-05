package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;

@Entity
@Table(name = "answeredQuestions_has_answers")
public class SubmittedAnswer {

    @Column(name = "givenAnswer")
    private boolean givenAnswer;

    @ManyToOne
    @JoinColumns(
            value = {@JoinColumn(name = "submittedQuestions_idassigned_test"),
            @JoinColumn(name = "submittedQuestions_idquestion")})
    private SubmittedQuestion submittedQuestion;

    @ManyToOne
    @JoinColumn(name = "answers_idanswer")
    private Answer masterAnswer;
}
