package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dto.*;
import by.htp.basumatarau.jd2TestSystem.model.*;
import by.htp.basumatarau.jd2TestSystem.service.AssignmentService;
import by.htp.basumatarau.jd2TestSystem.service.SubmittedAnswerService;
import by.htp.basumatarau.jd2TestSystem.service.SubmittedQuestionService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api")
public class AssignmentController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserService userService;

    @Autowired
    private SubmittedAnswerService submittedAnswerService;

    @Autowired
    private SubmittedQuestionService submittedQuestionService;

    @RequestMapping(value = "/assignment-test", method = RequestMethod.POST)
    public ResponseEntity getAssignment(@RequestBody SubmittedTestDto dto){
        /*CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();*/
        //todo auth

        Assignment assignmentDetailed = assignmentService.getAssignmentDetailed(dto.getId());
        Set<SubmittedQuestion> submittedQuestions = dto.getSubmittedQuestionDtos().stream()
                .map(dtoSq -> {
                    SubmittedQuestion submittedQuestion = new SubmittedQuestion();
                    submittedQuestion.setMasterQuestion(
                            assignmentDetailed.getMasterTest().getQuestionSet().stream()
                                    .filter(q -> q.getId() == dtoSq.getId()).findAny().get()
                    );
                    submittedQuestion.setSubmittedTest(assignmentDetailed);
                    submittedQuestionService.persistNewSubmission(submittedQuestion);
                    Set<SubmittedAnswer> submittedAnswerSet
                            = dtoSq.getSubmittedAnswerDtos().stream()
                            .map(dtoSa -> {
                                SubmittedAnswer submittedAnswer = new SubmittedAnswer();
                                submittedAnswer.setMasterAnswer(
                                        submittedQuestion.getMasterQuestion()
                                                .getAnswerSet().stream()
                                                .filter(a -> a.getId() == dtoSa.getId())
                                                .findAny().get()
                                );
                                submittedAnswer.setGivenAnswer(dtoSa.isAnswer());
                                submittedAnswer.setSubmittedQuestion(submittedQuestion);
                                submittedAnswerService.persistNewSubmission(submittedAnswer);
                                return submittedAnswer;
                            }).collect(Collectors.toSet());
                    return submittedQuestion;
                }).collect(Collectors.toSet());
        assignmentDetailed.setSubmittedQuestionSet(submittedQuestions);
        assignmentDetailed.setSubmitted(true);
        assignmentService.mergeAssignment(assignmentDetailed);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/assignment-test", method = RequestMethod.GET)
    @ResponseBody
    public TestDto getAssignment(
            @RequestParam(value = "id") Integer id){
        /*CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();*/

        Assignment assignmentDetailed = assignmentService.getAssignmentDetailed(id);
        TestDto testDto = new TestDto();
        testDto.setTestId(assignmentDetailed.getId());
        testDto.setTestName(assignmentDetailed.getMasterTest().getName());
        testDto.setTestDescription(assignmentDetailed.getMasterTest().getDescription());
        testDto.setQuestionDtos(new LinkedList<>());
        for (Question question : assignmentDetailed.getMasterTest().getQuestionSet()) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setBody(question.getBody());
            questionDto.setId(question.getId());
            testDto.getQuestionDtos().add(questionDto);
            questionDto.setAnswerDtos(new LinkedList<>());
            for (Answer answer : question.getAnswerSet()) {
                AnswerDto answerDto = new AnswerDto();
                answerDto.setBody(answer.getBody());
                answerDto.setId(answer.getId());
                answerDto.setGivenAnswer(false);
                questionDto.getAnswerDtos().add(answerDto);
            }
        }

        return testDto;
    }
}
