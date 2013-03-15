package com.globallogic.sonar

import com.google.gdata.client.spreadsheet.*
import com.google.gdata.data.spreadsheet.*
import com.google.gdata.util.*

/**
 * This service allows to manage {@link User}
 *
 * @author maximiliano.britez
 */
class GoogleDriveService {
	
	/** The service version to use */
	String SERVICE_VERSION = "MySpreadsheetIntegration-v1"
	
	/** The URL for spread sheets*/
	String SPREADSHEET_FEED_URL = "https://spreadsheets.google.com/feeds/spreadsheets/private/full"
	
	/** The spring security service injection */
	def springSecurityService

	/**
	 * Authenticates into the google 
	 * drive service
	 */
	def authenticate(){
		final User user = (User)springSecurityService.getPrincipal()
		SpreadsheetService service = new SpreadsheetService(this.SERVICE_VERSION);
		service.setUserCredentials(user.googleAccount, user.googlePassword);
	}
	
	/**
	 * @return a list of spreadsheets
	 */
	def listSpreadSheets(){
		URL url = new URL(this.SPREADSHEET_FEED_URL);
		SpreadsheetFeed feed = service.getFeed(url, SpreadsheetFeed.class);
		List<SpreadsheetEntry> spreadsheets = feed.getEntries();
		spreadsheets.toString()
	}
}
