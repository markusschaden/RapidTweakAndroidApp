package ch.hsr.rapidtweakapp.helper;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import org.w3c.dom.Text;

import ch.hsr.rapidtweakapp.R;

/**
 * Created by Noah on 06.10.2015.
 */
public class TrackElementViewHolder extends RecyclerView.ViewHolder {
    public TextView title;
    public TextView best;
    public TextView last;
    
    public TrackElementViewHolder(View itemView) {
        super(itemView);
        title = (TextView)itemView.findViewById(R.id.track_title);
        best = (TextView)itemView.findViewById(R.id.track_time_best);
        last = (TextView)itemView.findViewById(R.id.track_time_last);
    }



}
