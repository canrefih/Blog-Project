package user.refihcan.canrefih

import android.content.Intent
import android.os.Bundle
import android.util.Patterns
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.signup.*

class SignUp_Page : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)

        signUpNextButton.setOnClickListener {

            val name = signUpFullname.text.toString()
            val email = signUpEmail.text.toString().trim()
            val password = signUpPassword.text.toString().trim()
            val repassword = signUpPasswordRepeat.text.toString().trim()

            if (name.isEmpty()) {
                signUpFullname.error = "Fullname required."
                signUpFullname.requestFocus()
                return@setOnClickListener
            }

            if (email.isEmpty()) {
                signUpEmail.error = "Email is required."
                signUpEmail.requestFocus()
                return@setOnClickListener
            }

            if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                signUpEmail.error = "Enter valid email."
                signUpEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty() || password.length < 8) {
                signUpPassword.error = "Password length should be between 8 to 15 characters."
                signUpPassword.requestFocus()
                return@setOnClickListener
            }

            if (!password.equals(repassword)) {
                signUpPasswordRepeat.error = "Passwords don't match."
                signUpPasswordRepeat.requestFocus()
                return@setOnClickListener

            }

            //Sending datas with this method
            val intent = Intent(this, SignUp_Page2::class.java)
            intent.putExtra("user_email", email)
            intent.putExtra("user_password", password)

            //Starting another activity
            startActivity(intent)

        }

    }

    fun loginBack(view: View) {
        startActivity(Intent(this, LogIn_Page::class.java))
    }

}