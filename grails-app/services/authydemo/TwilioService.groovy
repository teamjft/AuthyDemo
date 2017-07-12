package authydemo

import com.twilio.Twilio
import com.twilio.http.TwilioRestClient
import com.twilio.rest.api.v2010.account.Message
import com.twilio.rest.api.v2010.account.MessageCreator
import com.twilio.type.PhoneNumber
import grails.transaction.Transactional

@Transactional
class TwilioService {

    def grailsApplication

    def sendMessage(String toPhoneNumber, String message) {
        TwilioRestClient client = new TwilioRestClient.Builder(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken).build()
        Message msg = new MessageCreator(new PhoneNumber(toPhoneNumber), new PhoneNumber(grailsApplication.config.authy.fromPhoneNumber), message).create(client)

        System.out.println(msg.getSid());
        System.out.println(msg.getStatus());
    }

    // Another way to send text message
    def sendMessage1(String toPhoneNumber, String message) {
        Twilio.init(grailsApplication.config.authy.accountSID, grailsApplication.config.authy.authToken);
        Message msg = Message.creator(new PhoneNumber(toPhoneNumber), new PhoneNumber(grailsApplication.config.authy.fromPhoneNumber), message).create();

        System.out.println(msg.getSid());
        System.out.println(msg.getStatus());
    }
}
