<%@ page import="br.com.acme.planoCerto.Operadora" %>



<div class="fieldcontain ${hasErrors(bean: operadoraInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="operadora.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${operadoraInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: operadoraInstance, field: 'planos', 'error')} ">
	<label for="planos">
		<g:message code="operadora.planos.label" default="Planos" />
		
	</label>
	
	<ul class="one-to-many">
		<g:each in="${operadoraInstance?.planos}" var="p">
		<li><g:link controller="plano" action="show" id="${p.id}">${p?.nome?.encodeAsHTML()}</g:link></li>
	</g:each>
	<li class="add">
		<g:link controller="plano" action="create" params="['operadora.id': operadoraInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'plano.label', default: 'Plano')])}</g:link>
	</li>
</ul>
</div>

