package com.globallogic.sonar

import com.globallogic.sonar.exception.InvalidFormatException


class ExportController {
	
	def googleDriveService
	
	def sonarService

    def listDocs() {
		[list: this.googleDriveService.listSpreadSheets()]
	}
	
	def listProjects(){
		[list: sonarService.listProjects(params.id), metric:sonarService.getMetric(params.id)]
	}
	
	def listMetrics(){
		[list: sonarService.listMetrics()]
	}
	
	def extractMetric() {
		[measure: sonarService.extractMetrics(params.id, params.metricKey),
		 project: sonarService.getProject(params.id, params.metricKey), 
		 metric: sonarService.getMetric(params.metricKey), 
		 docs: googleDriveService.listSpreadSheets()]
	}
	
	def export(){
		
		def measure = sonarService.extractMetrics(params.project, params.metric)
		def project= sonarService.getProject(params.project, params.metric)
		try{
			googleDriveService.export(params.id, measure)
			flash.success = "Los datos han sido exportados con éxito"
		} catch(InvalidFormatException e){
			flash.error = "Error de exportacion"
			flash.description = e.getMessage()
		}
		render view: 'extractMetric', model: [measure:measure,
											 project:project, 
											 metric:sonarService.getMetric(params.metric), 
											 docs:googleDriveService.listSpreadSheets()]
	}
}
