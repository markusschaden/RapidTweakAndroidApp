package com.zuehlke.carrera.javapilot.akka.rapidtweak.track;

import java.io.Serializable;

import lombok.Data;
import lombok.ToString;

/**
 * Created by Markus on 25.09.2015.
 */
@Data
@ToString
public class Duration implements Serializable {

    int power;
    long time;

    public Duration(int power, long time) {
        this.power = power;
        this.time = time;
    }
}
