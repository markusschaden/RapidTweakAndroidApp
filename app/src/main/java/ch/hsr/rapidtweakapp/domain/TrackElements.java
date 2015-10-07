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

    public void add(TrackElement trackElement) {
        trackElements.add(trackElement);
    }

    public TrackElement get(int position) {
        return trackElements.get(position);
    }

    public int getSize() {
        return trackElements.size();
    }
}
