package com.globallogic.sonar

/**
 * Clase de seguridad, para obtener el acceso a la aplicación.
 * Además aloja las credenciales de google drive.
 * 
 * @author maximiliano.britez
 */
class User {

	transient springSecurityService

	String username
	String password
	boolean enabled = true
	boolean accountExpired = false
	boolean accountLocked = false
	boolean passwordExpired = false
	
	GoogleCredentials googleCredentials
	SonarEnvironment sonarEnvironment

	static constraints = {
		googleCredentials blank: true
		sonarEnvironment blank: true
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

	/** Se encodea el password antes de persistir */
	protected void encodePassword() {
		password = springSecurityService.encodePassword(password)
	}
}
