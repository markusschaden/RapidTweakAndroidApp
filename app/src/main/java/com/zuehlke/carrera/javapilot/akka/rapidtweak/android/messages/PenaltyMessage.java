package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class PenaltyMessage extends Message {

    private String sourceId;

    public PenaltyMessage(String barrier) {
        this.sourceId = barrier;
    }

    @Override
    public void accept(IInformationVisitor visitor) {
        visitor.visit(this);
    }
}
