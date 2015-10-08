package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.track.Element;

import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class MonitoringMessage extends Message {

    private Element element;

    public MonitoringMessage() {
    }

    public MonitoringMessage(Element element) {
        this.element = element;
    }

    @Override
    public void accept(IInformationVisitor visitor) {

    }
}
