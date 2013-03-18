package com.globallogic.sonar

class ExportController {
	
	def googleDriveService

    def list() {
		render googleDriveService.listSpreadSheets()
	}
}
