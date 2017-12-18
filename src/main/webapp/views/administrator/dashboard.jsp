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

<security:authorize access="hasRole('ADMIN')">	
	
	<h2>
		<spring:message code="firstQuery" />
	</h2>
	<display:table name="firstQuery" id="firstQuery" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.min" var="min"/>
			<spring:message code="dashboard.avg" var="avg"/>
			<spring:message code="dashboard.max" var="max"/>
			<spring:message code="dashboard.ds" var="ds"/>
			<display:column title="${min}">
				<h3> <jstl:out value="${firstQuery[0]}" /> </h3> 
			</display:column>
			<display:column title="${avg}">
				<h3> <jstl:out value="${firstQuery[1]}" /> </h3> 
			</display:column>
			<display:column title="${max}">
				<h3> <jstl:out value="${firstQuery[2]}" /> </h3> 
			</display:column>
			<display:column title="${ds}">
				<h3> <jstl:out value="${firstQuery[3]}" /> </h3> 
			</display:column>
		</display:table>
	
	<br>
	
	<h2>
		<spring:message code="secondQuery" />
	</h2>
	<display:table name="secondQuery" id="secondQuery" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.min" var="min"/>
			<spring:message code="dashboard.avg" var="avg"/>
			<spring:message code="dashboard.max" var="max"/>
			<spring:message code="dashboard.ds" var="ds"/>
			<display:column title="${min}">
				<h3> <jstl:out value="${secondQuery[0]}" /> </h3> 
			</display:column>
			<display:column title="${avg}">
				<h3> <jstl:out value="${secondQuery[1]}" /> </h3> 
			</display:column>
			<display:column title="${max}">
				<h3> <jstl:out value="${secondQuery[2]}" /> </h3> 
			</display:column>
			<display:column title="${ds}">
				<h3> <jstl:out value="${secondQuery[3]}" /> </h3> 
			</display:column>
		</display:table>
	
	<br>
	
	<h2>
		<spring:message code="thirstQuery" />
	</h2>
	<display:table name="thirstQuery" id="thirstQuery" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.min" var="min"/>
			<spring:message code="dashboard.avg" var="avg"/>
			<spring:message code="dashboard.max" var="max"/>
			<spring:message code="dashboard.ds" var="ds"/>
			<display:column title="${min}">
				<h3> <jstl:out value="${thirstQuery[0]}" /> </h3> 
			</display:column>
			<display:column title="${avg}">
				<h3> <jstl:out value="${thirstQuery[1]}" /> </h3> 
			</display:column>
			<display:column title="${max}">
				<h3> <jstl:out value="${thirstQuery[2]}" /> </h3> 
			</display:column>
			<display:column title="${ds}">
				<h3> <jstl:out value="${thirstQuery[3]}" /> </h3> 
			</display:column>
		</display:table>
	
	<br>
	
	<h2>
		<spring:message code="sixthQuery" />
	</h2>
	<display:table name="sixthQuery" id="sixthQuery" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.min" var="min"/>
			<spring:message code="dashboard.avg" var="avg"/>
			<spring:message code="dashboard.max" var="max"/>
			<display:column title="${min}">
				<h3> <jstl:out value="${sixthQuery[0]}" /> </h3> 
			</display:column>
			<display:column title="${avg}">
				<h3> <jstl:out value="${sixthQuery[1]}" /> </h3> 
			</display:column>
			<display:column title="${max}">
				<h3> <jstl:out value="${sixthQuery[2]}" /> </h3> 
			</display:column>
		</display:table>
	
	<br>
	
	<h2>
		<spring:message code="seventhQuery" />
	</h2>
	<display:table name="seventhQuery" id="seventhQuery" pagesize="5" requestURI="${requestURI}" class="displaytag">
			<spring:message code="dashboard.min" var="min"/>
			<spring:message code="dashboard.avg" var="avg"/>
			<spring:message code="dashboard.max" var="max"/>
			<display:column title="${min}">
				<h3> <jstl:out value="${seventhQuery[0]}" /> </h3> 
			</display:column>
			<display:column title="${avg}">
				<h3> <jstl:out value="${seventhQuery[1]}" /> </h3> 
			</display:column>
			<display:column title="${max}">
				<h3> <jstl:out value="${seventhQuery[2]}" /> </h3> 
			</display:column>
		</display:table>
	
	<br>
	
	
	<h2>
		<spring:message code="forthQuery" />
	</h2>
	<jstl:forEach items="${forthQuery}" var="item">
		<h4><jstl:out value="${item.name}"/></h4>
	</jstl:forEach>
	
	<br>
	
	<h2>
		<spring:message code="fifthQuery" />
	</h2>
	<jstl:forEach items="${fifthQuery}" var="item">
		<h4><jstl:out value="${item.userAccount.username}"/></h4>
	</jstl:forEach>
	
	<br>
	
	<h2>
		<spring:message code="eighthQuery" />
	</h2>
	<jstl:forEach items="${eighthQuery}" var="item">
		<h4><jstl:out value="${item.title}"/></h4>
	</jstl:forEach>
	
	<br>
	

</security:authorize>