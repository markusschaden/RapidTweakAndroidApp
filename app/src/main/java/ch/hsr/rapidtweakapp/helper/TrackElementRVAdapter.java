package ch.hsr.rapidtweakapp.helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.TrackElement;

import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.domain.TrackElements;

/**
 * Created by Noah on 06.10.2015.
 */
public class TrackElementRVAdapter extends RecyclerView.Adapter<TrackElementViewHolder> {
    private TrackElements dataset;

    public TrackElementRVAdapter(TrackElements trackElements) {
        dataset = trackElements;
    }

    @Override
    public TrackElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.track_element, parent, false);
        TrackElementViewHolder viewHolder = new TrackElementViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(TrackElementViewHolder holder, int position) {
        final TrackElement trackElement = dataset.get(position);
        holder.title.setText(trackElement.getTrackName());
        holder.best.setText("Best: "+trackElement.getAverageDuration(100));
        holder.last.setText("Best: "+trackElement.getAverageDuration(100));
    }

    @Override
    public int getItemCount() {
        return dataset.getSize();
    }

}
