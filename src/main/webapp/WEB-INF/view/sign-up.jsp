<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>test system</title>
    <jsp:include page="include/default-js-css-res.jsp" />

    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-validator/0.4.5/js/bootstrapvalidator.min.js"></script>
    <link rel="stylesheet" type="text/css" href="https://cdnjs.cloudflare.com/ajax/libs/jquery.bootstrapvalidator/0.5.0/css/bootstrapValidator.min.css"/>

    <script type="text/javascript">
        <%@include file="/resources/js/signUpFormValidation.js" %>
    </script>

</head>
<body>
	<jsp:include page="include/header.jsp" />
	<div class="container">

		<form class="form-horizontal" id="singUpForm"
			action="${pageContext.request.contextPath}/sign-up" method="post">
			<fieldset>

				<!-- Form Name -->
				<legend>Sign-up</legend>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="logininput">First name</label>
					<div class="col-md-4">
						<input id="firstName" name="firstName" type="text" placeholder=""
							value="testLogin" class="form-control input-md" required="">
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="logininput">Last name</label>
					<div class="col-md-4">
						<input id="lastName" name="lastName" type="text" placeholder=""
							value="testLogin" class="form-control input-md" required="">
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="emailinput">Email</label>
					<div class="col-md-4">
						<input id="email" name="email" type="text" placeholder=""
							value="testemail@mail.com" class="form-control input-md"
							required="">
					</div>
				</div>

				<!-- Password input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="passwordinput">Password</label>
					<div class="col-md-4">
						<input id="password" name="password" type="password"
							placeholder="" value="testpassword" class="form-control input-md"
							required="">
					</div>
				</div>

				<c:if test="${occupiedCredentials != null}">
					<p>the email has been already registered...</p>
				</c:if>

				<c:if test="${bindingErrors != null}">
					<c:forEach items="${bindingErrors}" var="error">
					    <p>${error.getDefaultMessage()}</p>
					</c:forEach>
				</c:if>

				<c:if test="${signUpError != null}">
					<p>${signUpError}</p>
				</c:if>

				<!-- Button -->
				<div class="form-group">
					<label class="col-md-4 control-label" for="signupsubmit"></label>
					<div class="col-md-4">
						<button id="signupsubmit" name="signupsubmit" type="submit"
							class="btn btn-primary">Sing me up</button>
					</div>
				</div>
			</fieldset>
		</form>

	</div>
</body>
</html>
