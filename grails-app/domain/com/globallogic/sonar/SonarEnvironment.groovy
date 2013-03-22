package com.globallogic.sonar

class SonarEnvironment {
	
	String serverUrl
	String username
	String password
	String projectKey

	static constraints = {
		username nullable: true
		password nullable: true
	}
	
	static belongsTo = [user:User]
}
