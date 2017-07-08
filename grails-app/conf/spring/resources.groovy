import authydemo.MyLoginEventListener
import authydemo.SecUserPasswordEncoderListener
// Place your Spring DSL code here
beans = {
    secUserPasswordEncoderListener(SecUserPasswordEncoderListener, ref('hibernateDatastore'))
    myLoginEventListener(MyLoginEventListener)
}
