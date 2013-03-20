package com.globallogic.sonar


class ExportController {
	
	def googleDriveService

    def list() {
		def list = this.googleDriveService.listSpreadSheets()
		[list: list]
	}
}
