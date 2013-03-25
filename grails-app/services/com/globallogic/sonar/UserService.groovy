package com.globallogic.sonar

import com.globallogic.sonar.exception.UserExistsException
import com.globallogic.sonar.exception.UserNotExistsException

/**
 * This service allows to manage {@link User}
 * 
 * @author maximiliano.britez
 */
class UserService {
	
	/** The spring security service injection */
	def springSecurityService

	/**
	 * Creates a new end user
	 * 
	 * @param user - {@link User} to be created
	 * @return the user created
	 * @throws UserExistsException if the user already exists
	 * 	in the application
	 */
    def create(User user) throws UserExistsException{
		
		if(User.findByUsername(user.username)){
			throw new UserExistsException("El usuario "+user.username+" ya existe")
		}
		user.save(failOnError: true)
		UserRole.create(user, Role.findByAuthority("ROLE_ADMIN"))
		return user
    }
	
	/**
	 * Updates a User
	 *
	 * @param user - {@link User} to be created
	 * @return the user created
	 * @throws UserNonExistsException if the user not exists
	 * 	in the application
	 */
	def update(User user) throws UserNotExistsException {
		def storedUser = User.findByUsername(user.username)
		if(storedUser == null) {
			throw new UserNotExistsException("El Usuario no existe")
		}
		storedUser.password = user.password
		storedUser.googleCredentials = user.googleCredentials
		storedUser.sonarEnvironment = user.sonarEnvironment
		storedUser.save(failOnError: true)
	}
	
	/**
	 * @return the logged in user
	 */
	def getSessionUser() {
		return User.get(springSecurityService.getPrincipal()?.id)
	}
}
