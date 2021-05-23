package com.example.disegnofunzione;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;

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

    public Paint getIndexesPaint() {
        return indexesPaint;
    }

    private Paint indexesPaint;
    private float indexesPaintTextSize = 35;


    public Paint getFunctionPaint() {
        return functionPaint;
    }

    private Paint functionPaint;
    private float functionPaintWidth = 3;

    private Canvas canvas;

    private Bounds bounds;

    private float scale = 150;

    public void setFunctions(List<FunctionToDraw> functions) {
        this.functions = functions;
    }

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

    public Graph(Canvas canvas, List<FunctionToDraw> functions) {
        init(canvas, functions);
    }

    private void init(Canvas canvas, List<FunctionToDraw> functions) {
        this.canvas = canvas;
        setFunctions(functions);
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

        indexesPaint = new Paint();
        arrowsPaint.setColor(Color.BLACK);
        indexesPaint.setStyle(Paint.Style.FILL);
        indexesPaint.setTextSize(indexesPaintTextSize);

        functionPaint = new Paint();
        functionPaint.setStrokeWidth(3);
        functionPaint.setColor(Color.BLACK);
        functionPaint.setStyle(Paint.Style.STROKE);
        functionPaint.setAntiAlias(true);
        functionPaint.setDither(true);
    }
    public void reDrawAll() {
        eraseAll();
        drawCompleteAxes();
        drawFunctions();
    }

    public void drawAll() {
        drawCompleteAxes();
        drawFunctions();
    }

    public void eraseAll() {
        Canvas canvas = getCanvas();
        canvas.drawColor(Color.WHITE);
    }

    private void drawCompleteAxes() {
        drawAxes();
        drawArrows();
        drawScale();
        drawIndexes();
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

    private void drawIndexes() {
        Canvas canvas = getCanvas();
        Paint paint = getIndexesPaint();
        Bounds bounds = getBounds();
        float scale = getScale();

        int textDistance = 4;
        String index;
        Rect rect;
        for(float i = 0; i < bounds.getEndX(); i ++)
        {
            if((i - bounds.getCenterX()) % scale == 0) {
                index = Integer.toString((int)(Math.round(i - bounds.getCenterX()) / scale));
                rect = new Rect();
                paint.getTextBounds(index, 0, index.length(), rect);
                canvas.drawText(index, i - rect.centerX(), bounds.getCenterY() - textDistance*rect.centerY(), paint);
            }
        }
        textDistance += 1;
        for(float i = 0; i < bounds.getEndY(); i ++)
        {
            if(((i - bounds.getCenterY()) % scale == 0) && i - bounds.getCenterY() != 0) {
                index = Integer.toString((int)(Math.round(-(i - bounds.getCenterY())) / scale));
                rect = new Rect();
                paint.getTextBounds(index, 0, index.length(), rect);
                canvas.drawText(index, bounds.getCenterX() - textDistance*rect.centerX(), i - rect.centerY(), paint);
            }
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
        functionPaint = new Paint(functionPaint);
        functionPaint.setStrokeWidth(functionPaint.getStrokeWidth()+1);
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