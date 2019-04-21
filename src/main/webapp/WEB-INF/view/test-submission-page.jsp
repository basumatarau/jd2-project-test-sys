<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>test system</title>
    <jsp:include page="include/default-js-css-res.jsp"/>

    <style>
        div.question-form {
            margin: 10px;
            padding: 10px;
            border-style: solid;
            border-width: 2px;
            border-color: #666699;
            border-radius: 10px;
        }

        div.answer-form{
            margin: 4px;
            padding: 4px;
        }

        div.test-question{
            margin: 10px;
            padding: 15px;
        }

        p.answer-body{
            margin: 5px;
            padding: 5px;
        }

        div.answer-checkbox {
            margin: 5px;
            padding: 5px;
        }

        div.submit-test-div{
            margin: 15px;
            padding: 15px;
        }

        #assignment-details{
            margin: 20px;
            padding: 20px;
        }
    </style>
</head>
<body>
    <jsp:include page="include/header.jsp" />

    <div class="container">
        <div id="assignment-details" >
            <div>
                <p id="test-name"></p>
            </div>
            <div>
                <p id="test-description"></p>
            </div>
        </div>

        <div id="test-place-holder">
            <div>
            </div>
        </div>

        <div id="answer-form-template" hidden>
            <div>
                <div class="answer-checkbox">
                    <input type="checkbox" class="should-be-checked" value="0">
                </div>
                <div>
                    <p class="answer-body"></p>
                </div>
            </div>
        </div>
        <div id="question-form-template" hidden>
            <div>
                <div class="test-question">
                    <p class="test-question-body"></p>
                </div>
                <div class="test-answers">

                </div>
            </div>
        </div>

        <div id="submit-test-div">
            <button type="button" class="btn btn-success btn-lg btn-block" id="submit-test">submit test</button>
        </div>
    </div>

    <script type="text/javascript">
        $(document).ready(function() {
            $.ajax({
                method: "GET",
                url: "${pageContext.request.contextPath}/api/assignment-test?id=${id}"
            }).then(function(testDto) {
            
                document.getElementById("test-name").innerHTML = testDto.testName;
                document.getElementById("test-description").innerHTML = testDto.testDescription;
                var placeHolder = document.getElementById("test-place-holder").firstElementChild;
                placeHolder.setAttribute("class", "test-container");
                placeHolder.setAttribute("id", testDto.testId);
                for (var i = 0; i < testDto.questionDtos.length; i++) {
                    var question = testDto.questionDtos[i];
                    var questionDivTemplate = document.getElementById("question-form-template").firstElementChild;
                    var questionDiv = questionDivTemplate.cloneNode(true);
                    questionDiv.getElementsByClassName("test-question-body")[0].innerHTML = question.body;
                    questionDiv.setAttribute("class", "question-form");
                    questionDiv.setAttribute("id", question.id);
                    for (var j = 0; j < question.answerDtos.length; j++) {
                        var answer = question.answerDtos[j];
                        var answerDivTemplate = document.getElementById("answer-form-template").firstElementChild;
                        var answerDiv = answerDivTemplate.cloneNode(true);
                        answerDiv.getElementsByClassName("answer-body")[0].innerHTML = answer.body;
                        answerDiv.setAttribute("class", "answer-form row");
                        answerDiv.setAttribute("id", answer.id);
                        questionDiv.getElementsByClassName("test-answers")[0].appendChild(answerDiv);
                    }
                    placeHolder.appendChild(questionDiv);
                }
               
            });
        });
    </script>

    <script type="text/javascript">
        $( "button" ).click(function() {
            var submission = {};
            var submittedTest = document.getElementsByClassName("test-container")[0];
            submission.id = submittedTest.id;
            var submittedQuestions = [];
            var questions = submittedTest.getElementsByClassName("question-form");
            for(var i = 0; i < questions.length; i++){
                var question = {};
                question.id = questions[i].id;
                submittedAnswers = [];
                var answers = questions[i].getElementsByClassName("answer-form");
                for(var j = 0; j < answers.length; j++){
                    var answer = {};
                    answer.id = answers[j].id;
                    if(answers[j].getElementsByClassName("should-be-checked")[0].checked){
                        answer.answer = true;
                    }else{
                        answer.answer = false;
                    }
                    submittedAnswers.push(answer);
                }
                question.submittedAnswerDtos = submittedAnswers;
                submittedQuestions.push(question);
            }
            submission.submittedQuestionDtos = submittedQuestions;
            console.log(submission);

            $.ajax({
                method: 'POST',
                contentType: 'application/json',
                url: '${pageContext.request.contextPath}/api/assignment-test',
                data: JSON.stringify(submission)
            }).done(function(response) {
                console.log(response);
                if(response == 'OK'){
                    document.getElementById("test-place-holder").remove();
                    document.getElementById("answer-form-template").remove();
                    document.getElementById("question-form-template").remove();

                    var template = document.getElementById("assignment-details");
                    while(template.hasChildNodes()){
                        template.removeChild(template.firstChild);
                    }
                    var success = document.createElement('h2');
                    success.innerHTML = "test has been submitted successfully!"
                    template.appendChild(success);
                    var getBack = document.createElement('a');
                    getBack.setAttribute('href', "${pageContext.request.contextPath}");
                    getBack.innerHTML = "back to home page";
                    template.appendChild(getBack);
                    var submitDiv = document.getElementById("submit-test-div");
                    submitDiv.parentElement.removeChild(submitDiv);
                }
            });
        });
    </script>
</body>
</html>