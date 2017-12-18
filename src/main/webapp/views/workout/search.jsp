<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security"
	uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="acme" tagdir="/WEB-INF/tags"%>

	<jstl:choose>
		<jstl:when test="${principal != null}">
				
			<form:form action="workout/searchLogin.do">
		
				<input type="text" name="keyword" />
				<input type="submit" name="search"
					value="<spring:message code = "workout.search" />" />
		
			</form:form>
			
		</jstl:when>
		<jstl:otherwise>
			
			<form:form action="workout/search.do">
		
				<input type="text" name="keyword" />
				<input type="submit" name="search"
					value="<spring:message code = "workout.search" />" />
		
			</form:form>
			
		</jstl:otherwise>
	</jstl:choose>


	<display:table name = "workouts" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >

			<spring:message code = "workout.title" var = "titleHeader" />
			<display:column property = "title" title = "${titleHeader}" />
		
			<spring:message code = "workout.description" var="descriptionHeader" />
			<display:column property="description" title="${descriptionHeader}" />
			
			
			<jstl:choose>
			
				<jstl:when test="${row.gym.manager == principal}">
				
					<display:column>
						<a href="workout/detailsLogin.do?workoutId=${row.id}">
							<spring:message code="workout.details" />
						</a>
					</display:column>
				
				</jstl:when>
				
				<jstl:otherwise>
				
					<display:column>
						<a href="workout/details.do?workoutId=${row.id}">
							<spring:message code="workout.details" />
						</a>
					</display:column>
					
				</jstl:otherwise>
			
			</jstl:choose>
			

			
			<security:authorize access="hasRole('MANAGER')">
				<jstl:if test="${row.gym.manager == principal }">
								
					<display:column>
						<a href="workout/edit.do?workoutId=${row.id}">
							<spring:message code="workout.edit" />
						</a>
					</display:column>
			
				</jstl:if>
			</security:authorize>
						
</display:table>


			<input type="button" name="cancel" value="<spring:message code="workout.cancel" />"
		onclick="javascript: window.location.replace('/Acme-Gym')" />
