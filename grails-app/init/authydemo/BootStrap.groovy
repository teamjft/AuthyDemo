package authydemo

class BootStrap {

    def init = { servletContext ->
        if (!SecRole.findByAuthority("ROLE_USER")) {
            new SecRole(authority: "ROLE_USER").save(flush: true, failOnError: true)
        }
    }

    def destroy = {
    }
}
