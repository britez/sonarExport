package com.globallogic.sonar

import com.globallogic.sonar.exception.InvalidFormatException

/**
 * This class is the orchester for the requests regarding
 * exporting the values from sonar
 * 
 * @author Maximiliano Britez
 */
class ExportController {
	
	/** The google service injection */
	def googleDriveService
	
	/** The sonar service injection */
	def sonarService

	/** @return a view with the list of available documents */
    def listDocs() {
		[list: this.googleDriveService.listSpreadSheets()]
	}
	
	/** @return a view with the list of available projects */
	def listProjects(){
		[list: sonarService.listProjects(params.id), metric:sonarService.getMetric(params.id)]
	}
	
	/** @return a view with the list of available metrics */
	def listMetrics(){
		[list: sonarService.listMetrics()]
	}
	
	/** @return a view with the metric result */
	def extractMetric() {
		[measure: sonarService.extractMetrics(params.id, params.metricKey),
		 project: sonarService.getProject(params.id, params.metricKey), 
		 metric: sonarService.getMetric(params.metricKey), 
		 docs: googleDriveService.listSpreadSheets()]
	}

	/**
	 * Export the metric result into a spreadsheet selected
	 * from google drive
	 */
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
