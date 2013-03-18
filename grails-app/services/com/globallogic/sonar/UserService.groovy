package com.globallogic.sonar

import com.globallogic.sonar.exception.UserExistsException

/**
 * This service allows to manage {@link User}
 * 
 * @author maximiliano.britez
 */
class UserService {

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
			throw new UserExistsException("The username "+user.username+" already exists")
		}
		user.save()
		Role role = new Role(authority: Role.END_USER).save()
		UserRole.create(user, role)
		return user
    }
}
