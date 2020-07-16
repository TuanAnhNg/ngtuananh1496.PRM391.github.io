package com.example.minesweeper.model;

import android.content.Context;
import android.util.AttributeSet;

public class GameButton extends androidx.appcompat.widget.AppCompatImageButton {

    private int coordinateX;
    private int coordinateY;
    private int value;
    private boolean isShowed = false;

    public GameButton(Context context) {
        super(context);
    }

    public GameButton(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
    }

    public int getCoordinateX() {
        return coordinateX;
    }

    public void setCoordinateX(int coordinateX) {
        this.coordinateX = coordinateX;
    }

    public int getCoordinateY() {
        return coordinateY;
    }

    public void setCoordinateY(int coordinateY) {
        this.coordinateY = coordinateY;
    }

    public int getValue() {
        return value;
    }

    public void changeStatus() {
        this.isShowed = true;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
