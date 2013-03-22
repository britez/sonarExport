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
  				<li><g:link action="listProjects" id="${metricKey}">${project.getName()}</g:link> <span class="divider">/</span></li>
  				<li class="active">Resultado</li>
			</ul>
			<table class="table table-bordered table-hover">
				<thead>
					<th>Nombre</th>
					<th>Valor</th>
				</thead>
				<tbody>
					<tr>
						<td>${metric.getMetricKey()}</td>
						<td>${metric.getValue()}</td>
					</tr>
				</tbody>
			</table>
			<p>Seleccione el documento donde será exportado:</p>
			<table class="table table-bordered table-hover">
				<thead>
					<th>Nombre</th>
					<th>URL</th>
				</thead>
				<tbody>
					<g:each in="${docs}" var="${it}">
					<tr>
						<td>
							<g:link action="export" id="${it.key}" params="${[project:project.getKey(), metric: metricKey]}">
							${it.key}
							</g:link>
						</td>
						<td>${it.value}</td>
					</tr>
					</g:each>
				</tbody>
			</table>
		</div>
	</body>
</html>
