
<%@ page import="br.com.acme.planoCerto.LinhaTelefonica" %>
<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main">
        <g:set var="entityName" value="${message(code: 'linhaTelefonica.label', default: 'LinhaTelefonica')}" />
        <title><g:message code="default.show.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#show-linhaTelefonica" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
                </ul>
            </div>
            <div id="show-linhaTelefonica" class="content scaffold-show" role="main">
                <h1><g:message code="default.show.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <ol class="property-list linhaTelefonica">

                <g:if test="${linhaTelefonicaInstance?.ddd}">
                    <li class="fieldcontain">
                        <span id="ddd-label" class="property-label"><g:message code="linhaTelefonica.ddd.label" default="DDD" /></span>				
                        <span class="property-value" aria-labelledby="ddd-label"><g:fieldValue bean="${linhaTelefonicaInstance}" field="ddd"/></span>
                    </li>
                </g:if>

                <g:if test="${linhaTelefonicaInstance?.numero}">
                    <li class="fieldcontain">
                        <span id="numero-label" class="property-label"><g:message code="linhaTelefonica.numero.label" default="Numero" /></span>				
                        <span class="property-value" aria-labelledby="numero-label"><g:fieldValue bean="${linhaTelefonicaInstance}" field="numero"/></span>
                    </li>
                </g:if>

                <g:if test="${linhaTelefonicaInstance?.operadora}">
                    <li class="fieldcontain">
                        <span id="operadora-label" class="property-label"><g:message code="linhaTelefonica.operadora.label" default="Operadora" /></span>					
                        <span class="property-value" aria-labelledby="operadora-label"><g:link controller="operadora" action="show" id="${linhaTelefonicaInstance?.operadora?.id}">${linhaTelefonicaInstance?.operadora?.encodeAsHTML()}</g:link></span>					
                        </li>
                </g:if>			
            </ol>
            <g:form url="[resource:linhaTelefonicaInstance, action:'delete']" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${linhaTelefonicaInstance}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>