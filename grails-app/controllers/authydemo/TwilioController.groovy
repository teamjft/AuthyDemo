package authydemo

import com.twilio.base.ResourceSet
import com.twilio.rest.api.v2010.account.Message

class TwilioController {

    static allowedMethods = [sendMessage: 'POST', updateMessageBody: 'POST', deleteMessage: 'POST']

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

        twilioService.sendMessage(to, params.message, params.url)
        flash.message = "Message send successfully to ${to}"
        redirect action: 'index'
    }

    def messages() {
        ResourceSet<Message> messages = twilioService.messages()
        [messages: messages]
    }

    def messageDetails() {
        if (!params.sid) {
            flash.error = "Message sid cannot be blank."
            redirect action: 'index'
            return
        }

        Message message = twilioService.message(params.sid)
        [message: message]
    }

    def updateMessageBody() {
        if (!params.sid) {
            flash.error = "Message sid cannot be blank."
            redirect action: 'index'
            return
        }

        twilioService.updateMessageBody(params.sid)
        flash.message = "Message (${params.sid}) updated successfully."
        redirect action: 'messages'
    }

    def deleteMessage() {
        if (!params.sid) {
            flash.error = "Message sid cannot be blank."
            redirect action: 'index'
            return
        }

        twilioService.deleteMessage(params.sid)
        flash.message = "Message (${params.sid}) deleted successfully."
        redirect action: 'messages'
    }
}
