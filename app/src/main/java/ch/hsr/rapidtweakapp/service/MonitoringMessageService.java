package ch.hsr.rapidtweakapp.service;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.google.common.util.concurrent.Monitor;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.MonitoringMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.TrackElement;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.domain.TrackElements;

public class MonitoringMessageService extends IntentService {

    public MonitoringMessageService(){
        super("MonitoringMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        MonitoringMessage message;
        TrackElements race = ((Application)this.getApplication()).getRace();
        if(extras != null) {
            message = (MonitoringMessage)extras.get("MonitoringMessage");
            race.add((TrackElement) message.getElement());
        }
        Log.i("RaceElements", race.toString());
    }
}
