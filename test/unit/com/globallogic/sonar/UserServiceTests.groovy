package com.globallogic.sonar

import grails.test.mixin.*

import org.junit.*

/**
 * Test for {@link UserService}
 */
@TestFor(UserService)
class UserServiceTests {
	
	/** The service to test */
	def userService
	
	/**
	 * Initializes the test
	 */
	@Before
	void setUp(){
		userService = new UserService();
	}

	/**
	 * Ensures that a end user is created
	 */
	@Test
    void testCreate() {
		def testInstances = [ new User(username: "maxi")]
		mockDomain(User, testInstances)
		def result = userService.create(new User(username: "maxi"))
		assertNotNull result.id
    }
}
