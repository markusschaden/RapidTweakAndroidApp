package ch.hsr.rapidtweakapp.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.view.View;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.Coordinate;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RaceDrawerMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RacePositionMessage;

import java.util.List;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.domain.Race;
import ch.hsr.rapidtweakapp.helper.CanvasView;

/**
 * Created by Noah on 30.09.2015.
 */
public class TrackActivity extends Main {

    private CanvasView customCanvas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle(getString(R.string.track));
        setContentView(R.layout.activity_track);

        customCanvas = (CanvasView) findViewById(R.id.race_canvas);
        Race race = ((Application)this.getApplication()).getRace();
        if(race.getRaceCoordinantes() != null) {
            drawRace(race.getRaceCoordinantes());
        }
        
        IntentFilter filter = new IntentFilter();
        filter.addAction("raceDrawerInformation");
        filter.addAction("racePositionInformation");
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {

            if(intent.getSerializableExtra("raceDrawerInformation") != null) {
                List<Coordinate> raceDrawer = ((RaceDrawerMessage) intent.getSerializableExtra("raceDrawerInformation")).getTrack();

                TrackActivity.this.drawRace(raceDrawer);

            } else if (intent.getSerializableExtra("racePositionInformation") != null) {
                Coordinate racePosition = ((RacePositionMessage)intent.getSerializableExtra("racePositionInformation")).getPosition();

            }
        }
    };

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }

    public void drawRace(List<Coordinate> raceDrawer) {
        customCanvas.setRaceCoordinates(raceDrawer);
    }

}
