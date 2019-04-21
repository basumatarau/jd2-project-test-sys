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
</head>
<body>
    <jsp:include page="include/header.jsp" />

    <div>
        <div class="container">
            <h1>Test constructor</h1>
        </div>
        <div class="container">
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

                <!-- DateTime input -->
                <div class="form-group row">
                    <label class="col-sm-2 col-form-label" for="datetimeinput">Date and time</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control datetimepicker-input" id='due-date-time-picker' data-toggle="datetimepicker" data-target="#due-date-time-picker"/>
                    </div>
                </div>

                <!-- Multiple Checkboxes -->
                <div class="form-group row">
                  <label class="col-sm-2 col-form-label" for="is-public"></label>
                  <div class="col-sm-10">
                      <div class="checkbox">
                        <label for="is-public-0">
                          <input type="checkbox" name="is-public" id="is-public-0" value="0">
                          Do not publish the test
                        </label>
                        </div>
                      </div>
                </div>
            </form>
        </div>
    </div>

    <script type="text/javascript">
        $(function () {
            $('#due-date-time-picker').datetimepicker({
                format: 'YYYY-MM-DD HH:mm:ss'
            });
        });
    </script>

    <script type="text/javascript">
        $( "#add-answer-option" ).click(function() {

        });
    </script>

    <script type="text/javascript">
        $( "#add-question" ).click(function() {

        });
    </script>

    <script type="text/javascript">
        $( "#submit-new-test" ).click(function() {

            $.ajax({
                method: 'POST',
                contentType: 'application/json',
                url: '${pageContext.request.contextPath}/api/assignment-test',
                data: JSON.stringify(submission)
            }).done(function(response) {
                console.log(response);
                if(response == 'OK'){

                }
            });
        });
    </script>

</body>
</html>