package com.globallogic.sonar

import org.springframework.aop.aspectj.RuntimeTestWalker.ThisInstanceOfResidueTestVisitor;

import com.globallogic.sonar.exception.GoogleConnectionException
import com.google.gdata.client.GoogleService.InvalidCredentialsException
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.client.spreadsheet.SpreadsheetQuery
import com.google.gdata.data.spreadsheet.SpreadsheetEntry
import com.google.gdata.data.spreadsheet.SpreadsheetFeed
import com.google.gdata.data.spreadsheet.ListEntry
import com.google.gdata.data.spreadsheet.WorksheetEntry
import com.google.gdata.data.spreadsheet.WorksheetFeed
import com.google.gdata.client.spreadsheet.FeedURLFactory


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
	
	/** The google spreadsheet service*/
	def googleSpreadsheetService
	
	/** The sonar service injection */
	def sonarService
	
	/**
	 * Authenticates into the google 
	 * drive service
	 */
	def authenticate(def service){
		GoogleCredentials credentials = User.get(springSecurityService.getPrincipal().id).googleCredentials
		try {
			service.setUserCredentials(credentials.username, credentials.password);
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
		}
		this.authenticate(this.googleSpreadsheetService)
		return this.googleSpreadsheetService
	}
	
	/**
	 * @return a list of spreadsheets
	 */
	def listSpreadSheets(){
		def result = [:]
		for (SpreadsheetEntry sheet : this.getAll()) {
			result.put(sheet.getTitle().getPlainText(), sheet.getEditLink()?.getHref())
		}
		return result
	}
	
	private def getAll(){
		URL url = new URL(this.SPREADSHEET_FEED_URL);
		SpreadsheetFeed feed = this.getSpreadsheetService().getFeed(url, SpreadsheetFeed.class);
		feed.getEntries();
	}
	
	def export(String docKey, def metric){
		
		FeedURLFactory factory = FeedURLFactory.getDefault()
		
		def service = this.getSpreadsheetService()
		
		SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(factory.getSpreadsheetsFeedUrl())
		spreadsheetQuery.setTitleQuery(docKey)
		spreadsheetQuery.setTitleExact(true)
		
		def spreadsheetFeed = service.query(spreadsheetQuery, SpreadsheetFeed.class)
		def list = spreadsheetFeed.getEntries()
		
		SpreadsheetEntry sheet = list.get(0)
		
		WorksheetFeed worksheetFeed = service.getFeed(sheet.getWorksheetFeedUrl(), WorksheetFeed.class);
		List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
		WorksheetEntry worksheet = worksheets.get(0);
		def url = worksheet.getListFeedUrl();
		
		ListEntry row = new ListEntry()
		row.getCustomElements().setValueLocal("A", metric.getMetricKey())
		row.getCustomElements().setValueLocal("B", metric.getFormattedValue())
//		row = this.getSpreadsheetService().insert(url, row)
		row.update()
	}
}