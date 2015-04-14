<%@ page import="museetoulouse.DemandeVisiteMusee" %>



<div class="fieldcontain ${hasErrors(bean: demandeVisiteMuseeInstance, field: 'musee', 'error')} required">
	<label for="musee">
		<g:message code="demandeVisiteMusee.musee.label" default="Musee" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="myField" value="${MuseeInstance.name}" />

</div>

<div class="fieldcontain ${hasErrors(bean: demandeVisiteMuseeInstance, field: 'demandeVisite', 'error')} required">
	<label for="demandeVisite">
		<g:message code="demandeVisiteMusee.demandeVisite.label" default="Demande Visite" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="demandeVisite" name="demandeVisite.id" from="${museetoulouse.DemandeVisite.list()}" optionKey="id" required="" value="${demandeVisiteMuseeInstance?.demandeVisite?.id}" class="many-to-one"/>

</div>

