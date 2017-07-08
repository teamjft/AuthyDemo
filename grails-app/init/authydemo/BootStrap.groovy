package authydemo

class BootStrap {

    def init = { servletContext ->
        if (!SecRole.findByAuthority("ROLE_USER")) {
            SecRole role = new SecRole(authority: "ROLE_USER").save(flush: true, failOnError: true)
            SecUser user = new SecUser(name: "Admin", username: "admin@jft.com", password: "password.jft").save(flush: true, failOnError: true)
            new SecUserSecRole(secUser: user, secRole: role).save(flush: true, failOnError: true)
        }
    }

    def destroy = {
    }
}
