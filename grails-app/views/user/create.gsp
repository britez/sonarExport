<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Sonar exporter</title>
	</head>
	<body>
		<div id="page-body" role="main">
		
			<g:if test="${flash.success}">
				<div class="alert alert-success">${flash.message }</div>
			</g:if>
			<g:if test="${flash.error}">
				<div class="alert alert-error">
					<p>${flash.error}</p>
					<p>${flash.description}</p>
				</div>
			</g:if>
			
			<h2>Create user</h2>
			<g:form action="save">
				<input type="text" name="username" placeholder="Username">
				<input type="password" name="password" placeholder="Password">
				<input type="password" name="passwordConfim" placeholder="Confirm password">
				<input type="text" name="googleAccount" placeholder="Google account">
				<input type="password" name="googlePassword" placeholder="Google password">
				<input type="submit" value="Create">
			</g:form>
		</div>
	</body>
</html>
