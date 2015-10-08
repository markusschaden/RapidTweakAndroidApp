package ch.hsr.rapidtweakapp.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.helper.RaceChange;
import ch.hsr.rapidtweakapp.helper.RaceInformation;
import ch.hsr.rapidtweakapp.helper.TrackElementRVAdapter;

/**
 * Created by Noah on 30.09.2015.
 */
public class TrackElementsActivity extends Main  {
    TrackElementRVAdapter adapter;
    RecyclerView rv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle(getString(R.string.track));
        setContentView(R.layout.activity_track_elements);

        rv = (RecyclerView) findViewById(R.id.track_element_container);
        rv.setHasFixedSize(true);

        LinearLayoutManager llm = new LinearLayoutManager(this);
        rv.setLayoutManager(llm);

        Application app = ((Application)this.getApplication());
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
//        Log.i("Activity", "On Race Restart");
//        Application app = ((Application)this.getApplication());
//        adapter = new TrackElementRVAdapter(this, app.getRace());
//        rv.setAdapter(adapter);
        rv.getAdapter().notifyDataSetChanged();
    }
    public void onRoundtime(){
        Log.i("RoundTime", "Activity got Roundtime");
    }
}
