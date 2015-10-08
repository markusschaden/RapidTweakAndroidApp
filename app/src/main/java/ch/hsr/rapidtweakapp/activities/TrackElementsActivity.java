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

import java.util.List;
import java.util.Observable;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.domain.TrackElements;
import ch.hsr.rapidtweakapp.helper.RaceChange;
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

        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, new IntentFilter("raceChanged"));
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.i("Broadcast", "Received");
            RaceChange changeType = (RaceChange)intent.getSerializableExtra("changeType");
            int position = intent.getIntExtra("position", 0);
            switch (changeType) {
                case ADD:
                    onDataAdd(position);
                    break;
                case UPDATE:
                    onDataUpdate(position);
                    break;
            }
        }
    };

    public void onDataAdd(int position) {
        rv.getAdapter().notifyItemInserted(position);
    }
    public void onDataUpdate(int position) {
        rv.getAdapter().notifyItemChanged(position);
    }
}
