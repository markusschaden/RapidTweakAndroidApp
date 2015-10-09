package ch.hsr.rapidtweakapp.activities;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.net.URI;
import java.net.URISyntaxException;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.R;

/**
 * Created by Noah on 09.10.2015.
 */
public class SettingsActivity extends Main{

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

    }

}
