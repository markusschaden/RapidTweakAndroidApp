package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import lombok.Data;
import lombok.ToString;

/**
 * Created by Markus on 09.10.2015.
 */
@Data
@ToString
public class PowerMessage extends Message {
    int power;

    public PowerMessage(int power) {
        this.power = power;
    }

    @Override
    public void accept(IInformationVisitor visitor) {
        visitor.visit(this);
    }
}
