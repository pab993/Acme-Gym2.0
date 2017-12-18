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


<form:form action="gym/edit.do" modelAttribute="gym">
	
	<form:hidden path="id" />
	<form:hidden path="version" />
	
	<acme:textbox code="gym.logo" path="logo" mandatory="true"/>
	<acme:textbox code="gym.name" path="name" mandatory="true"/>
	<acme:textbox code="gym.address" path="address" mandatory="true"/>
	<acme:textbox code="gym.fee" path="fee" mandatory="true"/>
	
	
	<acme:submit name="save" code="gym.submit"/>
	<jstl:if test="${gym.id != 0 and gym.closed == false}">
		<input type="submit" name="delete" value="<spring:message code="gym.delete" />" onclick="return confirm('<spring:message code="gym.confirm" />')" />
	</jstl:if>
	<acme:cancel code="gym.cancel" url ="gym/myList.do"/>
	
</form:form>