<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing table -->
	
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
			
		<jstl:if test="${row.gym.closed == false }">
			<display:column>
					<a href="gym/listByActivity.do?activityId=${row.id}">
						<spring:message code="activity.listGym" />
					</a>
			</display:column>
		</jstl:if>	
		
		<security:authorize access="hasRole('MANAGER')">
			<jstl:if test="${manager != null }">
			<display:column>
				<jstl:if test="${row.cancelled == false && row.gym.manager eq manager}">
					<a href="activity/cancel.do?activityId=${row.id}">
						<spring:message code="activity.cancel2" />
					</a>
				</jstl:if>	
			</display:column>
			</jstl:if>
		</security:authorize>
		
		<jstl:if test="${var }">
				<security:authorize access="hasRole('CUSTOMER')">
					
					<display:column>
	
						<jstl:choose>
			
							<jstl:when test="${row.gym.customers.contains(principal) && fn:length(row.customers) < row.availableSeats && !row.customers.contains(principal)}">
								<a href="activity/joinActivity.do?activityId=${row.id }"><spring:message
										code="activity.joinActivity" /></a>
			
							</jstl:when>
							
							<jstl:when test="${row.customers.contains(principal)}">
								<a href="activity/leaveActivity.do?activityId=${row.id }"><spring:message
										code="activity.leaveActivity" /></a>
							</jstl:when>
							
							<jstl:otherwise>
								--
							</jstl:otherwise>
	
			
						</jstl:choose>
						
					</display:column>
					
				</security:authorize>		
			</jstl:if>
		
		
</display:table>

<jstl:if test="${gym.closed == false}">
	<security:authorize access="hasRole('MANAGER')">
		<a href="activity/create.do?gymId=${gym.id}">
			<spring:message code="activity.create" />
		</a>
	</security:authorize>
</jstl:if>

<br>

	<input type="button" name="cancel" value="<spring:message code="activity.cancel" />" onclick="javascript: window.location.replace('/Acme-Gym')" />