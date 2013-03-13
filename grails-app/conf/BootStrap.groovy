import com.globallogic.sonar.Role
import com.globallogic.sonar.User
import com.globallogic.sonar.UserRole

class BootStrap {

    def init = { servletContext ->
		
		def user = new User(username:"maxi", password: "maxi").save()
		def role = new Role(roleName:"user").save()
		UserRole.create(user, role)
		
    }
    def destroy = {
    }
}
