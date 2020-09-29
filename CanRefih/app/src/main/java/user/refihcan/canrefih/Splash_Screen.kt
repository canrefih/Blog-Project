package user.refihcan.canrefih

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.splashscreen.*

class Splash_Screen : AppCompatActivity() {

    lateinit var handler: Handler
    override fun onCreate(savedInstanceState: Bundle?) {

        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        super.onCreate(savedInstanceState)
        setContentView(R.layout.splashscreen)

        val animation = AnimationUtils.loadAnimation(baseContext, R.anim.fade)

        rights.startAnimation(animation)
        animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationRepeat(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                handler = Handler()
                handler.postDelayed({
                    finish()
                    startActivity(Intent(this@Splash_Screen, LogIn_Page::class.java))
                }, 1500)

            }

            override fun onAnimationStart(animation: Animation?) {

            }
        })

    }
}