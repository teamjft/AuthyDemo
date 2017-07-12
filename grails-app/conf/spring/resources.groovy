import authydemo.LoginEventListener
import authydemo.SecUserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    secUserPasswordEncoderListener(SecUserPasswordEncoderListener, ref('hibernateDatastore'))
    loginEventListener(LoginEventListener)
}
