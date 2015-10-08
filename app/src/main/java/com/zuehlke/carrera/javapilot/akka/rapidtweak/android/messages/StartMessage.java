package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import ch.hsr.rapidtweakapp.helper.IVisitor;

/**
 * Created by Markus on 05.10.2015.
 */
public class StartMessage extends Message {
    @Override
    public void accept(IInformationVisitor visitor) {
        visitor.visit(this);
    }
}
