package com.example.tracker.mother

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.example.tracker.R

class SettingActivityFragment : PreferenceFragmentCompat(){
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }

}