<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing table -->
	
	<display:table name = "gyms" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >

			<spring:message code = "gym.name" var = "nameHeader" />
			<display:column property = "name" title = "${nameHeader}" />
		
			<spring:message code = "gym.logo" var="logoHeader" />
			<display:column property="logo" title="${logoHeader}" />
			
			<spring:message code = "gym.address" var = "addressHeader" />
			<display:column property = "address" title = "${addressHeader}" />
			
			<spring:message code = "gym.fee" var = "feeHeader" />
			<display:column property = "fee" title = "${feeHeader}" />
			
			<spring:message code = "gym.closed" var = "closedHeader" />
			<display:column title = "${closedHeader}">
				<jstl:if test="${row.closed == false }">
					<spring:message code="gym.status.open"/>
				</jstl:if>
				<jstl:if test="${row.closed == true }">
					<spring:message code="gym.status.closed"/>
				</jstl:if>
			</display:column>
			
			<display:column>
					<a href="gym/edit.do?gymId=${row.id}">
						<spring:message code="gym.edit" />
					</a>
			</display:column>
			
			<display:column>
					<a href="activity/listOfGym.do?gymId=${row.id}">
						<spring:message code="gym.listActivities" />
					</a>
			</display:column>
									
</display:table>

	<a href="gym/create.do"><spring:message code="gym.create"/></a>
	<br>
	<br>
	<input type="button" name="cancel" value="<spring:message code="gym.cancel" />"onclick="javascript: window.location.replace('/Acme-Gym')" />
