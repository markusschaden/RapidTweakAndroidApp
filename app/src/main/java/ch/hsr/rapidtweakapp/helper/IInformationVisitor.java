package ch.hsr.rapidtweakapp.helper;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.ManualSpeedMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.PenaltyMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.PowerMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RaceDrawerMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RacePositionMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.RoundTimeMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.StartMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.StateMessage;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.StopMessage;

/**
 * Created by Noah on 08.10.2015.
 */
public interface IInformationVisitor {
    void visit(StartMessage elementClass);
    void visit(StopMessage elementClass);
    void visit(RoundTimeMessage elementClass);
    void visit(ManualSpeedMessage elementClass);
    void visit(PenaltyMessage penaltyMessage);
    void visit(PowerMessage powerMessage);
    void visit(RaceDrawerMessage raceDrawerMessage);
    void visit(RacePositionMessage racePositionMessage);
    void visit(StateMessage stateMessage);
}
