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


<form:form action="activity/edit.do" modelAttribute="activity">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="cancelled"/>
	<form:hidden path="gym"/>
	
	<acme:textbox code="activity.title" path="title" mandatory="true"/>
	<acme:textbox code="activity.description" path="description" mandatory="true"/>
	<acme:textarea code="activity.pictures" path="pictures"/>
	<br>
	<spring:message code="activity.day" />(*)
	<form:select path="day">	
		<spring:message code="search.monday" var="mondayHeader"/><form:option value="MONDAY" label="${mondayHeader}" />	
		<spring:message code="search.tuesday" var="tuesdayHeader"/><form:option value="TUESDAY" label="${tuesdayHeader}" />
		<spring:message code="search.wednesday" var="wednesdayHeader"/><form:option value="WEDNESDAY" label="${wednesdayHeader}" />		
		<spring:message code="search.thrusday" var="thrusdayHeader"/><form:option value="THURSDAY" label="${thrusdayHeader}" />	
		<spring:message code="search.friday" var="fridayHeader"/><form:option value="FRIDAY" label="${fridayHeader}" />
		<spring:message code="search.saturday" var="saturdayHeader"/><form:option value="SATURDAY" label="${saturdayHeader}" />
		<spring:message code="search.sunday" var="sundayHeader"/><form:option value="SUNDAY" label="${sundayHeader}" />
	</form:select>
	<acme:textbox code="activity.startTime" path="startTime" mandatory="true" placeholder="hour"/>
	<acme:textbox code="activity.endTime" path="endTime" mandatory="true" placeholder="hour"/>
	<acme:textbox code="activity.availableSeats" path="availableSeats" mandatory="true"/>
	
	<spring:message code="activity.assign"/>:
		<form:select path="trainers" multiple="true">		
			<form:options items="${trainers}" itemLabel="name" />
		</form:select>
		<form:errors cssClass="error" path="trainers" />

		<br>
	

	
	<jstl:if test="${activity.id == 0}">
		<acme:submit name="save" code="activity.submit"/>
	</jstl:if>
	<jstl:if test="${activity.id != 0 and activity.cancelled == false}">
		<input type="submit" name="delete" value="<spring:message code="activity.delete" />" onclick="return confirm('<spring:message code="activity.confirm" />')" />
	</jstl:if>
	<acme:cancel code="activity.cancel" url ="activity/listOfGym.do?gymId=${gym.id}"/>
	
</form:form>