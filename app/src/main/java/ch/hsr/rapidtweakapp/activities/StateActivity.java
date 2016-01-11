package ch.hsr.rapidtweakapp.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.PenaltyMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.state.StateType;

import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.helper.RaceChange;
import ch.hsr.rapidtweakapp.helper.RaceInformation;

public class StateActivity extends Main {

    StateType stateBefore = StateType.UNKNOWN;
    StateType currentState = StateType.UNKNOWN;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle(getString(R.string.state));
        setContentView(R.layout.activity_state);

        IntentFilter filter = new IntentFilter();
        filter.addAction("stateInformation");
        filter.addAction("penaltyInformation");
        LocalBroadcastManager.getInstance(this).registerReceiver(mMessageReceiver, filter);
    }

    private void changeState(StateType changeToState){
        if(changeToState != null){
            stateBefore = currentState;
            ((TextView) findViewById(R.id.tv_stateBefore)).setText(stateBefore.toString());
            currentState = changeToState;
            ((TextView) findViewById(R.id.tv_currentState)).setText(currentState.toString());
        }
    }

    private BroadcastReceiver mMessageReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            //Log.i("Broadcast", "State Change Received");
            if(intent.getSerializableExtra("state") != null) {
                StateType state = (StateType) intent.getSerializableExtra("state");
                if(state != null) {
                    changeState(state);
                }
            } else if (intent.getSerializableExtra("penalty") != null) {
                PenaltyMessage penalty = (PenaltyMessage)intent.getSerializableExtra("penalty");
                Toast.makeText(StateActivity.this, "Penalty occurred on Barrier: " + penalty.getSourceId(), Toast.LENGTH_LONG).show();
            }
        }
    };
}
