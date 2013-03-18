package com.globallogic.sonar

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

import com.globallogic.sonar.exception.GoogleConnectionException
import com.google.gdata.client.GoogleService.InvalidCredentialsException
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet.SpreadsheetEntry
import com.google.gdata.data.spreadsheet.SpreadsheetFeed


/**
 * This service allows to manage {@link User}
 *
 * @author maximiliano.britez
 */
class GoogleDriveService {
	
	/** The URL for spread sheets*/
	String SPREADSHEET_FEED_URL = "https://spreadsheets.google.com/feeds/spreadsheets/private/full"
	
	/** The name of the spreadsheet service */
	String SPREADSHEET_SERVICE = "TheSpreadsheetService"
	
	/** The spring security service injection */
	def springSecurityService
	
	/** The google spreadsheet service */
	def googleSpreadsheetService
	
	/**
	 * Authenticates into the google 
	 * drive service
	 */
	def authenticate(def service){
		User user = User.get(springSecurityService.getPrincipal().id)
		try {
			service.setUserCredentials(user.googleAccount, user.googlePassword);
		} catch (InvalidCredentialsException e) {
			throw new GoogleConnectionException(e.getMessage()) 
		}
	}
	
	/**
	 * @return the spread sheet service
	 */
	def getSpreadsheetService(){
		if(this.googleSpreadsheetService == null) {
			this.googleSpreadsheetService = new SpreadsheetService(this.SPREADSHEET_SERVICE)
			this.authenticate(this.googleSpreadsheetService)
		}
		return this.googleSpreadsheetService
	}
	
	/**
	 * @return a list of spreadsheets
	 */
	def listSpreadSheets(){
		URL url = new URL(this.SPREADSHEET_FEED_URL);
		SpreadsheetFeed feed = this.getSpreadsheetService().getFeed(url, SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();
		def result = [:]
		for (SpreadsheetEntry sheet : spreadsheets) {
			result.put(sheet.getTitle().toString(), sheet.getEditLink().toString())
		}
		return result
	}
}