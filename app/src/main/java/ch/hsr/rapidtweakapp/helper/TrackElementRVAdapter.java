package ch.hsr.rapidtweakapp.helper;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.google.common.collect.Lists;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Duration;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Element;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.SpeedMeasureTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.TrackElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.domain.Race;

/**
 * Created by Noah on 06.10.2015.
 */
public class TrackElementRVAdapter extends RecyclerView.Adapter<TrackElementViewHolder> {
    private Race dataset;
    private Context context;

    public TrackElementRVAdapter(Context context, Race race) {
        this.context = context;
        this.dataset = race;
    }

    @Override
    public TrackElementViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View v = layoutInflater.inflate(R.layout.track_element, parent, false);
        return new TrackElementViewHolder(v);
    }

    @Override
    public void onBindViewHolder(final TrackElementViewHolder holder, int position) {
        final Element element = dataset.get(position);
        if(element == null){
            return;
        }

        holder.element = element;

        if(element instanceof TrackElement) {
            final TrackElement trackElement = (TrackElement)element;
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


            holder.durations.setOnTouchListener(new View.OnTouchListener() {
                //Prevent scrolling in recyclerview
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });

            List<String> lst = new ArrayList<>();
            int round = 1;
            for(Duration d : trackElement.getDurations()) {
                String durationString = String.format("%02d:%02d",
                        TimeUnit.MILLISECONDS.toSeconds(d.getTime()),
                        TimeUnit.MILLISECONDS.toMillis(d.getTime()) % TimeUnit.SECONDS.toMillis(1));
                lst.add("Round " + round +": " + durationString);
                round++;
            }

            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, Lists.reverse(lst));
            holder.durations.setAdapter(itemsAdapter);

            if(dataset.getCollapsed().containsKey(position) && dataset.getCollapsed().get(position)) {
                holder.detailedInfos.setVisibility(View.VISIBLE);
            } else {
                holder.detailedInfos.setVisibility(View.GONE);
            }

            holder.cardView.setOnClickListener(new CardClickListenerTrackElement(holder, position));



        } else if(element instanceof SpeedMeasureTrackElement) {
            holder.trackElementContainer.setVisibility(View.GONE);
            holder.speedElementContainer.setVisibility(View.VISIBLE);

            SpeedMeasureTrackElement speedMeasureTrackElement = (SpeedMeasureTrackElement)element;
            String lastSpeed = String.format("%.3f",speedMeasureTrackElement.getLastSpeed());

            holder.speed.setText("Speed: " + lastSpeed);
            holder.maxSpeed.setText("Max Speed: " + speedMeasureTrackElement.getSpeedLimit());
            holder.speedTitle.setText("Speed Measure " +  speedMeasureTrackElement.getSourceId());
            holder.cardView.setCardBackgroundColor(Color.WHITE);


            holder.speeds.setOnTouchListener(new View.OnTouchListener() {
                //Prevent scrolling in recyclerview
                public boolean onTouch(View v, MotionEvent event) {
                    v.getParent().requestDisallowInterceptTouchEvent(true);
                    return false;
                }
            });

            List<String> lst = new ArrayList<>();
            int round = 1;
            for(Double d : speedMeasureTrackElement.getSpeeds()) {
                String speedString = String.format("%.3f",d);

                lst.add("Round " + round +": " + speedString);
                round++;
            }

            ArrayAdapter<String> itemsAdapter = new ArrayAdapter<>(context, android.R.layout.simple_list_item_1, Lists.reverse(lst));
            holder.speeds.setAdapter(itemsAdapter);

            if(dataset.getCollapsed().containsKey(position) && dataset.getCollapsed().get(position)) {
                holder.detailedInfosSpeed.setVisibility(View.VISIBLE);
            } else {
                holder.detailedInfosSpeed.setVisibility(View.GONE);
            }

            if (speedMeasureTrackElement.getSpeedLimit() > 0 && speedMeasureTrackElement.getLastSpeed() >= speedMeasureTrackElement.getSpeedLimit()) {
                holder.cardView.setCardBackgroundColor(context.getResources().getColor(R.color.accent));
            } else {
                holder.cardView.setCardBackgroundColor(Color.WHITE);
            }

            holder.cardView.setOnClickListener(new CardClickListenerSpeedElement(holder, position));
        }
    }

    @Override
    public int getItemCount() {
        return dataset.getSize();
    }

    class CardClickListenerTrackElement implements View.OnClickListener{

        private int position;
        private TrackElementViewHolder holder;

        public CardClickListenerTrackElement(TrackElementViewHolder holder, int position) {
            this.holder = holder;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if(holder.detailedInfos.getVisibility() == View.GONE) {
                holder.detailedInfos.setVisibility(View.VISIBLE);
                dataset.getCollapsed().put(position, true);
            } else {
                holder.detailedInfos.setVisibility(View.GONE);
                dataset.getCollapsed().remove(position);
            }
        }
    }

    class CardClickListenerSpeedElement implements View.OnClickListener{

        private int position;
        private TrackElementViewHolder holder;

        public CardClickListenerSpeedElement(TrackElementViewHolder holder, int position) {
            this.holder = holder;
            this.position = position;
        }

        @Override
        public void onClick(View v) {
            if(holder.detailedInfosSpeed.getVisibility() == View.GONE) {
                holder.detailedInfosSpeed.setVisibility(View.VISIBLE);
                dataset.getCollapsed().put(position, true);
            } else {
                holder.detailedInfosSpeed.setVisibility(View.GONE);
                dataset.getCollapsed().remove(position);
            }
        }
    }

}
