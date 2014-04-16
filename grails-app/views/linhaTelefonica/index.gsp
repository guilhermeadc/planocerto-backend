
<%@ page import="br.com.acme.planoCerto.LinhaTelefonica" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'linhaTelefonica.label', default: 'LinhaTelefonica')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-linhaTelefonica" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-linhaTelefonica" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>		
						<g:sortableColumn property="ddd" title="${message(code: 'linhaTelefonica.ddd.label', default: 'DDD')}" />	

						<g:sortableColumn property="numero" title="${message(code: 'linhaTelefonica.numero.label', default: 'Numero')}" />					
						<th><g:message code="linhaTelefonica.operadora.label" default="Operadora" /></th>					
					</tr>
				</thead>
				<tbody>
				<g:each in="${linhaTelefonicaInstanceList}" status="i" var="linhaTelefonicaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">					
						<td><g:link action="show" id="${linhaTelefonicaInstance.id}">${fieldValue(bean: linhaTelefonicaInstance, field: "ddd")}</g:link></td>						
						<td><g:link action="show" id="${linhaTelefonicaInstance.id}">${fieldValue(bean: linhaTelefonicaInstance, field: "numero")}</g:link></td>
						<td>${fieldValue(bean: linhaTelefonicaInstance, field: "operadora")}</td>					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${linhaTelefonicaInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>