package com.globallogic.sonar

/**
 * This class stores the {@link User}'s data to connect
 * to Sonar server.
 *
 * @author Maximiliano Britez
 */
class SonarEnvironment {
	
	/** The server URL to connect */
	String serverUrl
	
	/** The username to connect */
	String username
	
	/** The password to connect*/
	String password
	
	static constraints = {
		username nullable: true
		password nullable: true
	}

	/** The owner for this credentials */
	static belongsTo = [user:User]
}
