package com.example.disegnofunzione;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import java.io.CharArrayReader;
import java.util.List;
import java.util.concurrent.Callable;

public class Graph {
    private Paint axesPaint;
    private float axesPaintWidth = 1;

    private float scaleHeight = 12;

    private Paint arrowsPaint;
    private float arrowsPaintWidth = 1.5f;
    private float arrowsHeight = 12;

    public Paint getFunctionPaint() {
        return functionPaint;
    }

    private Paint functionPaint;
    private float functionPaintWidth = 3;

    private Canvas canvas;

    private Bounds bounds;

    private float scale = 100;

    private List<FunctionToDraw> functions;

    public Paint getAxesPaint() {
        return axesPaint;
    }

    public float getAxesPaintWidth() {
        return axesPaintWidth;
    }

    public float getScaleHeight() {
        return scaleHeight;
    }

    public Paint getArrowsPaint() {
        return arrowsPaint;
    }

    public float getArrowsPaintWidth() {
        return arrowsPaintWidth;
    }

    public float getArrowsHeight() {
        return arrowsHeight;
    }

    public List<FunctionToDraw> getFunctions() {
        return functions;
    }

    Bounds getBounds() {
        return bounds;
    }

    float getScale() {
        return scale;
    }

    Canvas getCanvas() {
        return canvas;
    }

    private Graph() {

    }

    public Graph(Canvas canvas) {
        init(canvas);
    }

    private void init(Canvas canvas) {
        this.canvas = canvas;
        setBounds();
        setPaint();

        drawCompleteAxes();
    }

    private void setBounds() {
        Canvas canvas = getCanvas();
        float centerX = canvas.getClipBounds().centerX();
        float centerY = canvas.getClipBounds().centerY();
        float endX = canvas.getClipBounds().right;
        float endY = canvas.getClipBounds().bottom;

        bounds = new Bounds(centerX, centerY, endX, endY);
    }

    private void setPaint() {
        axesPaint = new Paint();
        axesPaint.setColor(Color.BLACK);
        axesPaint.setStrokeWidth(axesPaintWidth);

        arrowsPaint = new Paint();
        arrowsPaint.setColor(Color.BLACK);
        arrowsPaint.setStrokeWidth(arrowsPaintWidth);

        functionPaint = new Paint();
        functionPaint.setStrokeWidth(3);
        functionPaint.setColor(Color.BLACK);
        functionPaint.setStyle(Paint.Style.STROKE);
        //functionPaint.setAntiAlias(true);
        functionPaint.setDither(true);

    }
    public void drawAll() {
        drawCompleteAxes();
        drawFunctions();

    }

    private void drawCompleteAxes() {
        drawAxes();
        drawArrows();
        drawScale();
    }

    private void drawAxes() {
        Canvas canvas = getCanvas();
        Paint paint = getAxesPaint();
        Bounds bounds = getBounds();
        canvas.drawLine(bounds.getCenterX(), 0, bounds.getCenterX(), bounds.getEndY(), paint);
        canvas.drawLine(0, bounds.getCenterY(), bounds.getEndX(), bounds.getCenterY(), paint);
    }

    private void drawArrows() {
        Canvas canvas = getCanvas();
        Paint paint = getArrowsPaint();
        drawArrowDX();
        drawArrowUP();
    }

    private void drawArrowUP() {
        Canvas canvas = getCanvas();
        Paint paint = getArrowsPaint();
        Bounds bounds = getBounds();
        float arrowsHeight = getArrowsHeight();
        canvas.drawLine(bounds.getCenterX(), 0,
                bounds.getCenterX() + arrowsHeight, 0 + arrowsHeight,
                paint);
        canvas.drawLine(bounds.getCenterX(), 0,
                bounds.getCenterX() - arrowsHeight, 0 + arrowsHeight,
                paint);
    }

    private void drawArrowDX() {
        Canvas canvas = getCanvas();
        Paint paint = getArrowsPaint();
        Bounds bounds = getBounds();
        float arrowsHeight = getArrowsHeight();
        canvas.drawLine(
                bounds.getEndX(), bounds.getCenterY(),
                bounds.getEndX() - arrowsHeight, bounds.getCenterY() - arrowsHeight,
                paint);
        canvas.drawLine(bounds.getEndX(), bounds.getCenterY(),
                bounds.getEndX() - arrowsHeight, bounds.getCenterY() + arrowsHeight,
                paint);
    }

    private void drawScale() {
        Canvas canvas = getCanvas();
        Paint paint = getAxesPaint();
        float scaleHeight = getScaleHeight();
        float scale = getScale();

        Bounds bounds = getBounds();
        float scaleChanged = scale;

        boolean condition = true;
        while (condition) {
            canvas.drawLine(bounds.getCenterX() + scaleChanged, bounds.getCenterY() + scaleHeight, bounds.getCenterX() + scaleChanged, bounds.getCenterY() - scaleHeight, paint);
            scaleChanged += scale;
            condition = (bounds.getCenterX() + scaleChanged) < bounds.getEndX();
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(bounds.getCenterX() - scaleChanged, bounds.getCenterY() + scaleHeight, bounds.getCenterX() - scaleChanged, bounds.getCenterY() - scaleHeight, paint);
            scaleChanged += scale;
            condition = (bounds.getCenterX() - scaleChanged) > 0;
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(bounds.getCenterX() + scaleHeight, bounds.getCenterY() + scaleChanged, bounds.getCenterX() - scaleHeight, bounds.getCenterY() + scaleChanged, paint);
            scaleChanged += scale;
            condition = (bounds.getCenterY() + scaleChanged) < bounds.getEndY();
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(bounds.getCenterX() + scaleHeight, bounds.getCenterY() - scaleChanged, bounds.getCenterX() - scaleHeight, bounds.getCenterY() - scaleChanged, paint);
            scaleChanged += scale;
            condition = (bounds.getCenterY() - scaleChanged) > 0;
        }
    }

    private void drawFunctions() {
        Canvas canvas = getCanvas();
        Paint functionPaint = getFunctionPaint();
        List<FunctionToDraw> functions = getFunctions();
        for (FunctionToDraw functionToDraw : functions) {
           drawFunction(functionToDraw);
        }
    }

    private void eraseFunctions() {
        List<FunctionToDraw> functions = getFunctions();
        for (FunctionToDraw functionToDraw : functions) {
            eraseFunction(functionToDraw);
        }
    }

    private void drawFunction(FunctionToDraw functionToDraw) {
        Canvas canvas = getCanvas();
        Paint functionPaint = getFunctionPaint();
        functionToDraw.drawFunction(canvas, functionPaint);
    }

    private void eraseFunction(FunctionToDraw functionToDraw) {
        Canvas canvas = getCanvas();
        Paint functionPaint = getFunctionPaint();
        functionToDraw.eraseFunction(canvas, functionPaint);
    }

    public void addFunction(String fx) {
        FunctionToDraw functionToDraw = new FunctionToDraw(this, fx, Color.BLUE);
        drawFunction(functionToDraw);
        //functions.add(functionToDraw);
        //drawFunction(functions.get(0));
    }
    public void deleteFunction(String fx) {
        FunctionToDraw functionToDraw = new FunctionToDraw(this, fx, Color.WHITE);
        eraseFunction(functionToDraw);
        //functions.add(functionToDraw);
        //drawFunction(functions.get(0));
    }
}