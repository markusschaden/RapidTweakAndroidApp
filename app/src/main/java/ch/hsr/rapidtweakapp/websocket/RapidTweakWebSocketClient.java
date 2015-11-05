package ch.hsr.rapidtweakapp.websocket;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.Message;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.MonitoringMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.PowerMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RaceDrawerMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RoundTimeMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.StartMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.StopMessage;

import org.java_websocket.client.WebSocketClient;
import org.java_websocket.drafts.Draft_17;
import org.java_websocket.handshake.ServerHandshake;

import java.io.FileOutputStream;
import java.net.URI;
import java.net.URISyntaxException;

import ch.hsr.rapidtweakapp.helper.Serializator;
import ch.hsr.rapidtweakapp.service.InformationMessageService;
import ch.hsr.rapidtweakapp.service.MonitoringMessageService;

/**
 * Created by Markus on 28.09.2015.
 */
public class RapidTweakWebSocketClient extends WebSocketClient {
    private String FILENAME = "log";
    private FileOutputStream fos;
    private Context context;

    public RapidTweakWebSocketClient(Context context, URI uri) throws URISyntaxException {
        super(uri, new Draft_17());

        this.context = context;
        connect();
        Log.i("WebSocket", "Connected");
    }


    @Override
    public void onMessage(String message) {

        String parts[] = message.split("\\|");
        if (parts.length == 2) {
            String className = parts[0];
            String data = parts[1];

            try {
                Class clazz = Class.forName(className);
                Serializator<Message> serializator = new Serializator<>();
                Message rawMessage = serializator.deserialize(data, clazz);

                Log.i("Message", rawMessage.toString());
                if(clazz == MonitoringMessage.class) {
                    Intent intent = new Intent(context, MonitoringMessageService.class);
                    intent.putExtra("MonitoringMessage", rawMessage);
                    context.startService(intent);
                } else if (clazz == StartMessage.class || clazz ==  StopMessage.class || clazz == RoundTimeMessage.class || clazz == RaceDrawerMessage.class) {
                    Intent intent = new Intent(context, InformationMessageService.class);
                    intent.putExtra("InformationMessage", rawMessage);
                    context.startService(intent);
                } else if (clazz == PowerMessage.class) {
                    Intent intent = new Intent(context, InformationMessageService.class);
                    intent.putExtra("PowerMessage", rawMessage);
                    context.startService(intent);
                }

            } catch (ClassNotFoundException e) {
                e.printStackTrace();
                Log.e("Error", e.getMessage());
            }

        } else {
            Log.e("deserializer", "Invalid message: " + message);
        }


//        Log.i("WebSocket", "got: " + message);
//        Intent intent = new Intent(context, MonitoringMessageService.class);
//        intent.putExtra("log", message);
//        context.startService(intent);
    }

    @Override
    public void onOpen(ServerHandshake handshake) {
        Log.i("WebSocket", "You are connected to ChatServer: " + getURI() + "\n");
        Intent intent = new Intent(context, MonitoringMessageService.class);
        intent.putExtra("log", "Start Service");
        context.startService(intent);
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
        send("speed=" + progress);
    }

}
