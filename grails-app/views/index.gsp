<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Sonar exporter</title>
	</head>
	<body>
		<div id="page-body" role="main">
			<h3>Bienvenido!</h3>
			<p>SonarExporter es una herramienta, junto con la solución de Sonar para proyectos web, 
			le permite exportar las métricas de sus proyectos a un archivo de su cuenta en googlDrive</p>
			
			<div id="actionBox">
				<g:link controller="export" action="listMetrics" class="btn btn-large btn-block" type="button">Exportar métricas</g:link>
			</div>
		</div>
	</body>
</html>
