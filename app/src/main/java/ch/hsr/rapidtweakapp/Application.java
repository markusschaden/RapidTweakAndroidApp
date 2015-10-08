package ch.hsr.rapidtweakapp;

import java.util.Observable;
import java.util.Observer;

import ch.hsr.rapidtweakapp.domain.Race;

/**
 * Created by Noah on 06.10.2015.
 */
public class Application extends android.app.Application implements Observer {
    private Race race = new Race();

    public Application() {
        this.race = new Race();
    }

    public Race getRace() {
        return race;
    }


    @Override
    public void update(Observable observable, Object data) {

    }

}
