package ch.hsr.rapidtweakapp.activities;

import android.os.Bundle;

import java.util.Observable;

import ch.hsr.rapidtweakapp.R;

/**
 * Created by Noah on 30.09.2015.
 */
public class TrackElementsActivity extends Main  {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle(getString(R.string.track));
        setContentView(R.layout.activity_track_elements);

    }
}
