<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

	<display:table name = "managers" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >

			<spring:message code = "manager.name" var = "nameHeader" />
			<display:column property = "name" title = "${nameHeader}" />
		
			<spring:message code = "manager.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}" />
			
			<spring:message code = "manager.email" var = "emailHeader" />
			<display:column property = "email" title = "${emailHeader}" />
			
			<spring:message code = "manager.postalAddress" var = "postalAddressHeader" />
			<display:column property = "postalAddress" title = "${postalAddressHeader}" />
			
			<spring:message code = "manager.city" var = "cityHeader" />
			<display:column property = "city" title = "${cityHeader}" />
			
			<spring:message code = "manager.country" var = "countryHeader" />
			<display:column property = "country" title = "${countryHeader}" />
			
			<security:authorize access="hasRole('ADMIN')">
				<display:column>
					<a href="administrator/ban.do?managerId=${row.id}">
						<jstl:choose>
							<jstl:when test="${row.userAccount.enabled == false}">
								<spring:message code="manager.unban" />
							</jstl:when>
							<jstl:otherwise>
								<spring:message code="manager.ban" />
							</jstl:otherwise>
						</jstl:choose>
					</a>
				</display:column>
			</security:authorize>
			
									
</display:table>


			<input type="button" name="cancel" value="<spring:message code="manager.cancel" />"
		onclick="javascript: window.location.replace('/Acme-Gym')" />