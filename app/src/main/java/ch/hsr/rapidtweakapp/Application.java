package ch.hsr.rapidtweakapp;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RoundTimeMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.StraightTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.TrackElement;

import java.util.HashMap;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

import ch.hsr.rapidtweakapp.domain.TrackElements;
import ch.hsr.rapidtweakapp.helper.IVisitor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * Created by Noah on 06.10.2015.
 */
public class Application extends android.app.Application implements Observer {
    private TrackElements race = new TrackElements();

    public Application() {
        this.race = new TrackElements();
    }

    public TrackElements getRace() {
        return race;
    }


    @Override
    public void update(Observable observable, Object data) {

    }

}
