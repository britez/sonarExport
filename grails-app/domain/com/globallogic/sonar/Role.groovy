package com.globallogic.sonar

class Role {
	
	static String END_USER = "END_USER"

	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
