<%@ page import="br.com.acme.planoCerto.LinhaTelefonica" %>

<div class="fieldcontain ${hasErrors(bean: linhaTelefonicaInstance, field: 'ddd', 'error')} required">
	<label for="ddd">
		<g:message code="linhaTelefonica.ddd.label" default="DDD" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="ddd" required="" value="${linhaTelefonicaInstance?.ddd}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: linhaTelefonicaInstance, field: 'numero', 'error')} required">
	<label for="numero">
		<g:message code="linhaTelefonica.numero.label" default="Numero" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="numero" required="" value="${linhaTelefonicaInstance?.numero}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: linhaTelefonicaInstance, field: 'operadora', 'error')} required">
	<label for="operadora">
		<g:message code="linhaTelefonica.operadora.label" default="Operadora" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="operadora" name="operadora.id" from="${br.com.acme.planoCerto.Operadora.list()}" optionKey="id" required="" value="${linhaTelefonicaInstance?.operadora?.id}" class="many-to-one"/>
</div>

