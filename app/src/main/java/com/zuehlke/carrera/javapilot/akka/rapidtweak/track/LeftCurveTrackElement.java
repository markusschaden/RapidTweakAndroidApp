package com.zuehlke.carrera.javapilot.akka.rapidtweak.track;

import ch.hsr.rapidtweakapp.helper.IVisitor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Markus on 25.09.2015.
 */
@Data
@ToString(callSuper = true)
public class LeftCurveTrackElement extends TrackElement {

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitLeft(this);
    }
}
