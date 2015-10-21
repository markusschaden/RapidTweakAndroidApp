package ch.hsr.rapidtweakapp;

import android.util.Log;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.Coordinate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import ch.hsr.rapidtweakapp.domain.Race;
import ch.hsr.rapidtweakapp.websocket.RapidTweakWebSocketClient;

/**
 * Created by Noah on 06.10.2015.
 */
public class Application extends android.app.Application {
    private Race race;
    private RapidTweakWebSocketClient connection = null;

    public Application() {
        this.race = new Race();
        connection = null;
    }

    public Race getRace() {
        return race;
    }

    public void connect(String address) {
        URI uri = createUri(address);
        try {
            connection = null;
            this.connection = new RapidTweakWebSocketClient(this, uri);
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    private URI createUri(String address) {
        URI uri = null;
        try{
            uri = new URI("ws://" + address + ":10500");
        } catch (URISyntaxException e){
            Log.e("Websocket", "URI Exception");
            e.printStackTrace();
        }
        return uri;
    }

    public boolean connectionStatus(){
        return connection == null ? false : true;
    }

}
