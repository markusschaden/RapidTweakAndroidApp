package ch.hsr.rapidtweakapp.domain;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.TrackElement;

import java.util.HashMap;

/**
 * Created by Noah on 06.10.2015.
 */
public class TrackElements {
    private HashMap<String, TrackElement> trackElements = new HashMap<String, TrackElement>();

    public TrackElements() {

    }

    public void add(TrackElement trackElement) {
        trackElements.put(trackElement.getElementName(), trackElement);
    }

    public TrackElement get(String elementName) {
        return trackElements.get(elementName);
    }



    public int getSize() {
        return trackElements.size();
    }
}
