import com.globallogic.sonar.GoogleCredentials
import com.globallogic.sonar.Role
import com.globallogic.sonar.SonarEnvironment
import com.globallogic.sonar.User
import com.globallogic.sonar.UserRole

class BootStrap {

    def init = { servletContext ->
		
		def adminRole = new Role(authority:"ROLE_ADMIN").save(failOnError: true)
		def user = new User(username:"maxi", password: "maxi").save(failOnError: true)
		def googleCredentials = new GoogleCredentials(username: "maxi03.15", password: "NarelaYael88*", user: user).save(failOnError: true)
		def sonarEnvirenment = new SonarEnvironment(serverUrl: "http://sonar.analytical-labs.com", projectKey: "org.saiku:saiku-webapp-mondrian4", user: user).save(failOnError: true)
		
		UserRole.create user, adminRole
		
    }
    def destroy = {
    }
}
