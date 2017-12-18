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

		<spring:message code="trainer.name"/>:
		<jstl:out value="${trainer.name}"/>
		<br>
		
		<spring:message code="trainer.surname"/>:
		<jstl:out value="${trainer.surname}"/>
		<br>
		
		<spring:message code="trainer.email"/>:
		<jstl:out value="${trainer.email}"/>
		<br>
		
		<spring:message code="trainer.phone"/>:
		<jstl:out value="${trainer.phone}"/>
		<br>
		
		<spring:message code="trainer.postalAddress"/>:
		<jstl:out value="${trainer.postalAddress}"/>
		<br>
		
		<spring:message code="trainer.city"/>:
		<jstl:out value="${trainer.city}"/>
		<br>
		
		<spring:message code="trainer.country"/>:
		<jstl:out value="${trainer.country}"/>
		<br>

		<br>

	<form:form action="trainer/assign.do" modelAttribute="trainer">
	
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		
		<acme:select items="${gyms}" itemLabel="name" code="choose.gym" path="gym"/>

		<br>

		<acme:submit id="submitButton" name="save" code="trainer.submit2"/>
		<input type="button" name="cancel" value="<spring:message code="trainer.cancel2" />" onclick="javascript: window.location.replace('trainer/listOfTrainer.do')" />

	</form:form>
	
	