<%@ page import="museetoulouse.Gestionnaire" %>



<div class="fieldcontain ${hasErrors(bean: gestionnaireInstance, field: 'nom', 'error')} required">
	<label for="nom">
		<g:message code="gestionnaire.nom.label" default="Nom" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nom" required="" value="${gestionnaireInstance?.nom}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: gestionnaireInstance, field: 'listeMusees', 'error')} ">
	<label for="listeMusees">
		<g:message code="gestionnaire.listeMusees.label" default="Liste Musees" />
		
	</label>
	
<ul class="one-to-many">
<g:each in="${gestionnaireInstance?.listeMusees?}" var="l">
    <li><g:link controller="musee" action="show" id="${l.id}">${l?.encodeAsHTML()}</g:link></li>
</g:each>
<li class="add">
<g:link controller="musee" action="create" params="['gestionnaire.id': gestionnaireInstance?.id]">${message(code: 'default.add.label', args: [message(code: 'musee.label', default: 'Musee')])}</g:link>
</li>
</ul>


</div>

