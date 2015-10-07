package com.zuehlke.carrera.javapilot.akka.rapidtweak.track;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;

import ch.hsr.rapidtweakapp.helper.IVisitee;
import ch.hsr.rapidtweakapp.helper.IVisitor;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Collection;
import java.util.Observable;

@Data
@ToString(callSuper = true)
public abstract class Element extends Observable implements Serializable, IVisitee{

    protected Multimap<Integer, Long> positions = ArrayListMultimap.create();
    private String elementName;

    public abstract String getTrackName();
    public abstract void accept(IVisitor visitor);

    public Double getAveragePosition(int power) {

        return getAverageOfList(positions.get(power));
    }

    protected Double getAverageOfList(Collection<Long> values) {

        Double result = 0d;
        if (values.size() == 0) {
            return result;
        }

        int count = 0;
        for (Long d : values) {
            result += d;
            count++;
        }

        return result / count;
    }

    protected Double getAverageOfListDouble(Collection<Double> values) {

        Double result = 0d;
        if (values.size() == 0) {
            return result;
        }

        int count = 0;
        for (Double d : values) {
            result += d;
            count++;
        }

        return result / count;
    }


}
