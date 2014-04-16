
<%@ page import="br.com.acme.planoCerto.Plano" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'plano.label', default: 'Plano')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-plano" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-plano" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list plano">
			
				<g:if test="${planoInstance?.nome}">
				<li class="fieldcontain">
					<span id="nome-label" class="property-label"><g:message code="plano.nome.label" default="Nome" /></span>
					
						<span class="property-value" aria-labelledby="nome-label"><g:fieldValue bean="${planoInstance}" field="nome"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${planoInstance?.tipoPlano}">
				<li class="fieldcontain">
					<span id="tipoPlano-label" class="property-label"><g:message code="plano.tipoPlano.label" default="Tipo Plano" /></span>
					
						<span class="property-value" aria-labelledby="tipoPlano-label"><g:fieldValue bean="${planoInstance}" field="tipoPlano"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${planoInstance?.operadora}">
				<li class="fieldcontain">
					<span id="operadora-label" class="property-label"><g:message code="plano.operadora.label" default="Operadora" /></span>
					
						<span class="property-value" aria-labelledby="operadora-label"><g:link controller="operadora" action="show" id="${planoInstance?.operadora?.id}">${planoInstance?.operadora?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form url="[resource:planoInstance, action:'delete']" method="DELETE">
				<fieldset class="buttons">
					<g:link class="edit" action="edit" resource="${planoInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
