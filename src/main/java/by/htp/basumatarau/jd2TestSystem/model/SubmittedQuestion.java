package by.htp.basumatarau.jd2TestSystem.model;

import javax.persistence.*;

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
}
