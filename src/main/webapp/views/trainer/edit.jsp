
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="trainer/register.do" modelAttribute="trainerForm">
	
	<fieldset > 
	
	<legend><b> <spring:message code="trainer.accountData" /> </b></legend>
	
		<acme:textbox code="trainer.username" path="username" mandatory="true"/>
		<br/>
	
		<acme:password code="trainer.password" path="password" mandatory="true"/>
		<br/>
	
		<acme:password code="trainer.repeatPassword" path="repeatPassword" mandatory="true"/>
		<br/>
		
		<%-- <acme:select items="${gyms}" itemLabel="name" code="choose.gym" path="gym"/> --%>
	
	</fieldset>
	
	
	<fieldset > 
	
		<legend><b> <spring:message code="trainer.personalData" /></b> </legend>
	
	
		<acme:textbox code="trainer.name" path="name" mandatory="true"/>
		<br />
			
		<acme:textbox code="trainer.surname" path="surname" mandatory="true"/>
		<br />

		<acme:textbox code="trainer.phone" path="phone" mandatory="true"/>
		<br />
			
		<acme:textbox code="trainer.email" path="email" mandatory="true"/>
		<br />
		
		<acme:textbox code="trainer.postalAddress" path="postalAddress"/>
		<br/>
		
		<acme:textbox code="trainer.city" path="city"/>
		<br />
		
		<acme:textbox code="trainer.country" path="country"/>
		<br/>
	
	</fieldset>
	

	<acme:submit id="submitButton" name="save" code="trainer.submit"/>

</form:form>