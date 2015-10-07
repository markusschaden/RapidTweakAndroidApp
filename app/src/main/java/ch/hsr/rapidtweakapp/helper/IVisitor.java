package ch.hsr.rapidtweakapp.helper;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.LeftCurveTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.RightCurveTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.SpeedMeasureTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.StraightTrackElement;

/**
 * Created by Noah on 07.10.2015.
 */
public interface IVisitor {
    void visitStraight(StraightTrackElement elementClass);
    void visitLeft(LeftCurveTrackElement elementClass);
    void visitRight(RightCurveTrackElement elementClass);
    void visitSpeed(SpeedMeasureTrackElement elementClass);

}
