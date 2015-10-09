package com.zuehlke.carrera.javapilot.akka.rapidtweak.track;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import java.util.ArrayList;
import java.util.List;

import ch.hsr.rapidtweakapp.helper.IVisitee;
import ch.hsr.rapidtweakapp.helper.IVisitor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Markus on 25.09.2015.
 */
@Data
@ToString(callSuper = true)
public class SpeedMeasureTrackElement extends Element implements IVisitee{

    protected List<Double> speeds = new ArrayList<>();
    private double speedLimit;
    private double lastSpeed;
    private String sourceId;

    public Double getAveragePosition(int power) {

        return getAverageOfListDouble(speeds);
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitSpeed(this);
    }
}