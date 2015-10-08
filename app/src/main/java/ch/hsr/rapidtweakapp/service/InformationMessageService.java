package ch.hsr.rapidtweakapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.util.Log;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.ManualSpeedMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.Message;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RoundTimeMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.StartMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.StopMessage;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.domain.Race;
import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import ch.hsr.rapidtweakapp.helper.RaceInformation;

/**
 * Created by Noah on 08.10.2015.
 */
public class InformationMessageService extends IntentService implements IInformationVisitor {

    public InformationMessageService(){
        super("MonitoringMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Message message;
        if(extras.getSerializable("InformationMessage") != null) {
            message = (Message)extras.getSerializable("InformationMessage");
            message.accept(this);
        }

    }

    @Override
    public void visit(StartMessage elementClass) {
        Race race = ((Application)this.getApplication()).getRace();
        race.startRace();
        Log.i("InformationService", "Race Restarted");
        sendMessage(RaceInformation.RACE_START);
    }

    @Override
    public void visit(StopMessage elementClass) {

    }

    @Override
    public void visit(RoundTimeMessage elementClass) {
        Race race = ((Application)this.getApplication()).getRace();
        race.addRoundtime(elementClass.getRoundTime());
        sendMessage(RaceInformation.ROUNDTIME);
    }

    @Override
    public void visit(ManualSpeedMessage elementClass) {

    }

    private void sendMessage(RaceInformation information) {
        Intent intent = new Intent("raceInformation");
        intent.putExtra("raceInformation", information);
        LocalBroadcastManager.getInstance(this).sendBroadcast(intent);
    }
}
