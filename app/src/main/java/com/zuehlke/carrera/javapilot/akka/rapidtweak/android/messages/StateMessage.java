package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.state.StateType;

import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class StateMessage extends Message {

    StateType state;

    public StateMessage(StateType state) {
        this.state = state;
    }
    @Override
    public void accept(IInformationVisitor visitor) {
        visitor.visit(this);
    }
}
