package ch.hsr.rapidtweakapp.helper;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.LeftCurveTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.RightCurveTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Speed;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.SpeedMeasureTrackElement;
import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.StraightTrackElement;

/**
 * Created by Noah on 07.10.2015.
 */
public interface IVisitor {
    public void visitStraight(StraightTrackElement elementClass);
    public void visitLeft(LeftCurveTrackElement elementClass);
    public void visitRight(RightCurveTrackElement elementClass);
    public void visitSpeed(SpeedMeasureTrackElement elementClass);

}
