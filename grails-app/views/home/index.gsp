<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Musees Toulouse & Visites</title>
		<style type="text/css" media="screen">
			#status {
				background-color: #eee;
				border: .2em solid #fff;
				margin: 2em 2em 1em;
				padding: 1em;
				width: 12em;
				float: left;
				-moz-box-shadow: 0px 0px 1.25em #ccc;
				-webkit-box-shadow: 0px 0px 1.25em #ccc;
				box-shadow: 0px 0px 1.25em #ccc;
				-moz-border-radius: 0.6em;
				-webkit-border-radius: 0.6em;
				border-radius: 0.6em;
			}

			.ie6 #status {
				display: inline; /* float double margin fix http://www.positioniseverything.net/explorer/doubled-margin.html */
			}

			#status ul {
				font-size: 0.9em;
				list-style-type: none;
				margin-bottom: 0.6em;
				padding: 0;
			}

			#status li {
				line-height: 1.3;
			}

			#status h1 {
				text-transform: uppercase;
				font-size: 1.1em;
				margin: 0 0 0.3em;
			}

			#page-body {
				margin-left: 10%;
			}
            #page-body-right {
                float: left;
                margin-bottom: 30%;
            }
            #page-body-left {
                margin-top: 100px;
                float: right;
                margin-right: 10%;
                width: 50%;
                border: 1px solid black;
            }
            #page-body-left-favoris {
                float: right;
                margin-right: 10%;
                width: 50%;
                border: 1px solid black;
            }
			h2 {
				margin-top: 1em;
				margin-bottom: 0.3em;
				font-size: 1em;
			}

			p {
				line-height: 1.5;
				margin: 0.25em 0;
			}

			#controller-list ul {
				list-style-position: inside;
			}

			#controller-list li {
				line-height: 1.3;
				list-style-position: inside;
				margin: 0.25em 0;
			}
			@media screen and (max-width: 480px) {
				#status {
					display: none;
				}

				#page-body {
					margin: 0 1em 1em;
				}

				#page-body h1 {
					margin-top: 0;
				}
			}
		</style>
	</head>
	<body>
		<div id="page-body" role="main">
			<h1>Bienvenue sur l'application de réservation de visite des Musées de Toulouse</h1></br>
			<p>Ce site à pour but de vous aider à visiter les Musées de Toulouse</p></br>
            <div id="page-body-right">
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
                <g:actionSubmit action="search" value="Rechercher" /></br>
               <!-- <input type="submit" name="rechercher" size="15">-->
            </g:form>
        </div>
            <div id="page-body-left-favoris" role="main">
                <h2> MUSEE </h2>
                <table>
                    <thead>
                    <tr>
                        <g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
                        <g:sortableColumn property="favoris" title="${message(code: 'musee.telephone.label', default: 'Favoris')}" />

                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${museeList}" status="i" var="museeInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><g:link controller="musee" action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>
                            <td><g:checkBox name='favoris' value="${museeInstance.favoris}" onclick="${remoteFunction(action:'updateFavoris', id:museeInstance.id
                            )}"  />
                            </td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
            </div>
            <div id="page-body-left" role="main">
                <h2> MUSEE EN FAVORIS</h2>
                <table>
                    <thead>
                    <tr>
                        <g:sortableColumn property="nom" title="${message(code: 'musee.nom.label', default: 'Nom')}" />
                        <g:sortableColumn property="telephone" title="${message(code: 'musee.telephone.label', default: 'Telephone')}" />

                    </tr>
                    </thead>
                    <tbody>
                    <g:each in="${museeInstanceList}" status="i" var="museeInstance">
                        <tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
                            <td><g:link controller="musee" action="show" id="${museeInstance.id}">${fieldValue(bean: museeInstance, field: "nom")}</g:link></td>
                            <td style="height: 100%">${fieldValue(bean: museeInstance, field: "telephone")}</td>
                        </tr>
                    </g:each>
                    </tbody>
                </table>
                <div class="pagination">
                    <g:paginate max="5" total="${museeInstanceCount ?: 0}" />
                </div>
            </div>
        </div>
	</body>
</html>
