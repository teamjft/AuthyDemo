package authydemo

import grails.compiler.GrailsCompileStatic
import groovy.transform.EqualsAndHashCode
import groovy.transform.ToString

@GrailsCompileStatic
@EqualsAndHashCode(includes = 'username')
@ToString(includes = 'username', includeNames = true, includePackage = false)
class SecUser implements Serializable {

    private static final long serialVersionUID = 1

    String username
    String password
    boolean enabled = true
    boolean accountExpired
    boolean accountLocked
    boolean passwordExpired

    String name
    String countryCode
    String phoneNumber
    Integer authyUserId

    Set<SecRole> getAuthorities() {
        (SecUserSecRole.findAllBySecUser(this) as List<SecUserSecRole>)*.secRole as Set<SecRole>
    }

    static constraints = {
        password blank: false, password: true
        username blank: false, unique: true

        countryCode nullable: true
        phoneNumber nullable: true
        authyUserId nullable: true
    }

    static mapping = {
        password column: '`password`'
    }

    public String getFullPhoneNumber() {
        return countryCode + phoneNumber;
    }
}
