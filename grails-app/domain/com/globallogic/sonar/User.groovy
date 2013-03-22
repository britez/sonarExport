package com.globallogic.sonar

/**
 * Clase que determina quien esta interactuando con
 * la applicacion.
 * 
 * @author Maximiliano Britez
 */
class User {

	/** The spring security service injection */
	transient springSecurityService

	/** The user name for the user */
	String username
	
	/** The password for the user */
	String password
	
	/** Account property to enable the user */
	boolean enabled = true
	
	/** Account property to expire the user*/
	boolean accountExpired = false
	
	/** Account property to lock the user */
	boolean accountLocked = false
	
	/** Account property to expire the password */
	boolean passwordExpired = false
	
	/** The credentials to connect to google */
	GoogleCredentials googleCredentials
	
	/** The credentials to connect to sonar */
	SonarEnvironment sonarEnvironment

	static constraints = {
		googleCredentials nullable: true
		sonarEnvironment nullable: true
		username blank: false, unique: true
		password blank: false
	}

	static mapping = {
		password column: '`password`'
	}

	Set<Role> getAuthorities() {
		UserRole.findAllByUser(this).collect { it.role } as Set
	}

	def beforeInsert() {
		encodePassword()
	}

	def beforeUpdate() {
		if (isDirty('password')) {
			encodePassword()
		}
	}

	/** Encode the password */
	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
