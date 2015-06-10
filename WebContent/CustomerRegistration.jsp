<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="h" uri="http://java.sun.com/jsf/html" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsf/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>Registration</title>
</head>
<body>
<f:view>
<h:form>
	<div>
		${registrationController.errorDescription}
	</div>
	<br>
	<br>
	<div> First Name 
		<h:inputText value="#{registrationController.firstName}"	 id = "firstName"
			required = "true"
            requiredMessage = "First Name is mandatory"
            validatorMessage = "Names must contain letters">
			<f:validateRegex pattern="[a-z A-Z]+"/>
		</h:inputText>
		<h:message for = "firstName" styleClass = "error" />
	</div>
	<div> Last Name 
		<h:inputText value="#{registrationController.lastName}"	 id = "lastName"
			required = "true"
            requiredMessage = "Last Name is mandatory"
            validatorMessage = "Names must contain letters">
			<f:validateRegex pattern="[a-z A-Z]+"/>
		</h:inputText>
		<h:message for = "lastName" styleClass = "error" />
	</div>
	<div> Birth Date 
		<h:inputText value="#{registrationController.birthDate}" id = "birthDate"
			required = "true"
			requiredMessage = "Birth Date is mandatory"
			converterMessage = "Birth Date must be in day/month/year format">
			<f:convertDateTime pattern="d/M/yyyy" />
		</h:inputText>
		<h:message for = "birthDate" styleClass = "error" />
	</div>
	<div> Address 
		<h:inputText value="#{registrationController.address}"	id = "address"
			required = "true"
			requiredMessage = "Address is mandatory">
		</h:inputText>
		<h:message for = "address" styleClass = "error" />
	</div>
	<div> Email 
		<h:inputText value="#{registrationController.email}"	id = "email"
			required = "true"
			requiredMessage = "Email is mandatory"
			validatorMessage="Wrong email format">
			<f:validateRegex pattern="[-0-9a-zA-Z.+_]+@[-0-9a-zA-Z.+_]+\.[a-zA-Z]{2,4}"/>
		</h:inputText>
		<h:message for = "email" styleClass = "error" />
	</div>
	<div> Password 
		<h:inputSecret value="#{registrationController.password}" id = "password" 
			required = "true"
			requiredMessage = "Password is mandatory"/> 
		<h:message for = "password" styleClass = "error" />
	</div>
	<br>
	<hr>
	<br>
	<div> <h:commandButton value="Register" action="#{registrationController.registerCustomer}" /></div>
</h:form> 		
</f:view>
</body>
</html>