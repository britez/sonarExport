package com.globallogic.sonar

/**
 * Clase de seguridad, para obtener el acceso a la aplicaci�n.
 * Adem�s aloja las credenciales de google drive.
 * 
 * @author maximiliano.britez
 */
class User {

	transient springSecurityService

	String username
	String password
	boolean enabled
	boolean accountExpired
	boolean accountLocked
	boolean passwordExpired

	static constraints = {
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
