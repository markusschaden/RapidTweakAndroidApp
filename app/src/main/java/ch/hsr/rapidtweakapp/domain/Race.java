package ch.hsr.rapidtweakapp.domain;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Element;

import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Noah on 06.10.2015.
 */
public class Race {
    private List<Element> elements = new ArrayList<Element>();
    @Setter @Getter
    private int roundNumber;
    @Setter @Getter
    private long roundTimeMessage;
    public Race() {

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
    }

    public Element get(int position) {
        return elements.get(position);
    }

    public void update(Element element) {
        elements.set(element.getId(), element);
    }

    public int getSize() {
        return elements.size();
    }
    
    public void startRace() {
        elements.clear();
    }
}
