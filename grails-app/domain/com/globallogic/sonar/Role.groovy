package com.globallogic.sonar

/**
 * This class stores the {@link User}'s role to intectact
 * with the application
 *
 * @author Maximiliano Britez
 */
class Role {
	
	/** The name of the Role */
	String authority

	static mapping = {
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
