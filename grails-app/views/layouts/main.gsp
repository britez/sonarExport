<!DOCTYPE html>
<!--[if lt IE 7 ]> <html lang="en" class="no-js ie6"> <![endif]-->
<!--[if IE 7 ]>    <html lang="en" class="no-js ie7"> <![endif]-->
<!--[if IE 8 ]>    <html lang="en" class="no-js ie8"> <![endif]-->
<!--[if IE 9 ]>    <html lang="en" class="no-js ie9"> <![endif]-->
<!--[if (gt IE 9)|!(IE)]><!--> <html lang="en" class="no-js"><!--<![endif]-->
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.css')}" type="text/css">
		<g:layoutHead/>
		<r:layoutResources />
	</head>
	<body>
		<div id="grailsLogo" role="banner" class="clearfix">
			<g:link action="index" class="pull-left"><img src="${resource(dir: 'images', file: 'ah.png')}" alt="Sonar"/></g:link>
			<div class="pull-right">
				<sec:ifNotLoggedIn>
        			<g:form method="POST" url="sonarExporter/j_spring_security_check">
						<input type="text" name="j_username"/>
						<input type="password" name="j_password"/>
						<input type="submit" value="login">
					</g:form>
					<g:link controller="user" action="create">Create User</g:link>
		        </sec:ifNotLoggedIn>
		        <sec:ifLoggedIn>
		            <sec:username/>
		            <g:link controller="logout">Logout</g:link>
		        </sec:ifLoggedIn>
			</div>
		</div>
		<g:layoutBody/>
		<div class="footer" role="contentinfo"></div>
		<div id="spinner" class="spinner" style="display:none;"><g:message code="spinner.alt" default="Loading&hellip;"/></div>
		<g:javascript library="application"/>
		<g:javascript src="${resource(dir:'js', file:'bootstrap.js')}"/>
		<r:layoutResources />
	</body>
</html>
