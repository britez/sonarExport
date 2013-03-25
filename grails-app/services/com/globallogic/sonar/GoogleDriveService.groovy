package com.globallogic.sonar

import org.sonar.wsclient.services.Measure

import com.globallogic.sonar.exception.GoogleConnectionException
import com.globallogic.sonar.exception.InvalidFormatException
import com.google.gdata.client.GoogleService.InvalidCredentialsException
import com.google.gdata.client.spreadsheet.FeedURLFactory
import com.google.gdata.client.spreadsheet.SpreadsheetQuery
import com.google.gdata.client.spreadsheet.SpreadsheetService
import com.google.gdata.data.spreadsheet.ListEntry
import com.google.gdata.data.spreadsheet.SpreadsheetEntry
import com.google.gdata.data.spreadsheet.SpreadsheetFeed
import com.google.gdata.data.spreadsheet.WorksheetEntry
import com.google.gdata.data.spreadsheet.WorksheetFeed
import com.google.gdata.util.InvalidEntryException


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
	
	/** The URL for spread sheet*/
	String SPREADHSEET_METRIC= "metricSpreadSheet"
	
	/** The header of the metric */
	String METRIC_HEADER = "METRICA"
	
	/** The header of the value */
	String VALUE_HEADER = "VALOR"
	
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
	
	/** @return all the spreadsheets of the account */
	private def getAll(){
		URL url = new URL(this.SPREADSHEET_FEED_URL);
		SpreadsheetFeed feed = this.getSpreadsheetService().getFeed(url, SpreadsheetFeed.class);
		feed.getEntries();
	}
	
	/**
	 * Insert into the Spreadsheet a Metric values
	 * @param docKey - the key of the spreadsheet
	 * @param metric - the values to set
	 * @throws InvalidFormatException - if the export fails
	 */
	def export(String docKey, Measure metric) throws InvalidFormatException{
		def entry = this.getSpreadsheet(docKey)
		def feedUrl = this.getFeedUrl(entry)
		this.exportMetric(metric, entry, feedUrl) 
	}
	
	/**
	 * @param spreadSheetKey - the key of the spreadsheet to retrieve
	 * @return a spreadsheet
	 */
	def getSpreadsheet(def spreadSheetKey){
		FeedURLFactory factory = FeedURLFactory.getDefault()
		def service = this.getSpreadsheetService()
		SpreadsheetQuery spreadsheetQuery = new SpreadsheetQuery(factory.getSpreadsheetsFeedUrl());
		spreadsheetQuery.setTitleQuery(spreadSheetKey);
		spreadsheetQuery.setTitleExact(true);
		SpreadsheetFeed spreadsheetFeed = service.query(spreadsheetQuery, SpreadsheetFeed.class);
		
		List<SpreadsheetEntry> spreadsheets = spreadsheetFeed.getEntries();
		
		return spreadsheets.get(0);
	}

	/**
	 * @return the feed url to edit the spreadsheet
	 */
	def getFeedUrl(def entry){
		def service = this.getSpreadsheetService()
		WorksheetFeed worksheetFeed = service.getFeed(entry.getWorksheetFeedUrl(), WorksheetFeed.class);
		List<WorksheetEntry> worksheets = worksheetFeed.getEntries();
		WorksheetEntry worksheet = worksheets.get(0);
		return worksheet.getListFeedUrl();
	}
	
	/**
	 * Updates the entry with the data
	 * @param metric - to be setted
	 * @param entry - to be updated
	 * @param feedUrl - of the entry to update
	 */
	def exportMetric(metric, entry, feedUrl){
		def service = this.getSpreadsheetService()
		ListEntry row = new ListEntry();
		row.getCustomElements().setValueLocal(METRIC_HEADER,metric.getMetricKey());
		row.getCustomElements().setValueLocal(VALUE_HEADER,metric.getFormattedValue());
		try{
			row = service.insert(feedUrl, row);
		} catch (InvalidEntryException e){
			throw new InvalidFormatException("Imposible de exporta. Asegurese de que su documento, tiene las columnas METRICA y VALOR")
		}
	}
}