<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="jstl"	uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>
<%@taglib prefix="display" uri="http://displaytag.sf.net"%>

<!-- Listing table -->


<form:form action="trainer/search.do">

	<input type="text" name="keyword" />
	<input type="submit" name="search"
		value="<spring:message code = "trainer.search" />" />

</form:form>


	
	<display:table name = "trainers" id = "row" requestURI = "${requestURI}" pagesize = "5" class = "displaytag" >

			<spring:message code = "trainer.name" var = "nameHeader" />
			<display:column property = "name" title = "${nameHeader}" />
		
			<spring:message code = "trainer.surname" var="surnameHeader" />
			<display:column property="surname" title="${surnameHeader}" />
			
			<spring:message code = "trainer.email" var = "emailHeader" />
			<display:column property = "email" title = "${emailHeader}" />
			
			<spring:message code = "trainer.postalAddress" var = "postalAddressHeader" />
			<display:column property = "postalAddress" title = "${postalAddressHeader}" />
			
			<spring:message code = "trainer.city" var = "cityHeader" />
			<display:column property = "city" title = "${cityHeader}" />
			
			<spring:message code = "trainer.country" var = "countryHeader" />
			<display:column property = "country" title = "${countryHeader}" />
			
			<display:column>
					<a href="trainer/assign.do?trainerId=${row.id}">
						<spring:message code="trainer.assign" />
					</a>
			</display:column>
									
</display:table>


			<input type="button" name="cancel" value="<spring:message code="trainer.cancel" />"
		onclick="javascript: window.location.replace('/Acme-Gym')" />
