package ch.hsr.rapidtweakapp.websocket;

import android.util.Log;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft;
import org.java_websocket.drafts.Draft_10;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.drafts.Draft_75;
import org.java_websocket.handshake.ServerHandshake;

import java.net.URI;
import java.net.URISyntaxException;

/**
 * Created by Markus on 28.09.2015.
 */
public class RapidTweakWebSocketClient extends WebSocketClient {

    public RapidTweakWebSocketClient() throws URISyntaxException {

        super(new URI("ws://152.96.239.241:10500"), new Draft_17());

        connect();
        Log.i("WebSocket", "Connected");
    }


    @Override
    public void onMessage(String message) {
        Log.i("WebSocket", "got: " + message);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        Log.i("WebSocket", "You are connected to ChatServer: " + getURI() + "\n");

    }

    @Override
    public void onClose(int code, String reason, boolean remote) {
        Log.i("WebSocket", "You have been disconnected from: " + getURI() + "; Code: " + code + " " + reason + "\n");

    }

    @Override
    public void onError(Exception ex) {
        Log.i("WebSocket", "Exception occured ...\n" + ex + "\n");

    }


    public void changeSpeed(int progress) {
        send("speed="+progress);
    }
}
