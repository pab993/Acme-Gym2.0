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


<form:form action="workout/edit.do" modelAttribute="workout">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	<form:hidden path="gym"/>
	
	<acme:textbox code="workout.title" path="title" mandatory="true"/>
	<acme:textbox code="workout.description" path="description" mandatory="true"/>
		
	<acme:submit name="save" code="workout.submit"/>
	<jstl:if test="${workout.id != 0}">
		<input type="submit" name="delete" value="<spring:message code="workout.delete" />" onclick="return confirm('<spring:message code="workout.confirm" />')" />
	</jstl:if>
	<acme:cancel code="workout.cancel2" url ="workout/listLogin.do?gymId=${gymId }"/>
	
</form:form>