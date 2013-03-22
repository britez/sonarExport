<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Sonar exporter</title>
	</head>
	<body>
		<div id="page-body" role="main">
		
			<g:if test="${flash.modified}">
				<div class="alert alert-success">${flash.modified}</div>
			</g:if>
			<g:if test="${flash.error}">
				<div class="alert alert-error">
					<p>${flash.error}</p>
					<p>${flash.description}</p>
				</div>
			</g:if>
			
			<h3>Modificar usuario</h3>
			<p>Actualizar los datos del usuario y su conexión con las diferentes APIs</p>
			<div class="clearfix center">
				<g:form action="update">
				<div class="column pull-left">
					<h5>Credenciales principales</h5>
					<input type="text" name="username" placeholder="Username" value="${user.username}" class="disabled">
					<input type="password" name="password" placeholder="Password" value="${user.password}">
					<input type="password" name="passwordConfirm" placeholder="Confirm password" value="${user.password}">
				</div>
				<div class="column pull-left">
					<h5>Credenciales Google</h5>
					<input type="text" name="usernameGoogle" placeholder="Username" value="${user.googleCredentials?.username }">
					<input type="password" name="passwordGoogle" placeholder="Password" value="${user.googleCredentials?.password }">
				</div>
					<div class="column pull-left">
					<h5>Credenciales Sonar</h5>
					<input type="text" name="usernameSonar" placeholder="Username" value="${user.sonarEnvironment?.username }">
					<input type="password" name="passwordSonar" placeholder="Password" value="${user.sonarEnvironment?.password }">
					<input type="text" name="url" placeholder="Url" value="${user.sonarEnvironment?.serverUrl}">
					<input type="text" name="projectKey" placeholder="Id del proyecto" value="${user.sonarEnvironment?.projectKey}">
				</div>
				<input type="submit" value="Modificar" class="btn btn-primary submit"/>
				</g:form>
			</div>
		</div>
	</body>
</html>
