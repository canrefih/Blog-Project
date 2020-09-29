package user.refihcan.canrefih

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.profile_page.*

class Profile_Page : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_page, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        val personalinfo = personalinfo
        val experience = experience
        val review = review

        personalinfo.setVisibility(View.VISIBLE)
        experience.setVisibility(View.GONE)
        review.setVisibility(View.GONE)


        personalinfobtn.setOnClickListener {
            personalinfo.setVisibility(View.VISIBLE)
            experience.setVisibility(View.GONE)
            review.setVisibility(View.GONE)
            personalinfobtn.setTextColor(resources.getColor(R.color.colorPrimary))
            experiencebtn.setTextColor(resources.getColor(R.color.gray_bold))
            reviewbtn.setTextColor(resources.getColor(R.color.gray_bold))
        }

        experiencebtn.setOnClickListener {
            personalinfo.setVisibility(View.GONE)
            experience.setVisibility(View.VISIBLE)
            review.setVisibility(View.GONE)
            personalinfobtn.setTextColor(resources.getColor(R.color.gray_bold))
            experiencebtn.setTextColor(resources.getColor(R.color.colorPrimary))
            reviewbtn.setTextColor(resources.getColor(R.color.gray_bold))
        }

        reviewbtn.setOnClickListener {
            personalinfo.setVisibility(View.GONE)
            experience.setVisibility(View.GONE)
            review.setVisibility(View.VISIBLE)
            personalinfobtn.setTextColor(resources.getColor(R.color.gray_bold))
            experiencebtn.setTextColor(resources.getColor(R.color.gray_bold))
            reviewbtn.setTextColor(resources.getColor(R.color.colorPrimary))
        }
    }
}