
<%@ page import="museetoulouse.DemandeVisiteMusee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-demandeVisiteMusee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message message="faire une demande de visite" /></g:link></li>
			</ul>
		</div>


		<div id="list-demandeVisiteMusee" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${(flash.message == "Erreur Pas de Code Valide") || (flash.message == "Erreur impossible de récupérer la liste des demandes")|| (flash.message == "Erreur Il faut remplir/modifier le champs code")}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
            <g:form controller="demandeVisiteMusee" action="codeSearch" method="post">
                Entrez le code de la demande de visite :</br>
                <input type="text" name = "code" size="15" id="codeId">
                <g:actionSubmit id="submitCode"  action="codeSearch" value="Rechercher" />
            </g:form>

            <g:if test="${flash.message == "OK Demande Musee Valide"}">
			<table>
			<thead>
					<tr>
					
						<th><g:message code="demandeVisiteMusee.musee.label" default="Musee" /></th>
					
						<th><g:message code="demandeVisiteMusee.demandeVisite.label" default="Code" /></th>
                        <th><g:message code="demandeVisiteMusee.demandeVisite.label" default="Nombre de Personne" /></th>
                        <th><g:message code="demandeVisiteMusee.demandeVisite.label" default="Status" /></th>
                        <th><g:message code="demandeVisiteMusee.demandeVisite.label" default="Création de la demande de visite" /></th>

                        <g:if test="${demandeVisiteMuseeInstanceList.demandeVisite.statut == "Confirme"}">
                            <th><g:message code="demandeVisiteMusee.demandeVisite.label" default="Date de debut" /></th>
                            <th><g:message code="demandeVisiteMusee.demandeVisite.label" default="Date de fin" /></th>
                        </g:if>

					</tr>
				</thead>
				<tbody>
				<g:each in="${demandeVisiteMuseeInstanceList}" status="i" var="demandeVisiteMuseeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${demandeVisiteMuseeInstance.id}">${fieldValue(bean: demandeVisiteMuseeInstance.musee, field: "nom")}</g:link></td>
                        <td>${fieldValue(bean: demandeVisiteMuseeInstance.demandeVisite, field: "code")}</td>
                        <td>${fieldValue(bean: demandeVisiteMuseeInstance.demandeVisite, field: "nbPersonnes")}</td>
                        <g:if test="${demandeVisiteMuseeInstance.demandeVisite.statut == "Refusee"}">
                            <td><g:message message="Aucun	guide	disponible	sur	cette	période" /></td>
                        </g:if>
                        <g:else>
                            <td>${fieldValue(bean: demandeVisiteMuseeInstance.demandeVisite, field: "statut")}</td>
                        </g:else>
                        <td><g:formatDate format="dd-MM-yyyy H:mm:s">${fieldValue(bean: demandeVisiteMuseeInstance, field: "dateDemande")}</g:formatDate></td>
                        <g:if test="${demandeVisiteMuseeInstance.demandeVisite.statut == "Confirme"}">
                            <td><g:formatDate format="dd-MM-yyyy">${fieldValue(bean: demandeVisiteMuseeInstance.demandeVisite, field: "dateDebutPeriode")}</g:formatDate></td>
                            <td><g:formatDate format="dd-MM-yyyy">${fieldValue(bean: demandeVisiteMuseeInstance.demandeVisite, field: "dateFinPeriode")}</g:formatDate></td>
                        </g:if>
					</tr>
				</g:each>
				</tbody>
			</table>
            </g:if>
		</div>
	</body>
</html>
