package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Race;

import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import ch.hsr.rapidtweakapp.helper.IVisitor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class RoundTimeMessage extends Message {

    long roundTime;
    Race race;

    @Override
    public void accept(IInformationVisitor visitor) {
        visitor.visit(this);
    }
}
