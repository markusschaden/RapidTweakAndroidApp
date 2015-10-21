package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import lombok.Data;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Data
@ToString(callSuper = true)
public class RaceDrawerMessage extends Message {

    private List<Coordinate> track = new ArrayList<>();

    public RaceDrawerMessage(List<Double[]> track) {
        for(Double[] c : track) {
            this.track.add(new Coordinate(c));
        };
    }

    @Override
    public void accept(IInformationVisitor visitor) {
        visitor.visit(this);
    }
}
