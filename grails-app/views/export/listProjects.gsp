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
  				<li><g:link action="listMetrics">${metricKey}</g:link> <span class="divider">/</span></li>
  				<li class="active">Proyectos</li>
			</ul>
			<p>Seleccione el proyecto que desea exportar:</p>
			<table class="table table-bordered table-hover">
				<thead>
					<th>Nombre</th>
					<th>Descripcion</th>
				</thead>
				<tbody>
					<g:each in="${list}" var="${it}">
					<tr>
						<td><g:link action="extractMetric" id="${it.getKey()}" params="${[metricKey:metricKey]}">${it.getName()}</g:link></td>
						<td>${it.getDescription()}</td>
					</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
