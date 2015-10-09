package ch.hsr.rapidtweakapp.domain;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Element;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.SpeedMeasureTrackElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Observable;
import java.util.concurrent.TimeUnit;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Noah on 06.10.2015.
 */
public class Race {
    private List<Element> elements = new ArrayList<Element>();
    private List<Long> roundTimeList = new ArrayList<Long>();
    private long roundTimeBest = 999999;
    @Setter @Getter
    private Map<Integer, Boolean> collapsed = new HashMap<>();


    public Race() {
        roundTimeList.add(new Long(0));
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
    public void addRoundtime(long roundtime) {
        if(roundTimeBest > roundtime){
            roundTimeBest = roundtime;
        }
        roundTimeList.add(roundtime);
    }

    public String getRoundtimeLastString() {
        long raw = roundTimeList.get(roundTimeList.size() - 1);
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(raw),
                TimeUnit.MILLISECONDS.toSeconds(raw) % TimeUnit.MINUTES.toSeconds(1),
                TimeUnit.MILLISECONDS.toMillis(raw) % TimeUnit.SECONDS.toMillis(1));
    }

    public long getRoundTimeBest(){
        return roundTimeBest;
    }
    public String getRoundtimeBestString() {
        return String.format("%02d:%02d:%02d",
                TimeUnit.MILLISECONDS.toMinutes(roundTimeBest),
                TimeUnit.MILLISECONDS.toSeconds(roundTimeBest) % TimeUnit.MINUTES.toSeconds(1),
                TimeUnit.MILLISECONDS.toMillis(roundTimeBest) % TimeUnit.SECONDS.toMillis(1));
    }

    public int getRoundNumber(){
        return roundTimeList.size();
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
        roundTimeList.clear();
        roundTimeList.add(new Long(0));
        roundTimeBest = 999999;
    }

    public void addPenalty(String sourceId) {
        /*for(Element e : elements) {
            if(e instanceof SpeedMeasureTrackElement) {
                SpeedMeasureTrackElement smte = (SpeedMeasureTrackElement)e;
                if(sourceId.equals(smte.getSourceId())) {
                    smte.setPenaltyOccured(true);
                    return smte.getId();
                }
            }
        }
        return -1;*/
    }
}
