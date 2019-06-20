package com.example.ex4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;

public class JoystickView extends SurfaceView implements SurfaceHolder.Callback, View.OnTouchListener {

    private float centerX;
    private float centerY;
    private float baseRadius;
    private float hasRadius;
    private JoystickListener joystickCallback;
    private final int ratio = 5;

    private void setupDimentions() {
        centerX = getWidth() / 2;
        centerY = getHeight() / 2;
        baseRadius = Math.min(getWidth(), getHeight()) / 3;
        hasRadius = Math.min(getWidth(), getHeight()) / 5;
    }

    public JoystickView(Context context) {
        super(context);
        getHolder().addCallback(this);
        setOnTouchListener(this);
        if (context instanceof JoystickListener){
            joystickCallback = (JoystickListener)context;
        }
    }

    public JoystickView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    public JoystickView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        getHolder().addCallback(this);
        setOnTouchListener(this);
    }

    private void drawJoystick(float newX, float newY) {
        if (getHolder().getSurface().isValid()) {
            Canvas surfaceCanvas = this.getHolder().lockCanvas();
            Paint color = new Paint();
            surfaceCanvas.drawColor(Color.MAGENTA, PorterDuff.Mode.CLEAR);
            color.setARGB(255, 50, 50, 50);
            surfaceCanvas.drawCircle(centerX, centerY, baseRadius, color);
            color.setARGB(255, 0, 0, 255);
            surfaceCanvas.drawCircle(newX, newY, hasRadius, color);
            getHolder().unlockCanvasAndPost(surfaceCanvas);
        }
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        setupDimentions();
        drawJoystick(centerX, centerY);
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {

    }

    public boolean onTouch(View v, MotionEvent e) {
        if (v.equals(this)) {
            //calculate the place of the Joystick
            if (e.getAction() != e.ACTION_UP) {
                float displacement = (float) Math.sqrt((Math.pow(e.getX() - centerX, 2)) + Math.pow(e.getY() - centerY, 2));
                if (displacement < baseRadius){
                    drawJoystick(e.getX(),e.getY());
                    joystickCallback.onJoystickMoved((e.getX()-centerX)/baseRadius, (e.getY()-centerY)/baseRadius,getId());

                }else {
                    float ratio = baseRadius / displacement;
                    float constrainedX = centerX + (e.getX()-centerX) * ratio;
                    float constrainedY = centerY + (e.getY()-centerY) * ratio;
                    drawJoystick(constrainedX, constrainedY);
                    joystickCallback.onJoystickMoved((constrainedX-centerX)/baseRadius, (constrainedY-centerY)/baseRadius,getId());

                }

            } else {
                drawJoystick(centerX, centerY);
                joystickCallback.onJoystickMoved(0,0,getId());
            }
        }
        return true;
    }

    @Override
    public void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w,h,oldw,oldh);
        setNewScreen();
    }

    public void setNewScreen() {
        setupDimentions();
        //    this.invalidate();
        drawJoystick(centerX,centerY);
    }
}
