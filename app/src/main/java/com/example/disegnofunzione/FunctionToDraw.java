package com.example.disegnofunzione;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.FileUriExposedException;

import java.util.Objects;

//analoga a Funztion, possiede parametri in più per poter disegnare su una superficie
public class FunctionToDraw {
    public int getColor() {
        return color;
    }
    private int color;

    private Canvas getCanvas() {
        return getGraph().getCanvas();
    }

    private float getScale() {
        return getGraph().getScale();
    }

    private Bounds getBounds() {
        return getGraph().getBounds();
    }

    private Graph getGraph() {
        return graph;
    }

    private Graph graph;


    private Function getFunction() {
        return function;
    }
    private Function function;

    @Override
    public String toString() {
        return getFunction().toString();
    }

    private FunctionToDraw() {

    }

    public FunctionToDraw(Graph graph, Function function, int color) {      //costruttore
        this.graph = graph;
        this.function = function;
        this.color = color;
    }

    public FunctionToDraw(Graph graph, String fx , int color) {     //costruttore
        this.graph = graph;
        this.function = new Function(fx);
        this.color = color;
    }

    private float f(float x) {      //calcola f(x), tenendo conto dei bordi del riquadro,
                                    // tenendo conto che il punto (0,0) è in alto a sinistra e
                                    // bisogna "spostarlo" nel vero centro
        Bounds bounds = getBounds();
        float scale = getScale();
        Function function = getFunction();
        x -= bounds.getCenterX();
        double res = function.Evaluate(x / scale);
        res = -res * scale + bounds.getCenterY();

        return (float)(res);
    }

    private Path drawPath() {       //crea un immagine vettoriale della funzione
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

    private void drawFunction(Canvas canvas, Paint paint, int color) {      //disegna se stessa su una
                                                                            // superficie, più
                                                                            // generico
        paint.setColor(color);

        Path path = drawPath();

        canvas.drawPath(path, paint);
    }

    public void drawFunction(Canvas canvas, Paint paint) {      //disegna se stessa su una
                                                                // superficie
        drawFunction(canvas,paint,this.color);
    }

    public void eraseFunction(Canvas canvas, Paint paint) {     //cancella se stessa da una
                                                                // superficie
        drawFunction(canvas,paint,Color.WHITE);
    }
}
