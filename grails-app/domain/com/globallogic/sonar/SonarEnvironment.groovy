package com.globallogic.sonar

class SonarEnvironment {
	
	String sonnarServerUrl
	String user
	String password

    static constraints = {
    }
	
	static belongsTo = [user:User]
}
