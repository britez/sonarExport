<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Sonar exporter</title>
	</head>
	<body>
		<div id="page-body" role="main">
			<h2>Exportar métricas</h2>
			<p>Seleccione el documento donde desea que sean exportados los valores:</p>
			<table class="table table-bordered table-hover">
				<thead>
					<th>&nbsp;</th>
					<th>Nombre</th>
					<th>URL</th>
				</thead>
				<tbody>
					<g:each in="${list}" var="${it}">
					<tr>
						<td><g:radio></g:radio></td>
						<td>${it.key}</td>
						<td>${it.value}</td>
					</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
