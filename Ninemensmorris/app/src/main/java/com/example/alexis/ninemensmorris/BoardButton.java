package com.example.alexis.ninemensmorris;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;

class BoardButton extends Point{
    private boolean isEmpty;
    private Paint paint;
    private float posx;
    private float posy;

    BoardButton(float posx, float posy)
    {
        this.posx = posx;
        this.posy = posy;
        this.isEmpty = true;
        this.paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        this.paint.setColor(Color.DKGRAY);
    }

    boolean isEmpty() {
        return isEmpty;
    }

    void setEmpty(boolean empty) {
        isEmpty = empty;
    }

    float getPosx() {
        return posx;
    }

    float getPosy() {
        return posy;
    }

    Paint getPaint() {
        return paint;
    }

    void setPosition(float x, float y) {
        this.posx = x;
        this.posy = y;
    }
}
