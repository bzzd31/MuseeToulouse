
<%@ page import="museetoulouse.Musee" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'musee.label', default: 'Musee')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
        <style type="text/css" media="screen">
            #page-body {
                margin-left: 10%;
            }
            #page-body-left {
                float: left;
                margin-bottom: 21%;
                width: 30%;
            }
            #page-body-right {
                float: right;
                width: 69%;
                border: 1px solid black;
                margin-bottom: 5%;
            }
        </style>
	</head>
	<body>
		<a href="#list-musee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
        <div id="page-body" role="main">
            <h1>Bienvenue sur l'application de réservation de visite des Musées de Toulouse</h1></br>
            <p>Ce site à pour but de vous aider à visiter les Musées de Toulouse</p></br>
            <div id="page-body-left">
                <g:form controller="musee" action="search" method="post">
                    Entrez un nom de musée :</br>
                    <input type="text" name = "nomMusee" size="15"></br></br>
                        Choississez le code postal :</br>
                    <select name="codePostal" size="1">
                        <option>
                        <option>31000
                        <option>31200
                        <option>31300
                        <option>31400
                        <option>31500
                    </select></br></br>
                        Entrez le nom de la rue du musée :</br>
                    <input type="text" name = "nomRue" size="15"></br></br>
                    <g:actionSubmit action="search" value="Rechercher" />
                </g:form>
            </div>
            <div id="page-body-right" role="main">
                <h1> Musee en favoris</h1>
                <table>
                    <thead>
                    <tr>
                        <g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />

                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${museeFavorisList}" status="i" var="museeInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><h5><g:link controller="musee" action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></h5></td>
                            <td><g:checkBox name='favoris' value="${museeInstance.favoris}" onclick="${remoteFunction(action:'updateFavorisIndex', id:museeInstance.id
                            )}"  />
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
        </div>
		<div id="list-musee" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
			<thead>
					<tr>
					
						<g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
					
						<g:sortableColumn property="horairesOuverture" title="${message(code: 'musee.horairesOuverture.label', default: 'Horaires Ouverture')}" />
					
						<g:sortableColumn property="telephone" title="${message(code: 'musee.telephone.label', default: 'Telephone')}" />
					
						<g:sortableColumn property="accesMetro" title="${message(code: 'musee.accesMetro.label', default: 'Acces Metro')}" />
					
						<g:sortableColumn property="accesBus" title="${message(code: 'musee.accesBus.label', default: 'Acces Bus')}" />
					
						<th><g:message code="musee.gestionnaire.label" default="Gestionnaire" /></th>

                        <th><g:message code="musee.adresse.label" default="Adresse" /></th>

                        <g:sortableColumn property="favoris" title="${message(code: 'musee.favoris.label', default: 'Favoris')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${museeInstanceList}" status="i" var="museeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>

						<td>${fieldValue(bean: museeInstance, field: "horairesOuverture")}</td>

                        <td>${fieldValue(bean: museeInstance, field: "telephone")}</td>
					
						<td>${fieldValue(bean: museeInstance, field: "accesMetro")}</td>
					
						<td>${fieldValue(bean: museeInstance, field: "accesBus")}</td>
					
						<td>${fieldValue(bean: museeInstance, field: "gestionnaire")}</td>

                        <td>${fieldValue(bean: museeInstance, field: "adresse")}</td>

                        <td><g:checkBox name='favoris' value="${museeInstance.favoris}" onclick="${remoteFunction(action:'updateFavorisIndex', id:museeInstance.id
                        )}"  />
                        </td>
                    </tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate max="5" total="${museeInstanceCount ?: 0}" />
			</div>
		</div>
	</body>
</html>
