package com.globallogic.sonar

class GoogleCredentials {
	
	String username
	
	String password

    static belongsTo = [user:User]
}
