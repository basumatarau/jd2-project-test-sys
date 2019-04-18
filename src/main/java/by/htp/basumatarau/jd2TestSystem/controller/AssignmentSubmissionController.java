package by.htp.basumatarau.jd2TestSystem.controller;

import by.htp.basumatarau.jd2TestSystem.dto.AnswerDto;
import by.htp.basumatarau.jd2TestSystem.dto.QuestionDto;
import by.htp.basumatarau.jd2TestSystem.dto.TestDto;
import by.htp.basumatarau.jd2TestSystem.model.Answer;
import by.htp.basumatarau.jd2TestSystem.model.Assignment;
import by.htp.basumatarau.jd2TestSystem.model.Question;
import by.htp.basumatarau.jd2TestSystem.model.User;
import by.htp.basumatarau.jd2TestSystem.model.auth.CustomUser;
import by.htp.basumatarau.jd2TestSystem.service.AssignmentService;
import by.htp.basumatarau.jd2TestSystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashSet;

@RestController
@RequestMapping(value = "/api")
public class AssignmentSubmissionController {

    private static final int ENTRIES_PER_PAGE = 10;

    @Autowired
    private AssignmentService assignmentService;

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/assignment-submission")
    public void submission(){

    }

    @RequestMapping(value = "/assignment-get-test")
    @ResponseBody
    public TestDto getAssignment( ){
        /*CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();*/
        //todo dododo...
        return null;
    }

    @RequestMapping(value = "/assignment-get-test")
    @ResponseBody
    public TestDto getAssignment(
            @RequestParam(value = "id") Integer id){
        /*CustomUser customUser
                = (CustomUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = customUser.getCurrentUser();*/

        Assignment assignmentDetailed = assignmentService.getAssignmentDetailed(1);
        TestDto testDto = new TestDto();
        testDto.setTestId(assignmentDetailed.getMasterTest().getId());
        testDto.setTestName(assignmentDetailed.getMasterTest().getName());
        testDto.setTestDescription(assignmentDetailed.getMasterTest().getDescription());
        testDto.setQuestionDtos(new HashSet<>());
        for (Question question : assignmentDetailed.getMasterTest().getQuestionSet()) {
            QuestionDto questionDto = new QuestionDto();
            questionDto.setBody(question.getBody());
            questionDto.setId(question.getId());
            testDto.getQuestionDtos().add(questionDto);
            questionDto.setAnswerDtos(new HashSet<>());
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
