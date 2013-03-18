<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Sonar exporter</title>
	</head>
	<body>
		<div id="page-body" role="main">
			Seleccione el documento donde desea que sean exportados los valores:
			<table>
				<thead></thead>
			</table>
			<g:each in="${list}" var="${it}">
				Nombre: ${it.key} - URL: ${it.value}
			</g:each>
		</div>
	</body>
</html>
