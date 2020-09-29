package user.refihcan.canrefih

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.home_page.*

class Home_Page : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

//        logOutButton.setOnClickListener {
//            FirebaseAuth.getInstance().signOut()
//            startActivity(Intent(context,LogIn_Page::class.java))
//        }
        return inflater.inflate(R.layout.home_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        folding_cell.setOnClickListener {
            folding_cell.toggle(false)
        }
    }

}