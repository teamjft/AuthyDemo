package authydemo

class TwilioController {

    static allowedMethods = [sendMessage: 'POST']

    def springSecurityService
    def twilioService

    def index() {}

    def createMessage() {

    }

    def sendMessage() {
        if (!params.message) {
            flash.error = "Message cannot be blank."
            redirect action: 'index'
            return
        }

        String to = springSecurityService.currentUser.fullPhoneNumber

        twilioService.sendMessage(to, params.message)
        flash.message = "Message send successfully to ${to}"
        redirect action: 'index'
    }
}
