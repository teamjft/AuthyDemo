package authydemo

import authydemo.co.RegistrationForm
import grails.converters.JSON
import org.springframework.messaging.simp.SimpMessagingTemplate

class RegistrationController {

    static allowedMethods = [save: 'POST', verifyCode: 'POST', resendVerificationCode: 'POST', resendVerificationCodeViaCall: 'POST']

    def springSecurityService
    def registrationService
    SimpMessagingTemplate brokerMessagingTemplate

    def index() {
        if (springSecurityService.isLoggedIn()) {
            redirect controller: 'dashboard', action: 'index'
        }
    }

    def save(RegistrationForm form) {
        if (!form.validate()) {
            render view: 'index', model: [form: form]
            return
        }

        if (registrationService.saveUser(form)) {
            redirect controller: 'login'
        } else {
            flash.error = "Something went wrong, please try again later!"
            redirect action: 'index'
        }
    }

    def verify() {

    }

    def verifyCode() {
        if (!params.code) {
            flash.error = "Please enter the verification code"
        }

        if (registrationService.verifyCode(springSecurityService.principal.username, params.code)) {
            session.verified = Boolean.TRUE
            redirect controller: 'dashboard', action: 'index'
        } else {
            flash.error = "Incorrect code, please try again!"
            redirect action: 'verify'
        }
    }

    def resendVerificationCode() {
        registrationService.sendVerificationCode(springSecurityService.principal.username)
        redirect action: 'verify'
    }

    def resendVerificationCodeViaCall() {
        registrationService.sendVerificationCodeByCall(springSecurityService.principal.username)
        redirect action: 'verify'
    }

    def oneTouchAuthenticationRequest() {
        String uuid = registrationService.requestOneTimeAuthentication(springSecurityService.principal.username)
        session.uuid = uuid;
        redirect action: 'verify'
    }

    def oneTouchCallback() {
        brokerMessagingTemplate.convertAndSend "/topic/checkOTStatus", "hello from service! ${new Date().getTime()}"
        render contentType: "text/json", text: [status: 'success'] as JSON
    }

    def checkOTStatus() {
        String uuid = session.uuid
        if (uuid) {
            String status = registrationService.checkOTStatus(session.uuid)
            if (status == 'approved') {
                session.verified = Boolean.TRUE
                render contentType: "text/json", text: [status: 'approved', url: g.createLink(controller: 'dashboard')] as JSON
            } else if (status == 'denied') {
                render contentType: "text/json", text: [status: 'denied', message: 'User declined the one touch request.'] as JSON
            } else {
                render contentType: "text/json", text: [status: status] as JSON
            }
        } else {
            render contentType: "text/json", text: [status: 'error'] as JSON
        }
    }
}
