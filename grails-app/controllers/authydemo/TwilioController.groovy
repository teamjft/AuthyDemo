package authydemo

import com.twilio.Twilio
import com.twilio.base.ResourceSet
import com.twilio.rest.api.v2010.account.CallCreator
import com.twilio.rest.api.v2010.account.Recording
import com.twilio.twiml.*
import com.twilio.type.PhoneNumber

class TwilioController {

    static allowedMethods = [sendMessage        : 'POST', updateMessageBody: 'POST', deleteMessage: 'POST',
                             clickToCallMakeCall: 'POST', deleteRecording: 'POST']

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

        // Code to play "say" message and "play" recording
        /*Say say = new Say.Builder("Thanks for the call. Have a nice day!").voice(Say.Voice.ALICE).build()
        VoiceResponse.Builder builder = new VoiceResponse.Builder().say(say)
        Play play = new Play.Builder("https://demo.twilio.com/docs/classic.mp3").build()
        builder.play(play);
        VoiceResponse twiml = builder.build()
        render contentType: "text/xml", text: twiml.toXml()*/

        // Code to record voice
        /*Say say = new Say.Builder("Please record your message after the beep. Press star to end your recording.").build();
        String action = g.createLink(controller: 'twilio', action: 'hangupResponse')
        Record record = new Record.Builder().action(action).method(Method.POST).finishOnKey("*").build();
        VoiceResponse voiceResponse = new VoiceResponse.Builder().say(say).record(record).build();
        render contentType: "application/xml", text: voiceResponse.toXml()*/


        // Connect conference
        String message = "You are about to join the Rapid Response conference. Press 1 to join as a listener. Press 2 to join as a speaker. Press 3 to join as the moderator.";

        Say sayMessage = new Say.Builder(message).build();
        Gather gather = new Gather.Builder().action("/twilio/hangupResponse").method(Method.POST).say(sayMessage).build();

        VoiceResponse voiceResponse = new VoiceResponse.Builder().gather(gather).build();
        render contentType: "application/xml", text: voiceResponse.toXml()
    }

    def hangupResponse() {
        /*Say say = new Say.Builder("Your recording has been saved. Good bye.").build();
        VoiceResponse voiceResponse = new VoiceResponse.Builder().say(say).hangup(new Hangup()).build();
        render contentType: "application/xml", text: voiceResponse.toXml()*/


        // Join conference
        Boolean muted = false;
        Boolean moderator = false;
        String digits = params.Digits

        if (digits.equals("1")) {
            muted = true;
        }
        if (digits.equals("3")) {
            moderator = true;
        }

        String defaultMessage = "You have joined the conference.";
        Say sayMessage = new Say.Builder(defaultMessage).build();

        Conference conference = new Conference.Builder("RapidResponseRoom")
                .waitUrl("http://twimlets.com/holdmusic?Bucket=com.twilio.music.ambient")
                .muted(muted)
                .startConferenceOnEnter(moderator)
                .endConferenceOnExit(moderator)
                .build();

        Dial dial = new Dial.Builder().conference(conference).build();

        VoiceResponse voiceResponse = new VoiceResponse.Builder().say(sayMessage).dial(dial).build();
        render contentType: "application/xml", text: voiceResponse.toXml()
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

            String uri = g.createLink(controller: 'twilio', action: 'clickToCallFetchTwiML', absolute: true) + "?phoneNumber=${params.secondPhoneNumber}"
            CallCreator callCreator = new CallCreator(new PhoneNumber(params.firstPhoneNumber),
                    new PhoneNumber(grailsApplication.config.authy.fromPhoneNumber), new URI(uri));

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

    def recordings() {
        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken)
        ResourceSet<Recording> recordings = Recording.reader().read();
        [recordings: recordings]
    }

    def deleteRecording() {
        if (!params.sid) {
            flash.error = "Voice sid cannot be blank."
            redirect action: 'recordings'
            return
        }

        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken)
        Recording.deleter(params.sid).delete()

        flash.message = "Recording (${params.sid}) deleted successfully."
        redirect action: 'recordings'
    }
}
