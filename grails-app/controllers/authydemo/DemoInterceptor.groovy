package authydemo


class DemoInterceptor {

    def springSecurityService

    DemoInterceptor() {
        matchAll()
    }

    boolean before() {
        if (!(params.controller in ["registration", "stomp"]) && springSecurityService.isLoggedIn() && !session.verified) {
            redirect controller: 'registration', action: 'verify'
        }

        true
    }

    boolean after() { true }

    void afterView() {
        // no-op
    }
}
