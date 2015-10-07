package com.zuehlke.carrera.javapilot.akka.rapidtweak.track;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

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

    protected Multimap<Integer, Double> speeds = ArrayListMultimap.create();
    private final static String ELEMENT_NAME = "SpeedMeasure ";
    private static int elementCounter = 1;
    private double speedLimit;
    private String id;

    @Override
    public String getTrackName() {
        return ELEMENT_NAME + elementCounter++;
    }

    public Double getAveragePosition(int power) {

        return getAverageOfListDouble(speeds.get(power));
    }

    @Override
    public void accept(IVisitor visitor) {
        visitor.visitSpeed(this);
    }
}