package ch.hsr.rapidtweakapp.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.List;
import java.util.Observable;

import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.domain.TrackElements;
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
    }

    public void onDataUpdate(TrackElements race) {

        adapter = new TrackElementRVAdapter(race);
        rv.setAdapter(adapter);
        rv.getAdapter().notifyDataSetChanged();
    }
}
