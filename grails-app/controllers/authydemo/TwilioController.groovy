package authydemo

import com.twilio.twiml.Body
import com.twilio.twiml.Message
import com.twilio.twiml.MessagingResponse

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
        [messages: twilioService.messages()]
    }

    def messageDetails() {
        if (!params.sid) {
            flash.error = "Message sid cannot be blank."
            redirect action: 'index'
            return
        }

        [message: twilioService.message(params.sid)]
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

    def voiceCallback() {
        println "Voice = ${params}"

        Message sms = new Message.Builder().body(new Body("Thanks for the call!")).build();
        MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
        render contentType: "application/xml", text: twiml.toXml()
    }

    def messageCallback() {
        println "Incomming message = ${params}"

        Message sms = new Message.Builder().body(new Body("Thanks for the message!")).build();
        MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
        render contentType: "application/xml", text: twiml.toXml()
    }
}
