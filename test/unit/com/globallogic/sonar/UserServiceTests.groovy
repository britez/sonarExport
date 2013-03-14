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
		def mock = mockFor(User)
		mock.demand.getUsername {-> return "username"} 
		def result = userService.create((User)mock.createMock())
		assertNotNull result.id
    }
}
