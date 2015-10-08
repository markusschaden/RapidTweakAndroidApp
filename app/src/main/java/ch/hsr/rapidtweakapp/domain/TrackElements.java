package ch.hsr.rapidtweakapp.domain;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.TrackElement;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Noah on 06.10.2015.
 */
public class TrackElements {
    private ArrayList<TrackElement> trackElements = new ArrayList<TrackElement>();

    public TrackElements() {

    }

    public void add(TrackElement element) {
        int index = element.getId();
        if(index >= 0 && index < trackElements.size()) {
            trackElements.set(index, element);
        } else {
            int insertNulls = index - trackElements.size();
            for (int i = 0; i < insertNulls; i++) {
                trackElements.add(null);
            }
            trackElements.add(element);
        }
    }

    public TrackElement get(int position) {
        return trackElements.get(position);
    }

    public void update(TrackElement trackElement) {
        trackElements.set(trackElement.getId(),trackElement);
    }

    public int getSize() {
        return trackElements.size();
    }
}
