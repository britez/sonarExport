package com.globallogic.sonar

import com.globallogic.sonar.exception.UserExistsException
import com.globallogic.sonar.exception.UserNotExistsException


class UserController {
	
	/** The injection of {@link UserService} */
	def userService
	
	/** @return a view with the user authenticated */
	def view() {
		[user: userService.getSessionUser()]
	}
	
	/** @return the create form */
    def create() { }
	
	/** Creates a new end user */
	def save() {
 		try {
			userService.create(this.createUser(params))
			flash.created = "Usuario creado correctamente"
		} catch (UserExistsException e) {
			flash.error = "Ha ocurrido un error creando el usuario."
			flash.description = e.getMessage()
		}
		redirect action: 'create' 
	}
	
	/** Updates the user */
	def update(){
		try {
			userService.update(this.createUser(params))
			flash.created = "Usuario modificado correctamente"
		} catch (UserNotExistsException e) {
			flash.error = "Ha ocurrido un error modificando el usuario."
			flash.description = e.getMessage()
		}
		redirect action: 'view'
	}
	
	/**
	 * @return a User created with the parameters 
	 * of the request 
	 */
	private def createUser(def params){
		User user = new User(params)
		user.sonarEnvironment = this.createSonarEnvironment(params)
		user.googleCredentials = this.createGoogleCredentials(params)
		return user
	}
	
	/**
	 * @return a Sonar environment with the parameters
	 * of the request
	 */
	private def createSonarEnvironment(def params){
		return new SonarEnvironment(username:params.usernameSonar,
									password: params.passwordSonar,
									serverUrl: params.url,
									projectKey: params.projectKey)
	}
	
	/**
	 * @return a Google credentials with the parameters
	 * of the request
	 */
	private def createGoogleCredentials(def params){
		return new GoogleCredentials(username:params.usernameGoogle,
									 password: params.passwordGoogle)
	}
}