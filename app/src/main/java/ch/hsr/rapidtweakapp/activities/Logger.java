package ch.hsr.rapidtweakapp.activities;

import android.os.Bundle;
import android.widget.TextView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import ch.hsr.rapidtweakapp.R;

/**
 * Created by Noah on 30.09.2015.
 */
public class Logger extends Main {
    private String FILENAME = "log";
    @Override
    protected void onCreate(Bundle savedInstance){
        super.onCreate(savedInstance);
        super.setActivityTitle(getString(R.string.logger));
        setContentView(R.layout.activity_logger);


    }

}
