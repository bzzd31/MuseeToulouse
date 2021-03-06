<%@ page import="museetoulouse.Musee" %>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/html">
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'demandeVisiteMusee.label', default: 'DemandeVisiteMusee')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
        <script src="//code.jquery.com/jquery-1.10.2.js"></script>
        <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
        <script src="DemandeVisiteMusee.js"></script>
        <style>
        #create-demandeVisiteMusee {
            margin-left: auto;
            margin-right: auto;
            width: 25em
        }
        .buttons{
            margin-top: 10%;
        }
        #formulaire{
            margin-top: 10%;
        }
        #formulaireItem{
            margin-top: 2%;
        }
        </style>
	</head>
	<body>
		<a href="#create-demandeVisiteMusee" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="index"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-demandeVisiteMusee" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${demandeVisiteMuseeInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${demandeVisiteMuseeInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form url="[action:'save']" onsubmit="return test()" id="formulaire">
                <g:select name="museeInstance" from="${museeInstance}" value="${museeSelect}" optionKey="id" required="" class="many-to-one"/>
                <div id="formulaireItem">
                <p>Date début: <input type="text" id="datepickerDebut" name="dateDebut" onchange="testDateDebut()"></p>
                </div>
                <div id="formulaireItem">
                <p>Date fin: <input type="text" id="datepickerFin" name="dateFin" onchange="testDateFin()"></p>
                </div>
                <div id="formulaireItem">
                <g:select name="nbPersonnes" from="${1..6}"
                          noSelection="['':'Choisissez le nombre de personne']" onchange="testNbPersonne()"/>
                </div>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
