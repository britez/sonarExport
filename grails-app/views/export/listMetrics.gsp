<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main"/>
		<title>Sonar exporter</title>
	</head>
	<body>
		<div id="page-body" role="main">
			<h2>Exportar métricas</h2>
			<ul class="breadcrumb">
  				<li class="active">Metricas</li>
			</ul>
			<p>Seleccione la métrica que desea exportar:</p>
			<table class="table table-bordered table-hover">
				<thead>
					<th>Nombre</th>
					<th>Descripción</th>
				</thead>
				<tbody>
					<g:each in="${list}" var="${it}">
					<tr>
						<td><g:link action="listProjects" id="${it.getKey()}">${it.getName()}</g:link></td>
						<td>${it.getDescription()}</td>
					</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
