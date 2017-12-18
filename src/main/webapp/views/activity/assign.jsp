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

		<h1><spring:message code="activity"/></h1>

		<spring:message code="activity.title"/>:
		<jstl:out value="${activity.title}"/>
		<br>
		
		<spring:message code="activity.description"/>:
		<jstl:out value="${activity.description}"/>
		<br>
		

		<br>
		<br>

	<form:form action="activity/assign.do" modelAttribute="activity">
	
		<form:hidden path="id"/>
		<form:hidden path="version"/>
		<form:hidden path="gym"/>
		
		<%-- <acme:select items="${gyms}" itemLabel="name" code="choose.gym" path="gym"/> --%>
		
		<spring:message code="activity.assign"/>:
		<form:select path="trainers" multiple="true">		
			<form:options items="${trainers}" itemLabel="name" />
		</form:select>

		<br>

		<acme:submit id="submitButton" name="save" code="trainer.submit2"/>
		<input type="button" name="cancel" value="<spring:message code="trainer.cancel2" />" onclick="javascript: window.location.replace('activity/listOfGym.do?gymId=${gym.id}')" />

	</form:form>
	
	