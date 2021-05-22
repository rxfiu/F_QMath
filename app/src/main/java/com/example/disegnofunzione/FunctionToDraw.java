package com.example.disegnofunzione;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.FileUriExposedException;

import java.util.Objects;

public class FunctionToDraw {
    public int getColor() {
        return color;
    }

    private Graph getGraph() {
        return graph;
    }

    private float getScale() {
        return getGraph().getScale();
    }

    private Bounds getBounds() {
        return getGraph().getBounds();
    }

    private Canvas getCanvas() {
        return getGraph().getCanvas();
    }

    @Override
    public String toString() {
        return getFunction().toString();
    }

    private Function getFunction() {
        return function;
    }
    private Graph graph;
    private int color;
    private Function function;

    private FunctionToDraw() {

    }
    public FunctionToDraw(Graph graph, Function function, int color) {
        this.graph = graph;
        this.function = function;
        this.color = color;
    }
    public FunctionToDraw(Graph graph, String fx , int color) {
        this.graph = graph;
        this.function = new Function(fx);
        this.color = color;
    }
    private float f(float x) {
        Bounds bounds = getBounds();
        float scale = getScale();
        Function function = getFunction();
        x -= bounds.getCenterX();
        double res = function.Evaluate(x / scale);
        res = -res * scale + bounds.getCenterY();

        return (float)(res);
    }
    private Path drawPath() {
        Bounds bounds = getBounds();
        Path path = new Path();
        boolean move = true;
        for(float i = 0; i < bounds.getEndX(); i++) {
            float x = i;
            try {
                float y = f(x);
                if(move) {
                    path.moveTo(x, y);
                    move = false;
                }
                else {
                    path.lineTo(x, y);
                }
            }
            catch (Exception e) {
                move = true;
            }
        }
        return path;
    }
    private void drawFunction(Canvas canvas, Paint paint, int color) {
        // Canvas canvas = getCanvas();
        paint.setColor(color);

        Path path = drawPath();

        canvas.drawPath(path, paint);
    }
    public void drawFunction(Canvas canvas, Paint paint) {
        drawFunction(canvas,paint,this.color);
    }
    public void eraseFunction(Canvas canvas, Paint paint) {
        drawFunction(canvas,paint,Color.WHITE);
    }
}
