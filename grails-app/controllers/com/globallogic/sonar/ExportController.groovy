package com.globallogic.sonar


class ExportController {
	
	def googleDriveService
	
	def sonarService

    def listDocs() {
		[list: this.googleDriveService.listSpreadSheets()]
	}
	
	def listProjects(){
		[list: sonarService.listProjects(params.id), metricKey:params.id]
	}
	
	def listMetrics(){
		[list: sonarService.listMetrics()]
	}
	
	def extractMetric() {
		[metric: sonarService.extractMetrics(params.id, params.metricKey),
		 project: sonarService.getProject(params.id, params.metricKey), 
		 metricKey: params.metricKey, 
		 docs: googleDriveService.listSpreadSheets()]
	}
	
	def export(){
		[doc: googleDriveService.export(params.id, sonarService.extractMetrics(params.project, params.metric))]
	}
}
