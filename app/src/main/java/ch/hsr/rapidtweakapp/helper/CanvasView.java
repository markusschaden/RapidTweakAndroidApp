package ch.hsr.rapidtweakapp.helper;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.zuehlke.carrera.javapilot.akka.rapidtweak.android.messages.Coordinate;

import java.util.List;

import ch.hsr.rapidtweakapp.Application;
import ch.hsr.rapidtweakapp.R;

public class CanvasView extends View {

    public int width;
    public int height;
    private Bitmap mBitmap;
    private Canvas mCanvas;
    private Path mPath;
    Context context;
    private Paint borderPaint, streetPaint, linePaint;
    private float mX, mY;
    private static final float TOLERANCE = 5;
    private List<Coordinate> raceCoordinantes;
    private int centerX, centerY;
    private int scaleFactor = 55;

    public CanvasView(Context c, AttributeSet attrs) {
        super(c, attrs);
        context = c;

        // we set a new Path
        mPath = new Path();

        // and we set a new Paint with the desired attributes
        borderPaint = new Paint();
        borderPaint.setAntiAlias(true);
        borderPaint.setColor(Color.RED);
        borderPaint.setStyle(Paint.Style.STROKE);
        borderPaint.setStrokeWidth(16f);

        streetPaint = new Paint();
        streetPaint.setAntiAlias(true);
        streetPaint.setColor(Color.BLACK);
        streetPaint.setStyle(Paint.Style.STROKE);
        streetPaint.setStrokeWidth(12f);

        linePaint = new Paint();
        linePaint.setAntiAlias(true);
        linePaint.setColor(Color.YELLOW);
        linePaint.setStyle(Paint.Style.STROKE);
        linePaint.setStrokeWidth(1f);


    }

    // override onSizeChanged
    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        // your Canvas will draw onto the defined Bitmap
        mBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);
        mCanvas = new Canvas(mBitmap);
    }

    // override onDraw
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        // draw the mPath with the mPaint on the canvas when onDraw

        centerX = mCanvas.getWidth() / 2;
        centerY = mCanvas.getHeight() / 2;



        if(raceCoordinantes != null) {
            //Log.d("OnDraw", "RaceCoordinates got Data");
            mPath.moveTo(centerX, centerY);
            for(Coordinate c : raceCoordinantes) {
                mPath.lineTo(centerX + ((float)c.getX() * scaleFactor), centerY + ((float)c.getY() * scaleFactor));
            }
        } else {
            //Log.d("OnDraw", "RaceCoordinates empty");
        }
        canvas.drawPath(mPath, borderPaint);
        canvas.drawPath(mPath, streetPaint);
        canvas.drawPath(mPath, linePaint);
    }

    // when ACTION_DOWN start touch according to the x,y values
    private void startTouch(float x, float y) {
        mPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    // when ACTION_MOVE move touch according to the x,y values
    private void moveTouch(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOLERANCE || dy >= TOLERANCE) {
            mPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    public void clearCanvas() {
        mPath.reset();
        invalidate();
    }

    // when ACTION_UP stop touch
    private void upTouch() {
        mPath.lineTo(mX, mY);
    }

    //override the onTouchEvent
    /*@Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                moveTouch(x, y);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                upTouch();
                invalidate();
                break;
        }
        return true;
    }*/


    public void setRaceCoordinates(List<Coordinate> raceDrawer) {
        mPath.reset();
        invalidate();
        raceCoordinantes = raceDrawer;
        invalidate();
    }
}