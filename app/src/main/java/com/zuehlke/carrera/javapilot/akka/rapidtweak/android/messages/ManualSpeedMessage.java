package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import ch.hsr.rapidtweakapp.helper.IInformationVisitee;
import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import ch.hsr.rapidtweakapp.helper.IVisitor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class ManualSpeedMessage extends Message {

    int speed;

    @Override
    public void accept(IInformationVisitor visitor) {
        visitor.visit(this);
    }
}
