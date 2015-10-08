package ch.hsr.rapidtweakapp;

import java.net.URISyntaxException;
import java.util.Observable;
import java.util.Observer;

import ch.hsr.rapidtweakapp.domain.Race;
import ch.hsr.rapidtweakapp.websocket.RapidTweakWebSocketClient;

/**
 * Created by Noah on 06.10.2015.
 */
public class Application extends android.app.Application implements Observer {
    private Race race;
    private RapidTweakWebSocketClient connection;

    public Application() {
        this.race = new Race();
        try {
            if(connection == null) {
                connection = new RapidTweakWebSocketClient(this);
            }
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public Race getRace() {
        return race;
    }


    @Override
    public void update(Observable observable, Object data) {

    }

}
