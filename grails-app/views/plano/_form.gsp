<%@ page import="br.com.acme.planoCerto.Plano" %>



<div class="fieldcontain ${hasErrors(bean: planoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="plano.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${planoInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: planoInstance, field: 'tipoPlano', 'error')} required">
	<label for="tipoPlano">
		<g:message code="plano.tipoPlano.label" default="Tipo Plano" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tipoPlano" from="${br.com.acme.planoCerto.Plano$TipoPlano?.values()}" keys="${br.com.acme.planoCerto.Plano$TipoPlano.values()*.name()}" required="" value="${planoInstance?.tipoPlano?.name()}" />

</div>

<div class="fieldcontain ${hasErrors(bean: planoInstance, field: 'operadora', 'error')} required">
	<label for="operadora">
		<g:message code="plano.operadora.label" default="Operadora" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="operadora" name="operadora.id" from="${br.com.acme.planoCerto.Operadora.list()}" optionKey="id" required="" value="${planoInstance?.operadora?.id}" class="many-to-one"/>

</div>

