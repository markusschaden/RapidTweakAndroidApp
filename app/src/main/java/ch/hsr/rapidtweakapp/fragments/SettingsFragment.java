package ch.hsr.rapidtweakapp.fragments;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceFragment;
import android.preference.PreferenceManager;

import java.net.URI;
import java.net.URISyntaxException;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.R;

/**
 * Created by Noah on 09.10.2015.
 */
public class SettingsFragment extends PreferenceFragment implements SharedPreferences.OnSharedPreferenceChangeListener{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Load the preferences from an XML resource
        addPreferencesFromResource(R.xml.settings);
        PreferenceManager.getDefaultSharedPreferences(getActivity()).registerOnSharedPreferenceChangeListener(this);
    }


    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if(key.equals("server_address")) {
            ((Application) getActivity().getApplication()).connect(sharedPreferences.getString("server_address", "0"));
        }
    }
}
