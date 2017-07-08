package authydemo

class DashboardController {

    def registrationService
    def springSecurityService

    def index() {

    }

    def sendMessage() {
        if (!params.message) {
            flash.error = "Message cannot be blank."
            redirect action: 'index'
            return
        }

        String to = springSecurityService.currentUser.fullPhoneNumber

        registrationService.sendMessage(to, params.message)
        flash.message = "Message send successfully to ${to}"
        redirect action: 'index'
    }
}
