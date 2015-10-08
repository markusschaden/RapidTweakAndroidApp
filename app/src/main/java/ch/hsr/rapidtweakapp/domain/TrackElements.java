package ch.hsr.rapidtweakapp.domain;

import android.util.Log;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Element;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.TrackElement;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Noah on 06.10.2015.
 */
public class TrackElements {
    private List<Element> elements = new ArrayList<Element>();

    public TrackElements() {

    }

    public void add(Element element) {
        int index = element.getId();
        if(index >= 0 && index < elements.size()) {
            elements.set(index, element);
        } else {
            int insertNulls = index - elements.size();
            for (int i = 0; i < insertNulls; i++) {
                elements.add(null);
            }
            elements.add(element);
        }
        Log.i("A Race elements:",""+elements.size());
    }

    public Element get(int position) {
        return elements.get(position);
    }

    public void update(Element element) {
        elements.set(element.getId(), element);
        Log.i("U Race elements:",""+elements.size());
    }

    public int getSize() {
        return elements.size();
    }
}
