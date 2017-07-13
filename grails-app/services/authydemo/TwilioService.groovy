package authydemo

import com.twilio.Twilio
import com.twilio.rest.api.v2010.account.Message
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber
import grails.transaction.Transactional

@Transactional
class TwilioService {

    def grailsApplication

    String sendMessage(String toPhoneNumber, String message, String url) {
        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken);

        MessageCreator messageCreator = Message.creator(new PhoneNumber(toPhoneNumber),
                new PhoneNumber(grailsApplication.config.authy.fromPhoneNumber), message)

        if (url) {
            messageCreator.setMediaUrl(new URI(url))
        }

        Message msg = messageCreator.create();
        println msg.sid
        println msg.status
        return msg.sid
    }
}
