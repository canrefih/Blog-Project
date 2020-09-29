package user.refihcan.canrefih.NavigationDrawerFragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import user.refihcan.canrefih.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}