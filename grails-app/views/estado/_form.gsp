<%@ page import="br.com.acme.planoCerto.Estado" %>



<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'nome', 'error')} required">
	<label for="nome">
		<g:message code="estado.nome.label" default="Nome" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nome" required="" value="${estadoInstance?.nome}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'sigla', 'error')} required">
	<label for="sigla">
		<g:message code="estado.sigla.label" default="Sigla" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sigla" maxlength="2" required="" value="${estadoInstance?.sigla}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'regiao', 'error')} required">
	<label for="regiao">
		<g:message code="estado.regiao.label" default="Regiao" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="regiao" from="${br.com.acme.planoCerto.Regiao?.values()}" keys="${br.com.acme.planoCerto.Regiao.values()*.name()}" required="" value="${estadoInstance?.regiao?.name()}" />

</div>

