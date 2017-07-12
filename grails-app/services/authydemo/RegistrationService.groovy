package authydemo

import authydemo.co.RegistrationForm
import com.authy.AuthyApiClient
import com.authy.api.*
import grails.transaction.Transactional

@Transactional
class RegistrationService {

    def grailsApplication

    boolean saveUser(RegistrationForm form) {
        AuthyApiClient authyClient = new AuthyApiClient(grailsApplication.config.authy.apiKey)

        // Create user remotely
        User authyUser = authyClient.getUsers().createUser(form.email, form.phoneNumber, form.countryCode);

        if (authyUser.isOk()) {
            Integer authyUserId = authyUser.getId();

            // Create user locally
            SecRole role = SecRole.findByAuthority("ROLE_USER")
            SecUser user = new SecUser(name: form.name,
                    username: form.email,
                    password: form.password,
                    countryCode: form.countryCode,
                    phoneNumber: form.phoneNumber,
                    authyUserId: authyUserId).save()
            new SecUserSecRole(secUser: user, secRole: role).save()

            return Boolean.TRUE
        } else {
            log.error(authyUser.getStatus())
            log.error(authyUser.getError())
            return Boolean.FALSE
        }
    }

    Boolean verifyCode(String userName, String code) {
        SecUser user = SecUser.findByUsername(userName);

        AuthyApiClient authyClient = new AuthyApiClient(grailsApplication.config.authy.apiKey)
        Token token = authyClient.getTokens().verify(user.authyUserId, code);

        if (token.isOk()) {
            // Send SMS confirmation
            // sendMessage(user.getFullPhoneNumber(), "Login successful!");

            return Boolean.TRUE
        } else {
            return Boolean.FALSE
        }
    }

    def sendVerificationCode(String userName) {
        SecUser user = SecUser.findByUsername(userName);
        AuthyApiClient authyClient = new AuthyApiClient(grailsApplication.config.authy.apiKey)

        // Request SMS authentication
        authyClient.getUsers().requestSms(user.authyUserId);
    }

    def sendVerificationCodeByCall(String userName) {
        SecUser user = SecUser.findByUsername(userName);
        AuthyApiClient authyClient = new AuthyApiClient(grailsApplication.config.authy.apiKey)

        // Request Call authentication
        authyClient.getUsers().requestCall(user.authyUserId);
    }

    String requestOneTimeAuthentication(String userName) {
        SecUser user = SecUser.findByUsername(userName);
        AuthyApiClient authyClient = new AuthyApiClient(grailsApplication.config.authy.apiKey)

        ApprovalRequestParams approvalRequestParams = new ApprovalRequestParams.Builder(user.authyUserId, "Authorize OneTouch Test")
                .addDetail("username", user.username)
                .addLogo(ApprovalRequestParams.Resolution.Default, "https://media.glassdoor.com/sql/780298/jellyfish-technologies-squarelogo-1427198372245.png")
                .build();

        OneTouchResponse response = authyClient.getOneTouch().sendApprovalRequest(approvalRequestParams);
        // If the request was successfuly created.
        String uuid = response.getApprovalRequest().getUUID();
        System.out.println(uuid);

        return uuid;
    }

    String checkOTStatus(String uuid) {
        AuthyApiClient authyClient = new AuthyApiClient(grailsApplication.config.authy.apiKey)
        OneTouch oneTouch = authyClient.getOneTouch()
        OneTouchResponse response = oneTouch.getApprovalRequestStatus(uuid);
        OneTouchResponse.ApprovalRequest approvalRequest = response.getApprovalRequest();
        println approvalRequest.getStatus()
        return approvalRequest.getStatus()
    }
}
