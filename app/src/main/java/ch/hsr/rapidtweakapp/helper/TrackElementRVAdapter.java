package ch.hsr.rapidtweakapp.helper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Element;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Speed;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.SpeedMeasureTrackElement;
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
    private Context context;

    public TrackElementRVAdapter(Context context, TrackElements trackElements) {
        this.context = context;
        this.dataset = trackElements;
    }

    @Override
    public TrackElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.track_element, parent, false);
        return new TrackElementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(TrackElementViewHolder holder, int position) {
        final Element element = dataset.get(position);
        if(element == null){
            return;
        }
        if(element instanceof TrackElement) {
            TrackElement trackElement = (TrackElement)element;
            holder.speedElementContainer.setVisibility(View.GONE);
            holder.trackElementContainer.setVisibility(View.VISIBLE);
            long best = trackElement.getBestTime();
            long last = trackElement.getLatestDuration();
            String bestString = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toSeconds(best),
                    TimeUnit.MILLISECONDS.toMillis(best) % TimeUnit.SECONDS.toMillis(1));
            String lastString = String.format("%02d:%02d",
                    TimeUnit.MILLISECONDS.toSeconds(last),
                    TimeUnit.MILLISECONDS.toMillis(last) % TimeUnit.SECONDS.toMillis(1));

            holder.title.setText(trackElement.getElementName());
            if (trackElement.getElementName().startsWith("Left curve")) {
                holder.image.setBackgroundResource(R.drawable.ic_left_curve);
            } else if (trackElement.getElementName().startsWith("Right curve")) {
                holder.image.setBackgroundResource(R.drawable.ic_right_curve);
            } else {
                holder.image.setBackgroundResource(R.drawable.ic_straight);
            }

            holder.best.setText("Best: " + bestString);
            holder.last.setText("Last: " + lastString);
            if (last <= best) {
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.primary_dark));
            } else {
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.accent));
            }
        } else if(element instanceof SpeedMeasureTrackElement) {
            holder.trackElementContainer.setVisibility(View.GONE);
            holder.speedElementContainer.setVisibility(View.VISIBLE);

            SpeedMeasureTrackElement speedMeasureTrackElement = (SpeedMeasureTrackElement)element;
            String lastSpeed = String.format("%.3f",speedMeasureTrackElement.getLastSpeed());

            holder.speed.setText("Speed: " + lastSpeed);
            holder.maxSpeed.setText("Max Speed: " + speedMeasureTrackElement.getSpeedLimit());
            holder.speedTitle.setText("Speed Measure " +  speedMeasureTrackElement.getSourceId());
            holder.cardView.setCardBackgroundColor(Color.WHITE);
        }
    }

    @Override
    public int getItemCount() {
        return dataset.getSize();
    }

}
