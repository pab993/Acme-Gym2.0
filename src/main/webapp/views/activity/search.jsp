<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags" %>

<form:form action="activity/search.do" modelAttribute="searchTemplateForm">

	<acme:textbox code="searchTemplate.keyword" path="keyword"/>
	<spring:message code="searchTemplate.day" />:
	<form:select path="day">
		<spring:message code="search.empty" var="emptyHeader"/><form:option value="" label="${emptyHeader}" />	
		<spring:message code="search.monday" var="mondayHeader"/><form:option value="MONDAY" label="${mondayHeader}" />	
		<spring:message code="search.tuesday" var="tuesdayHeader"/><form:option value="TUESDAY" label="${tuesdayHeader}" />
		<spring:message code="search.wednesday" var="wednesdayHeader"/><form:option value="WEDNESDAY" label="${wednesdayHeader}" />		
		<spring:message code="search.thrusday" var="thrusdayHeader"/><form:option value="THURSDAY" label="${thrusdayHeader}" />	
		<spring:message code="search.friday" var="fridayHeader"/><form:option value="FRIDAY" label="${fridayHeader}" />
		<spring:message code="search.saturday" var="saturdayHeader"/><form:option value="SATURDAY" label="${saturdayHeader}" />
		<spring:message code="search.sunday" var="sundayHeader"/><form:option value="SUNDAY" label="${sundayHeader}" />
	</form:select>
	
	<acme:textbox code="searchTemplate.time" path="time" placeholder="hour"/>
	
	<acme:submit code="searchTemplate.search" name="search"/>

</form:form>

<br>
<br>
		
	<display:table name = "activities" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >

			<spring:message code = "activity.pictures" var = "picturesHeader" />
			<display:column title = "${picturesHeader}">
				<jstl:forEach items="${row.pictures}" var="picture">
					<img height="48" width="48" src="${picture}">
				</jstl:forEach>
			</display:column>

			<spring:message code = "activity.title" var = "titleHeader" />
			<display:column property = "title" title = "${titleHeader}" />
		
			<spring:message code = "activity.description" var="descriptionHeader" />
			<display:column property="description" title="${descriptionHeader}" />
			
			<spring:message code = "activity.day" var = "dayHeader" />
			<display:column property = "day" title = "${dayHeader}" />
			
			<spring:message code = "activity.startTime" var = "startHeader" />
			<display:column property = "startTime" title = "${startHeader}" />
			
			<spring:message code = "activity.endTime" var = "endHeader" />
			<display:column property = "endTime" title = "${endHeader}" />
			
			<spring:message code = "activity.availableSeats" var = "availableSeatsHeader" />
			<display:column property = "availableSeats" title = "${availableSeatsHeader}" />
			
			<display:column>
					<a href="trainer/listByActivity.do?activityId=${row.id}">
						<spring:message code="activity.listOfTrainers" />
					</a>
			</display:column>
			
			<display:column>
					<a href="gym/listByActivity.do?activityId=${row.id}">
						<spring:message code="activity.listGym" />
					</a>
			</display:column>
				
	</display:table>


		<input type="button" name="cancel" value="<spring:message code="activity.cancel" />"
		onclick="javascript: window.location.replace('/Acme-Gym')" />