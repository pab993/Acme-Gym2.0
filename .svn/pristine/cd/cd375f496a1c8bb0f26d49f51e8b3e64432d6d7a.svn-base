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
			
			<display:column>
					<a href="activity/listByGym.do?gymId=${row.id}">
						<spring:message code="gym.listActivities" />
					</a>
			</display:column>
			
			<jstl:if test="${var }">
				<security:authorize access="hasRole('CUSTOMER')">
					
					<display:column>
	
						<jstl:choose>
			
							<jstl:when test="${!row.customers.contains(principal)}">
								<a href="gym/joinGym.do?gymId=${row.id }"><spring:message
										code="gym.joinGym" /></a>
			
							</jstl:when>
							
							<jstl:when test="${row.customers.contains(principal)}">
								<a href="gym/leaveGym.do?gymId=${row.id }"><spring:message
										code="gym.leaveGym" /></a>
							</jstl:when>
	
			
						</jstl:choose>
						
					</display:column>
					
				</security:authorize>		
			</jstl:if>
						
</display:table>


			<input type="button" name="cancel" value="<spring:message code="gym.cancel" />"
		onclick="javascript: window.location.replace('/Acme-Gym')" />
