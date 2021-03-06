package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dto.TestDto;
import by.htp.basumatarau.jd2TestSystem.dto.QuestionDto;
import by.htp.basumatarau.jd2TestSystem.dto.AnswerDto;
import by.htp.basumatarau.jd2TestSystem.dto.NewTestDto;
import by.htp.basumatarau.jd2TestSystem.dto.NewQuestionDto;
import by.htp.basumatarau.jd2TestSystem.dto.NewAnswerDto;
import by.htp.basumatarau.jd2TestSystem.dto.SubmittedTestDto;
import by.htp.basumatarau.jd2TestSystem.model.*;
import by.htp.basumatarau.jd2TestSystem.service.*;
import by.htp.basumatarau.jd2TestSystem.service.exception.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.LinkedList;
import java.util.Set;
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

    @Autowired
    private TestService testService;

    @Autowired
    private QuestionService questionService;

    @Autowired
    private AnswerService answerService;

    @RequestMapping(value = "/assignment-test", method = RequestMethod.POST)
    public ResponseEntity submitAssignment(
            @RequestBody @Valid SubmittedTestDto dto,
            BindingResult bindingResult){
        //todo auth?

        if(bindingResult.hasErrors()){
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }

        //check if the test with the id exists in the database
        Assignment assignmentDetailed = assignmentService.getAssignmentDetailed(dto.getId());

        //throw exceptions if there is no such questions persisted as the contained in the submission
        Set<SubmittedQuestion> submittedQuestions = dto.getSubmittedQuestionDtos().stream()
                .map(dtoSq -> {
                    SubmittedQuestion submittedQuestion = new SubmittedQuestion();
                    submittedQuestion.setMasterQuestion(
                            assignmentDetailed.getMasterTest()
                                    .getQuestionSet()
                                    .stream()
                                    .filter(q -> q.getId() == dtoSq.getId())
                                    .findAny()
                                    //todo .orElseThrow(() ->new Exception())
                                    .get()
                    );
                    //submittedQuestion.setSubmittedTest(assignmentDetailed);
                    //submittedQuestionService.persistNewSubmission(submittedQuestion);
                    assignmentDetailed.addSubmittedQuestion(submittedQuestion);
                    Set<SubmittedAnswer> submittedAnswerSet
                            = dtoSq.getSubmittedAnswerDtos().stream()
                            .map(dtoSa -> {
                                SubmittedAnswer submittedAnswer = new SubmittedAnswer();
                                submittedAnswer.setMasterAnswer(
                                        submittedQuestion.getMasterQuestion()
                                                .getAnswerSet().stream()
                                                .filter(a -> a.getId() == dtoSa.getId())
                                                .findAny()
                                                //todo .orElseThrow(() ->new Exception())
                                                .get()
                                );
                                submittedAnswer.setGivenAnswer(dtoSa.isAnswer());
                                //submittedAnswer.setSubmittedQuestion(submittedQuestion);
                                //submittedAnswerService.persistNewSubmission(submittedAnswer);
                                submittedQuestion.addSubmittedAnswer(submittedAnswer);
                                return submittedAnswer;
                            }).collect(Collectors.toSet());
                    return submittedQuestion;
                }).collect(Collectors.toSet());
        assignmentDetailed.setSubmittedQuestionSet(submittedQuestions);

        assignmentDetailed.setSubmitted(true);

        assignmentService.updateAssignment(assignmentDetailed);

        return ResponseEntity.ok(HttpStatus.OK);
    }

    @RequestMapping(value = "/assignment-test", method = RequestMethod.GET)
    @ResponseBody
    public TestDto getAssignment(
            @RequestParam(value = "id") Integer id){

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

    @RequestMapping(value = "/new-assignment-test", method = RequestMethod.POST)
    public ResponseEntity createSimpleTestAndQuestions(
            @RequestBody @Valid NewTestDto dto,
            BindingResult bindingResult)
                throws ServiceException {
        User currentUser = userService.getUserByUserId(1);
        //todo auth

        if(bindingResult.hasErrors()){
            return ResponseEntity.ok(HttpStatus.NOT_ACCEPTABLE);
        }

        Test test = new Test();
        test.setAuthor(currentUser);
        test.setDescription(dto.getDescription());
        test.setName(dto.getName());
        test.setDuration(dto.getDuration());
        test.setPublic(dto.isPublic());
        for (NewQuestionDto newQuestionDto : dto.getQuestions()) {
            Question question = new Question();
            question.setAuthor(currentUser);
            question.setBody(newQuestionDto.getQuestionBody());
            test.addQuestion(question);
            for (NewAnswerDto newAnswerDto : newQuestionDto.getAnswers()) {
                Answer answer = new Answer();
                answer.setBody(newAnswerDto.getAnswerBody());
                answer.setFalse(!newAnswerDto.isChecked());
                question.addAnswer(answer);
            }
        }
        testService.createNewTest(test);
        return ResponseEntity.ok(HttpStatus.OK);
    }
}
