package com.zuehlke.carrera.javapilot.akka.rapidtweak.track;

import ch.hsr.rapidtweakapp.helper.IVisitee;
import ch.hsr.rapidtweakapp.helper.IVisitor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Markus on 25.09.2015.
 */
@Data
@ToString(callSuper = true)
public class LeftCurveTrackElement extends TrackElement implements IVisitee{

    private final static String ELEMENT_NAME = "Left curve ";
    //TODO: reset on new start
    private static int elementCounter = 1;

    @Override
    public String getTrackName() {
        return ELEMENT_NAME + elementCounter++;
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitLeft(this);
    }
}
