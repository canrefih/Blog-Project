package user.refihcan.canrefih

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.signup3.*

class SignUp_Page3 : AppCompatActivity() {

    private var email: String? = null
    private var password: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup3)

        val intent = getIntent()

        if (intent != null) {
            email = intent.getStringExtra("user_email")
            password = intent.getStringExtra("user_password")
        } else if (savedInstanceState != null) {
            email = savedInstanceState.getString("user_email")
            password = savedInstanceState.getString("user_password")
        }

        signUpComplete.setOnClickListener {
            //Button click animation
            val buttonClick = AlphaAnimation(1F, 0.3F)
            it.startAnimation(buttonClick)
            //Button click vibrate function
            val vibrator = getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
            if (vibrator.hasVibrator()) { // Vibrator availability checking
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    vibrator.vibrate(
                        VibrationEffect.createOneShot(
                            100,
                            VibrationEffect.DEFAULT_AMPLITUDE
                        )
                    ) // New vibrate method for API Level 26 or higher
                } else {
                    vibrator.vibrate(100) // Vibrate method for below API Level 26
                }
            }

            if (!switchPrivacy.isChecked) {
                Toast.makeText(
                    this,
                    "You must accept the privacy policy and terms.",
                    Toast.LENGTH_SHORT
                ).show()
                return@setOnClickListener
            }

            //Firebase Authentication to create user
            FirebaseAuth.getInstance()
                .createUserWithEmailAndPassword(
                    email.toString(),
                    password.toString()
                ).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        user?.sendEmailVerification()?.addOnCompleteListener {
                            if (it.isSuccessful) {
                                startActivity(Intent(this, LogIn_Page::class.java))
                                finish()
                                Toast.makeText(
                                    this,
                                    "Account successfully created, please log in.",
                                    Toast.LENGTH_LONG
                                ).show()
                            }
                        }

                    } else {
                        Toast.makeText(this, "You have already a account.", Toast.LENGTH_SHORT)
                            .show()
                        return@addOnCompleteListener
                    }

                }

        }

        backToBack.setOnClickListener {
            super.onBackPressed()
        }

        policyButton.setOnClickListener {
            startActivity(Intent(this, PopUp::class.java))
        }

    }

    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("user_email", email)
        outState.putString("user_password", password)
    }

}