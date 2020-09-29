package user.refihcan.canrefih

import android.os.Bundle
import android.view.MenuItem
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.fragment.app.FragmentTransaction
import com.etebarian.meowbottomnavigation.MeowBottomNavigation
import com.google.android.material.navigation.NavigationView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    companion object {
        private const val ID_HOME = 1
        private const val ID_CART = 2
        private const val ID_MAIL = 3
        private const val ID_PROFILE = 4
    }

    lateinit var homePage: Home_Page
    lateinit var cartPage: Cart_Page
    lateinit var mailPage: Mail_Page
    lateinit var profilePage: Profile_Page

    override fun onCreate(savedInstanceState: Bundle?) {

        //hiding status bar, navbar
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Bottom navigation menu hooks
        bottom_navigation.add(MeowBottomNavigation.Model(ID_HOME, R.drawable.home32))
        bottom_navigation.add(MeowBottomNavigation.Model(ID_CART, R.drawable.cart))
        bottom_navigation.add(MeowBottomNavigation.Model(ID_MAIL, R.drawable.email))
        bottom_navigation.add(MeowBottomNavigation.Model(ID_PROFILE, R.drawable.user))


        //This is our start/default page
        homePage = Home_Page()
        supportFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, homePage)
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
            .commit()


        //Toolbar
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayShowTitleEnabled(false)

        navigationDrawer()

        bottom_navigation.setOnClickMenuListener {
            //Navigation Bottom Item Selecting
            when (it.id) {
                ID_HOME -> {
                    homePage = Home_Page()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, homePage)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                ID_CART -> {
                    cartPage = Cart_Page()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, cartPage)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                ID_MAIL -> {
                    mailPage = Mail_Page()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, mailPage)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                ID_PROFILE -> {
                    profilePage = Profile_Page()
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.frame_layout, profilePage)
                        .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                        .commit()
                }
                else -> ""
            }
        }
    }

    //Navigation Drawer Opening
    private fun navigationDrawer() {
        design_navigation_view.bringToFront()
        design_navigation_view.setNavigationItemSelectedListener(this)

        //Drawer menu icon opening
        open_drawer_menu.setOnClickListener {
            if (drawer_layout.isDrawerVisible(GravityCompat.START))
                drawer_layout.closeDrawer(GravityCompat.START)
            else
                drawer_layout.openDrawer(GravityCompat.START)
        }
    }

    //Navigation Drawer Back Pressing
    override fun onBackPressed() {
        if (drawer_layout.isDrawerVisible(GravityCompat.START))
            drawer_layout.closeDrawer(GravityCompat.START)
        else
            super.onBackPressed()
    }

    //Navigation Drawer Item Selecting
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        val id = item.itemId
        when(id){
            R.id.search_menu -> ""
            R.id.promo_codes_menu -> ""
            R.id.orders_menu -> ""
            R.id.full_android_menu -> ""
            R.id.full_ios_menu -> ""
            R.id.front_end_menu -> ""
            R.id.flutter_menu -> ""
            R.id.others_menu -> ""
            R.id.support_menu -> ""
            R.id.settings_menu -> ""
            R.id.about_menu -> ""
            else -> true
        }
        return true
    }
}