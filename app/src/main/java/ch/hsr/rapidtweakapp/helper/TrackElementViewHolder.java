package ch.hsr.rapidtweakapp.helper;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Element;

import org.w3c.dom.Text;

import ch.hsr.rapidtweakapp.R;

/**
 * Created by Noah on 06.10.2015.
 */
public class TrackElementViewHolder extends RecyclerView.ViewHolder {
    public CardView cardView;
    public LinearLayout trackElementContainer, speedElementContainer, detailedInfos;
    public ImageView image;
    public TextView title;
    public TextView best;
    public TextView last;

    public TextView speedTitle;
    public TextView maxSpeed;
    public TextView speed;

    public ListView durations;
    public Element element;

    public TrackElementViewHolder(View itemView) {
        super(itemView);
        cardView = (CardView)itemView.findViewById(R.id.card_track_element);
        image = (ImageView)itemView.findViewById(R.id.track_image);
        title = (TextView)itemView.findViewById(R.id.track_title);
        best = (TextView)itemView.findViewById(R.id.track_time_best);
        last = (TextView)itemView.findViewById(R.id.track_time_last);

        speedTitle = (TextView)itemView.findViewById(R.id.speed_element_id);
        maxSpeed = (TextView)itemView.findViewById(R.id.speed_element_maxSpeed);
        speed = (TextView)itemView.findViewById(R.id.speed_element_speed);

        trackElementContainer = (LinearLayout)itemView.findViewById(R.id.track_element_container);
        speedElementContainer = (LinearLayout)itemView.findViewById(R.id.speed_element_container);
        detailedInfos = (LinearLayout)itemView.findViewById(R.id.detailedInfos);

        durations = (ListView)itemView.findViewById(R.id.listDurations);
    }



}
