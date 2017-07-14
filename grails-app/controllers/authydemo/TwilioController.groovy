package authydemo

import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.CallCreator
import com.twilio.twiml.*
import com.twilio.type.PhoneNumber

class TwilioController {

    static allowedMethods = [sendMessage: 'POST', updateMessageBody: 'POST', deleteMessage: 'POST', clickToCallMakeCall: 'POST']

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
        println "Incomming voice = ${params}"

        Say say = new Say.Builder("Thanks for the call. Have a nice day!").voice(Say.Voice.ALICE).build()
        VoiceResponse.Builder builder = new VoiceResponse.Builder().say(say)

        Play play = new Play.Builder("https://demo.twilio.com/docs/classic.mp3").build()
        builder.play(play);

        VoiceResponse twiml = builder.build()
        render contentType: "text/xml", text: twiml.toXml()
    }

    def messageCallback() {
        println "Incomming message = ${params}"

        Message sms = new Message.Builder().body(new Body("Thanks for the message!")).build();
        MessagingResponse twiml = new MessagingResponse.Builder().message(sms).build();
        render contentType: "application/xml", text: twiml.toXml()
    }

    def clickToCall() {

    }

    def clickToCallMakeCall() {
        if (params.firstPhoneNumber && params.secondPhoneNumber) {
            Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken)

            CallCreator callCreator = new CallCreator(new PhoneNumber(params.firstPhoneNumber),
                    new PhoneNumber(grailsApplication.config.authy.fromPhoneNumber),
                    new URI("https://d3ec84ca.ngrok.io/twilio/clickToCallFetchTwiML?phoneNumber=${params.secondPhoneNumber}"));

            callCreator.create();
        } else {
            flash.error = "Please provide both numbers."
        }

        redirect action: 'clickToCall'
    }

    def clickToCallFetchTwiML() {
        Number number = new Number.Builder(params.phoneNumber).build();
        Say say = new Say.Builder("Congratulations you have selected for an amazing prize. Please hold the line, we are connecting you to our customer representative. Thanks!").build()
        render contentType: "application/xml", text: new VoiceResponse.Builder().say(say).dial(new Dial.Builder().number(number).build()).build().toXml()
    }
}
