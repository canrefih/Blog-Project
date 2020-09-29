@file:Suppress("NAME_SHADOWING")

package user.refihcan.canrefih

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.signup2.*

class SignUp_Page2 : AppCompatActivity() {

    private var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup2)

        val intent = getIntent()

        if (intent != null) {
            email = intent.getStringExtra("user_email")
            password = intent.getStringExtra("user_password")
        } else if (savedInstanceState != null) {
            email = savedInstanceState.getString("user_email")
            password = savedInstanceState.getString("user_password")
        }

        signUpGetCode.setOnClickListener {

            if (signUpPhoneNumber.text.toString().isEmpty()) {
                signUpPhoneNumber.error = "Please enter valid phone"
                signUpPhoneNumber.requestFocus()
                return@setOnClickListener
            }

            //Sending datas with this method
            val intent = Intent(this, SignUp_Page3::class.java)
            intent.putExtra("user_email", email)
            intent.putExtra("user_password", password)

            //Starting another activity
            startActivity(intent)

        }

        backToBack.setOnClickListener {
            super.onBackPressed()
        }

//        fun callVerifyOtp(){
//            if (!signUpPhoneNumber()){
//
//            }
//        }

    }

    override fun onSaveInstanceState(outState: Bundle) {

        super.onSaveInstanceState(outState)
        outState.putString("user_email", email)
        outState.putString("user_password", password)

    }

}