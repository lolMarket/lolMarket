<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Login</title>
</head>
<body>
<f:view>
<h:form>
	<div>
		${loginController.errorDescription}
	</div>
	<br>
	<br>
	<div> Email 
		<h:inputText value="#{loginController.email}"	id = "email"
			required = "true"
			requiredMessage = "Email is mandatory"
			validatorMessage="Wrong email format">
			<f:validateRegex pattern="[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}"/>
		</h:inputText>
		<h:message for = "email" styleClass = "error" />
	</div>
	<div> Password 
		<h:inputSecret value="#{loginController.password}" id = "password" 
			required = "true"
			requiredMessage = "Password is mandatory"/> 
		<h:message for = "password" styleClass = "error" />
	</div>
	<br>
	<hr>
	<br>
	<div> <h:commandButton value="Login" action="#{loginController.login}" /></div>
</h:form> 		
</f:view>
</body>
</html>