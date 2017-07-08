package authydemo.co

import grails.validation.Validateable

class RegistrationForm implements Validateable {

    String name
    String email
    String password
    String countryCode
    String phoneNumber

    static constraints = {
        name size: 5..25, blank: false
        email email: true, blank: false
        password size: 5..15, blank: false
        countryCode blank: false
        phoneNumber blank: false
    }
}
