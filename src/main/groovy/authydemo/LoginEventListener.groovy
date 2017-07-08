package authydemo

import grails.util.Holders
import org.springframework.context.ApplicationEvent
import org.springframework.context.ApplicationListener
import org.springframework.security.authentication.event.InteractiveAuthenticationSuccessEvent

class MyLoginEventListener implements ApplicationListener {

    void onApplicationEvent(ApplicationEvent event) {
        //Login event capture for sending the security code
        if (event instanceof InteractiveAuthenticationSuccessEvent) {
            def ctx = Holders.applicationContext
            def springSecurityService = ctx.springSecurityService
            def registrationService = ctx.registrationService
            registrationService.sendVerificationCode(springSecurityService.principal.username)
        }
    }
}
