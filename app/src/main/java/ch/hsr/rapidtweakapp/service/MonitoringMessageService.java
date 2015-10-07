package ch.hsr.rapidtweakapp.service;

import android.app.IntentService;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.MonitoringMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Element;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.LeftCurveTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.RightCurveTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.SpeedMeasureTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.StraightTrackElement;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.domain.TrackElements;
import ch.hsr.rapidtweakapp.helper.IVisitor;

public class MonitoringMessageService extends IntentService implements IVisitor{
    private TrackElements race;

    public MonitoringMessageService(){
        super("MonitoringMessageService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Bundle extras = intent.getExtras();
        Element element;
        race = ((Application)this.getApplication()).getRace();
        if(extras.getSerializable("MonitoringMessage") != null) {
            MonitoringMessage message = (MonitoringMessage) (extras.getSerializable("MonitoringMessage"));
            try {
                element = message.getElement();
            }
            catch (NullPointerException e) {
                Log.e("MonitoringMessage", "No Element in Message");
                return;
            }
            element.accept(this);
        }
    }


    @Override
    public void visitStraight(StraightTrackElement element) {
        race.add(element);
        Log.i("TrackElementAdded", element.getElementName());
    }

    @Override
    public void visitLeft(LeftCurveTrackElement element) {
        race.add(element);
        Log.i("TrackElementAdded", element.getElementName());
    }

    @Override
    public void visitRight(RightCurveTrackElement element) {
        race.add(element);
        Log.i("TrackElementAdded", element.getElementName());
    }

    @Override
    public void visitSpeed(SpeedMeasureTrackElement element) {
        Log.i("TrackElementAdded", element.getElementName());
    }
}
