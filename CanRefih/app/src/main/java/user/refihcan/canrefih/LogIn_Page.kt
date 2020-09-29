package user.refihcan.canrefih

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.View
import android.view.WindowManager
import android.view.animation.AlphaAnimation
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*

class LogIn_Page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)

        logInButton.setOnClickListener {
            //Button click vibrate function
            val buttonClick = AlphaAnimation(1F, 0.3F)
            it.startAnimation(buttonClick)
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

            val email = logInEmail.text.toString()
            val password = logInPassword.text.toString()
            val preferences = getPreferences(Context.MODE_PRIVATE)
            val editor = preferences.edit()

            if (rememberMe.isChecked) {
                editor.putString("EMAIL", email)
                editor.putString("PASS", password)
                editor.putBoolean("CHECK", rememberMe.isChecked)
                editor.apply()
            } else {
                editor.putString("EMAIL", null)
                editor.putString("PASS", null)
                editor.putBoolean("CHECK", rememberMe.isChecked)
                editor.apply()
            }

            if (email.isEmpty()) {
                logInEmail.error = "Please enter your email"
                logInEmail.requestFocus()
                return@setOnClickListener
            }

            if (password.isEmpty()) {
                logInPassword.error = "Please enter your password"
                logInPassword.requestFocus()
                return@setOnClickListener
            }

            //Firebase Authentication to login
            FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener {
                    if (it.isSuccessful) {
                        val user = FirebaseAuth.getInstance().currentUser
                        updateUI(user)
                    } else {
                        updateUI(null)
                    }
                }

        }

    }

    fun signupBack(view: View) {
        startActivity(Intent(this, SignUp_Page::class.java))
    }

    public override fun onStart() {
        super.onStart()
        // Check if user is signed in (non-null) and update UI accordingly.
//        val currentUser = FirebaseAuth.getInstance().currentUser
//        updateUI(currentUser)
        val preferences = getPreferences(Context.MODE_PRIVATE)
        val email = preferences.getString("EMAIL", "")
        var password = preferences.getString("PASS", "")
        var isMyValueChecked: Boolean = preferences.getBoolean("CHECK", false)
        logInEmail.setText(email)
        logInPassword.setText(password)
        rememberMe.isChecked = isMyValueChecked

    }

    private fun updateUI(currentUser: FirebaseUser?) {
        if (currentUser != null) {
            if (currentUser.isEmailVerified) {
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            } else {
                Toast.makeText(baseContext, "Verify your email address.", Toast.LENGTH_SHORT).show()
            }
        } else
            Toast.makeText(baseContext, "Login failed.", Toast.LENGTH_SHORT).show()
    }

}