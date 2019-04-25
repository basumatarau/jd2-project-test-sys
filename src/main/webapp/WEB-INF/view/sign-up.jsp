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
					<label class="col-md-4 control-label" for="logininput">Login</label>
					<div class="col-md-4">
						<input id="firstName" name="firstName" type="text" placeholder=""
							value="testLogin" class="form-control input-md" required="">
					</div>
				</div>

				<!-- Text input-->
				<div class="form-group">
					<label class="col-md-4 control-label" for="logininput">Login</label>
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

				<!-- Button -->
				<c:if test="${occupiedCredentials != null}">
					<p>the email has been already registered...</p>
				</c:if>

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
