
<%@ page import="br.com.acme.planoCerto.Estado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'estado.label', default: 'Estado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-estado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-estado" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nome" title="${message(code: 'estado.nome.label', default: 'Nome')}" />
					
						<g:sortableColumn property="sigla" title="${message(code: 'estado.sigla.label', default: 'Sigla')}" />
					
						<g:sortableColumn property="regiao" title="${message(code: 'estado.regiao.label', default: 'Regiao')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${estadoInstanceList}" status="i" var="estadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${estadoInstance.id}">${fieldValue(bean: estadoInstance, field: "nome")}</g:link></td>
					
						<td>${fieldValue(bean: estadoInstance, field: "sigla")}</td>
					
						<td>${fieldValue(bean: estadoInstance, field: "regiao")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${estadoInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
