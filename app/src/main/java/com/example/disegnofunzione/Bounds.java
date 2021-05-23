package com.example.disegnofunzione;

public class Bounds {

    private float offset = 0.01f;

    public void setCenterX(float centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(float centerY) {
        this.centerY = centerY;
    }

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

//    public float getOffsetX() {
//        return offsetX;
//    }

    public void setOffsetX(float offsetX) {
        setCenterX(getCenterX() - Math.round(offsetX*offset));
    }

//    public float getOffsetY() {
//        return offsetY;
//    }

    public void setOffsetY(float offsetY) {
        setCenterY(getCenterY() - Math.round(offsetY*offset));
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
        this.centerX = centerX -100;
        this.centerY = centerY -100;
        this.endX = endX;
        this.endY = endY;
    }
}
