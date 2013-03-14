package com.globallogic.sonar

import com.globallogic.sonar.exception.UserExistsException

class UserController {
	
	/** The injection of {@link UserService} */
	def userService

    def create() { }
	
	/** Creates a new end user */
	def save() {
		flash.message = "User created"
		try {
			userService.create(new User(params))
		} catch (UserExistsException e) {
			flash.error = "There was an error trying to create the user."
			flash.description = e.getMessage()
		}
		redirect action: 'create' 
	}
}
