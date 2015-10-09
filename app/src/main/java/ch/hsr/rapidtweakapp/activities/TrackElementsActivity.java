package ch.hsr.rapidtweakapp.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.domain.Race;
import ch.hsr.rapidtweakapp.helper.RaceChange;
import ch.hsr.rapidtweakapp.helper.RaceInformation;
import ch.hsr.rapidtweakapp.helper.TrackElementRVAdapter;

/**
 * Created by Noah on 30.09.2015.
 */
public class TrackElementsActivity extends Main  {
    Race race;
    TrackElementRVAdapter adapter;
    TextView roundNumber;
    TextView roundTimeBest;
    TextView roundTimeLast;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle(getString(R.string.track));
        setContentView(R.layout.activity_track_elements);

        Application app = ((Application)this.getApplication());

        if(!app.connectionStatus()) {
            SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
            app.connect(prefs.getString("server_address", "10.0.2.2"));
        }

        race = ((Application)getApplication()).getRace();

        roundNumber = (TextView)findViewById(R.id.race_round_number);
        roundTimeBest = (TextView)findViewById(R.id.race_round_time_best);
        roundTimeLast = (TextView)findViewById(R.id.race_round_time_last);
        roundNumber.setText("Round Number: " + race.getRoundNumber());
        if(race.getRoundTimeBest() != 999999){
            roundTimeBest.setText("Best: " + race.getRoundtimeBestString());
        } else {
            roundTimeBest.setText("Best: N/A");
        }
        roundTimeLast.setText("Last: " + race.getRoundtimeLastString());


        rv = (RecyclerView) findViewById(R.id.track_element_container);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        adapter = new TrackElementRVAdapter(this, app.getRace());
        rv.setAdapter(adapter);

        IntentFilter filter = new IntentFilter();
        filter.addAction("raceChanged");
        filter.addAction("raceInformation");
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.i("Broadcast", "Received");
            if(intent.getSerializableExtra("changeType") != null) {
                RaceChange changeType = (RaceChange) intent.getSerializableExtra("changeType");
                int position = intent.getIntExtra("position", 0);
                switch (changeType) {
                    case ADD:
                        onDataAdd(position);
                        break;
                    case UPDATE:
                        onDataUpdate(position);
                        break;
                }
            } else if (intent.getSerializableExtra("raceInformation") != null) {
                RaceInformation raceInfo = (RaceInformation)intent.getSerializableExtra("raceInformation");
                switch (raceInfo) {
                    case RACE_START:
                        onStartRace();
                        break;
                    case ROUNDTIME:
                        onRoundtime();
                        break;
                }
            }
        }
    };

    public void onDataAdd(int position) {
        rv.getAdapter().notifyItemInserted(position);
    }
    public void onDataUpdate(int position) {
        rv.getAdapter().notifyItemChanged(position);
    }
    public void onStartRace(){
        rv.getAdapter().notifyDataSetChanged();
    }
    public void onRoundtime(){
        Race race = ((Application)this.getApplication()).getRace();
        roundNumber.setText("Round Number: " + race.getRoundNumber());
        roundTimeBest.setText("Best: " + race.getRoundtimeBestString());
        roundTimeLast.setText("Last: " + race.getRoundtimeLastString());
    }
}
