package com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages;

import java.io.Serializable;

import ch.hsr.rapidtweakapp.helper.IInformationVisitee;
import ch.hsr.rapidtweakapp.helper.IInformationVisitor;
import ch.hsr.rapidtweakapp.helper.IVisitee;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public abstract class Message implements Serializable, IInformationVisitee {

}
