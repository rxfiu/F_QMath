package com.example.disegnofunzione;

public class Bounds {

    private float centerX;
    private float centerY;
    private float endX;
    private float endY;

    public float getCenterX() {
        return centerX;
    }

    public float getCenterY() {
        return centerY;
    }

    public float getEndX() {
        return endX;
    }

    public float getEndY() {
        return endY;
    }
    private Bounds() {

    }

    public Bounds(float centerX, float centerY, float endX, float endY) {
        this.centerX = centerX;
        this.centerY = centerY;
        this.endX = endX;
        this.endY = endY;
    }
}
