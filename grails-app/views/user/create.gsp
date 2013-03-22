<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Sonar exporter</title>
	</head>
	<body>
		<div id="page-body" role="main">
		
			<g:if test="${flash.created}">
				<div class="alert alert-success">${flash.created}</div>
			</g:if>
			<g:if test="${flash.error}">
				<div class="alert alert-error">
					<p>${flash.error}</p>
					<p>${flash.description}</p>
				</div>
			</g:if>
			
			<h3>Nuevo usuario</h3>
			<p>Para poder utilizar la aplicación, deberá crear su cuenta. Además necesitamos los datos de su 
			cuenta gmail para poder acceder a sus documentos</p>
			<div class="clearfix center">
				<g:form action="save">
				<div class="column pull-left">
					<h5>Credenciales principales</h5>
					<input type="text" name="username" placeholder="Username">
					<input type="password" name="password" placeholder="Password">
					<input type="password" name="passwordConfirm" placeholder="Confirm password">
				</div>
				<div class="column pull-left">
					<h5>Credenciales Google</h5>
					<input type="text" name="usernameGoogle" placeholder="Username">
					<input type="password" name="passwordGoogle" placeholder="Password">
				</div>
					<div class="column pull-left">
					<h5>Credenciales Sonar</h5>
					<input type="text" name="usernameSonar" placeholder="Username">
					<input type="password" name="passwordSonar" placeholder="Password">
					<input type="text" name="url" placeholder="Url">
					<input type="text" name="projectKey" placeholder="Id del proyecto">
				</div>
				<input type="submit" value="Crear usuario" class="btn btn-primary submit"/>
				</g:form>
			</div>
		</div>
	</body>
</html>
