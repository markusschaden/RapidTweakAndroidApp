package ch.hsr.rapidtweakapp.helper;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.TrackElement;

import java.sql.Time;
import java.util.Date;
import java.util.concurrent.TimeUnit;

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
        return new TrackElementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrackElementViewHolder holder, int position) {
        final TrackElement trackElement = dataset.get(position);
        if(trackElement == null){
            return;
        }
        long best = trackElement.getBestTime();
        long last = (long)trackElement.getLatestDuration();
        String bestString = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toSeconds(best),
                TimeUnit.MILLISECONDS.toMillis(best) % TimeUnit.SECONDS.toMillis(1));
        String lastString = String.format("%02d:%02d",
                TimeUnit.MILLISECONDS.toSeconds(last),
                TimeUnit.MILLISECONDS.toMillis(last) % TimeUnit.SECONDS.toMillis(1));

        holder.title.setText(trackElement.getElementName());
        holder.best.setText("Best: " + bestString);
        holder.last.setText("Last: " + lastString);
        if(best <= last) {
            holder.cardView.setCardBackgroundColor(R.color.primary_dark);
        } else {
            holder.cardView.setCardBackgroundColor(R.color.accent);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.getSize();
    }

}
