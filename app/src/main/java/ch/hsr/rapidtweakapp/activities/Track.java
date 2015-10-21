package ch.hsr.rapidtweakapp.activities;

import android.graphics.Canvas;
import android.os.Bundle;
import android.view.Surface;
import android.view.SurfaceView;
import android.view.View;

import ch.hsr.rapidtweakapp.R;
import ch.hsr.rapidtweakapp.helper.CanvasView;

/**
 * Created by Noah on 30.09.2015.
 */
public class Track extends Main {

    private CanvasView customCanvas;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        super.setActivityTitle(getString(R.string.track));
        setContentView(R.layout.activity_track);

        customCanvas = (CanvasView) findViewById(R.id.signature_canvas);
    }

    public void clearCanvas(View v) {
        customCanvas.clearCanvas();
    }


}
