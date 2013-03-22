package com.globallogic.sonar

/**
 * This class stores the {@link User}'s data to connect
 * to google. 
 * 
 * @author Maximiliano Britez
 */
class GoogleCredentials {
	
	/** The username */
	String username
	
	/** The password */
	String password

	/** The owner for this credentials */
    static belongsTo = [user:User]
}
