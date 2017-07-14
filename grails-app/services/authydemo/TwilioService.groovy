package authydemo

import com.twilio.Twilio
import com.twilio.base.ResourceSet
import com.twilio.rest.api.v2010.account.Message
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.rest.api.v2010.account.MessageReader
import com.twilio.type.PhoneNumber
import grails.transaction.Transactional

@Transactional
class TwilioService {

    def grailsApplication

    String sendMessage(String toPhoneNumber, String message, String url) {
        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken)

        MessageCreator messageCreator = Message.creator(new PhoneNumber(toPhoneNumber),
                new PhoneNumber(grailsApplication.config.authy.fromPhoneNumber), message)

        if (url) {
            messageCreator.setMediaUrl(new URI(url))
        }

        Message msg = messageCreator.create()
        println msg.sid
        println msg.status
        return msg.sid
    }

    def messages() {
        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken)
        MessageReader messageReader = Message.reader()
//        messageReader.setTo(new PhoneNumber(""))
//        messageReader.setFrom(new PhoneNumber(""))
        ResourceSet<Message> messages = messageReader.read()
        return messages
    }

    def message(String sid) {
        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken)
        Message message = Message.fetcher(sid).fetch()
        return message
    }

    def updateMessageBody(String sid) {
        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken)
        Message message = Message.updater(sid, "").update();
        return message
    }

    def deleteMessage(String sid) {
        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken)
        Message.deleter(sid).delete()
    }
}
