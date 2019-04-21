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

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.22.2/moment.min.js"></script>
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/js/tempusdominus-bootstrap-4.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/tempusdominus-bootstrap-4/5.0.1/css/tempusdominus-bootstrap-4.min.css" />

    <style>
        div.test-question {
            margin: 10px;
            padding: 10px;
            border-style: solid;
            border-width: 2px;
            border-color: #666699;
            border-radius: 10px;
        }

        div.test-question-row {
            margin: 10px;
            padding: 10px;
        }

        div.answer-checkbox {
            margin: 6px;
            padding: 6px;
        }
    </style>

</head>
<body>
    <jsp:include page="include/header.jsp" />

    <div>
        <div class="container">
            <h1>Test constructor</h1>
        </div>
        <div id="new-test-submission-form" class="container">
            <div class="container test-meta-info">
                <form>
                    <!-- Text input-->
                    <div class="form-group row">
                      <label class="col-sm-2 col-form-label" for="test-name">Enter the test name</label>
                      <div class="col-sm-10">
                      <input id="test-name" name="test-name" type="text" placeholder="describe the test with a name" class="form-control input-md" required="">
                      <span class="help-block">a couple words should be enough</span>
                      </div>
                    </div>

                    <!-- Textarea -->
                    <div class="form-group row">
                      <label class="col-sm-2 col-form-label" for="test-description">Enter the test description</label>
                      <div class="col-sm-10">
                        <textarea class="form-control" id="test-description" name="test-description">give a few words...</textarea>
                      </div>
                    </div>

                    <!-- Text input-->
                    <div class="form-group row">
                      <label class="col-sm-2 col-form-label" for="test-duration">Enter the test duration (minutes)</label>
                      <div class="col-sm-10">
                      <input id="test-duration" name="test-duration" type="text" placeholder="be reasonable" class="form-control input-md" required="">
                      <span class="help-block">5 minus is rarely enough to complete a test...</span>
                      </div>
                    </div>

                    <!-- Multiple Checkboxes -->
                    <div class="form-group row">
                      <label class="col-sm-2 col-form-label" for="is-public"></label>
                      <div class="col-sm-10">
                          <div class="checkbox">
                            <label for="is-public">
                              <input type="checkbox" id="is-public" value="0">
                              Do not publish the test (so only you can assign it)
                            </label>
                            </div>
                          </div>
                    </div>
                </form>
            </div>

            <div id="test-content" class="container">
                <div class="test-question">
                    <div class="test-question-row row">
                        <label class="col-sm-2" for="test-question">Enter the question body</label>
                        <div class="col-sm-10">
                            <textarea class="form-control test-question-body" name="test-question">here goes the question...</textarea>
                        </div>
                    </div>
                    <div class="test-question-row test-answer row">
                        <label class="col-sm-2" for="test-answer">Enter the answer option body</label>
                        <div class="col-sm-8">
                            <textarea class="form-control test-answer-body" name="test-answer">here goes the answer...</textarea>
                        </div>
                        <div class="col-sm-2 checkbox">
                            <label for="checkbox-one">
                                <input type="checkbox" id="checkbox-one" class="should-be-checked" value="0">
                                should be checked
                            </label>
                        </div>
                    </div>
                    <div class="test-question-row mx-auto">
                        <button type="button" class="btn btn-success" id="add-answer-option" onclick="insertAnswerDiv(this)">add answer</button>
                    </div>
                </div>
            </div>

            <div class="container">
                <button id="add-question" type="button" class="btn btn-success btn-lg btn-block">Add question</button>
                <button id="submit-new-test" type="button" class="btn btn-primary btn-lg btn-block">Done!</button>
            </div>

            <div id="template-test-question" hidden>
                <div>
                    <div class="test-question-row row">
                        <label class="col-sm-2" for="test-question">Enter the question body</label>
                        <div class="col-sm-10">
                            <textarea class="form-control test-question-body" name="test-question">here goes the question...</textarea>
                        </div>
                    </div>
                    <div class="test-question-row test-answer row">
                        <label class="col-sm-2" for="test-answer">Enter the answer option body</label>
                        <div class="col-sm-8">
                            <textarea class="form-control test-answer-body" name="test-answer">here goes the answer...</textarea>
                        </div>
                        <div class="col-sm-2 checkbox">
                            <label for="checkbox-one">
                                <input type="checkbox" id="checkbox-one" class="should-be-checked" value="0">
                                should be checked
                            </label>
                        </div>
                    </div>
                    <div class="test-question-row mx-auto">
                        <button type="button" class="btn btn-success" id="add-answer-option" onclick="insertAnswerDiv(this)">add answer</button>
                    </div>
                </div>
            </div>
        </div>

        <div id="template-question-answer" hidden>
            <div class="test-question-row test-answer row">
                <label class="col-sm-2" for="test-answer">Enter the answer option body</label>
                <div class="col-sm-8">
                    <textarea class="form-control test-answer-body" name="test-answer">here goes the answer...</textarea>
                </div>
                <div class="col-sm-2 checkbox">
                    <label for="checkbox-one">
                    <input type="checkbox" id="checkbox-one" class="should-be-checked" value="0">
                        should be checked
                    </label>
                </div>
            </div>
        </div>
    </div>

    <script type="text/javascript">
        function insertAnswerDiv(element) {
            var templateAnswerDiv = document.getElementById("template-question-answer").firstElementChild;
            var templateAnswerDivClone = templateAnswerDiv.cloneNode(true);
            var addAnswerButtonDiv = element.parentElement;
            addAnswerButtonDiv.parentElement.insertBefore(templateAnswerDivClone, addAnswerButtonDiv);
        }
    </script>

    <script type="text/javascript">
        $( "#add-question" ).click(function() {
            var templateQuestionDiv = document.getElementById("template-test-question").firstElementChild;
            var templateQuestionDivClone = templateQuestionDiv.cloneNode(true);
            templateQuestionDivClone.setAttribute("class", "test-question");
            var testContentDiv = document.getElementById("test-content");
            testContentDiv.appendChild(templateQuestionDivClone);
        });
    </script>

    <script type="text/javascript">
        $( "#submit-new-test" ).click(function() {

            var questions = [];
            var questionElements = document.getElementsByClassName("test-question");

            for(var i = 0; i < questionElements.length; i++){
                var question = {};
                question.questionBody = questionElements[i].getElementsByClassName("test-question-body")[0].value;
                var answerElements = questionElements[i].getElementsByClassName("test-answer");
                var testAnswers = [];
                for(var j = 0; j < answerElements.length; j++){
                    var testAnswer = {};
                    testAnswer.answerBody = answerElements[j].getElementsByClassName("test-answer-body")[0].value;
                    testAnswer.isChecked = answerElements[j].getElementsByClassName("should-be-checked")[0].checked;
                    testAnswers.push(testAnswer);
                }
                question.answers = testAnswers;
                questions.push(question);
            }
            var newTest = {};
            newTest.questions = questions;
            newTest.name = document.getElementById("test-name").value || "";

            newTest.description = document.getElementById("test-description").value || "";
            newTest.duration = document.getElementById("test-duration").value || 0;
            newTest.isPublic = document.getElementById("is-public").checked;

            $.ajax({
                method: 'POST',
                contentType: 'application/json',
                url: '${pageContext.request.contextPath}/api/new-assignment-test',
                data: JSON.stringify(newTest)
            }).done(function(response) {
                console.log(response);
                if(response == 'OK'){
                    submissionForm = document.getElementById("new-test-submission-form");
                    while(submissionForm.hasChildNodes()){
                        submissionForm.removeChild(submissionForm.firstChild);
                    }

                    var success = document.createElement('h2');
                    success.innerHTML = "New test has been created successfully!"
                    submissionForm.appendChild(success);
                    var getBack = document.createElement('a');
                    getBack.setAttribute('href', "${pageContext.request.contextPath}");
                    getBack.innerHTML = "back to home page";
                    submissionForm.appendChild(getBack);
                }
            });
        });
    </script>

</body>
</html>