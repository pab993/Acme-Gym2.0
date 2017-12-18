<%--
 * header.jsp
 *
 * Copyright (C) 2017 Universidad de Sevilla
 * 
 * The use of this project is hereby constrained to the conditions of the 
 * TDG Licence, a copy of which you may download from 
 * http://www.tdg-seville.info/License.html
 --%>

<%@page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>

<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags"%>

<div>
	<img src="images/logo.png" alt="Acme-Gym Co., Inc." />
</div>

<div>
	<ul id="jMenu">
	
		<li><a class="fNiv" href="activity/search.do"><spring:message code="master.page.activity.search" /></a></li>
	
		<!-- Do not forget the "fNiv" class for the first level links !! -->
		<security:authorize access="hasRole('ADMIN')">
			<li><a class="fNiv" href="administrator/listOfManagers.do"><spring:message code="master.page.manager.list" /></a></li>
			<li><a class="fNiv" href="activity/list.do"><spring:message code="master.page.activity.list" /></a></li>
			<li><a class="fNiv" href="gym/list.do"><spring:message code="master.page.gym.list" /></a></li>
			<li><a class="fNiv" href="administrator/dashboard.do"><spring:message code="master.page.administrator.dashboard" /></a></li>
			
		</security:authorize>
		
		<security:authorize access="hasRole('CUSTOMER')">
			<li><a class="fNiv" href="activity/listForCustomer.do"><spring:message code="master.page.activity.list" /></a></li>
			<li><a class="fNiv" href="gym/listForCustomer.do"><spring:message code="master.page.gym.list" /></a></li>
			
		</security:authorize>
		
		<security:authorize access="hasRole('TRAINER')">
			<li><a class="fNiv" href="activity/list.do"><spring:message code="master.page.activity.list" /></a></li>
			<li><a class="fNiv" href="gym/list.do"><spring:message code="master.page.gym.list" /></a></li>
			
		</security:authorize>
		
		<security:authorize access="hasRole('MANAGER')">
			<li><a class="fNiv" href="activity/list.do"><spring:message code="master.page.activity.list" /></a></li>
			<li><a class="fNiv" href="gym/list.do"><spring:message code="master.page.gym.list" /></a></li>
			<li><a class="fNiv" href="trainer/register.do"><spring:message code="master.page.trainer.register" /></a></li>
			<li><a class="fNiv" href="trainer/listOfTrainer.do"><spring:message code="master.page.manager.trainer.list" /></a></li>
			<li><a class="fNiv" href="gym/myList.do"><spring:message code="master.page.manager.gym" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAnonymous()">
			<li><a class="fNiv" href="activity/list.do"><spring:message code="master.page.activity.list" /></a></li>
			<li><a class="fNiv" href="gym/list.do"><spring:message code="master.page.gym.list" /></a></li>
			<li><a class="fNiv" href="security/login.do"><spring:message code="master.page.login" /></a></li>
			<li><a class="fNiv" href="customer/register.do"><spring:message code="master.page.customer.register" /></a></li>
			<li><a class="fNiv" href="manag/register.do"><spring:message code="master.page.manager.register" /></a></li>
		</security:authorize>
		
		<security:authorize access="isAuthenticated()">
			<li>
				<a class="fNiv"> 
					<spring:message code="master.page.profile" /> 
			        (<security:authentication property="principal.username" />)
				</a>
				<ul>
					<li class="arrow"></li>
					<li><a href="actor/seeProfile.do"><spring:message code="master.page.actor.profile" /></a></li>					
					<li><a href="j_spring_security_logout"><spring:message code="master.page.logout" /> </a></li>
				</ul>
			</li>
		</security:authorize>
	</ul>
</div>

<div>
	<a href="?language=en">en</a> | <a href="?language=es">es</a>
</div>

