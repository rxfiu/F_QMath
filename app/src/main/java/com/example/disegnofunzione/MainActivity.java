package com.example.disegnofunzione;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.os.Bundle;
import android.text.TextPaint;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import java.util.ResourceBundle;

public class MainActivity extends AppCompatActivity {
    float centerX, centerY, endX, endY;
    Bitmap bitmap;
    Function mathFunction;
    float offset = 10;
    float scale = 100f, scaleX = 1, scaleY = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void onButtonTestClick(View v)
    {
        ImageView img = findViewById(R.id.imageView);
        int width = img.getWidth();
        int height = img.getHeight();

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        img.setImageBitmap(bitmap);

        Canvas canvas = new Canvas(bitmap);

        /*disegna grafico vuoto */
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);

        endX = canvas.getClipBounds().right;
        endY = canvas.getClipBounds().bottom;
        centerX = canvas.getClipBounds().centerX();
        centerY = canvas.getClipBounds().centerY();

        canvas.drawLine(centerX, 0, centerX, endY, paint);
        canvas.drawLine(0, centerY, endX, centerY, paint);
        //frecce

        drawArrows(canvas);
        drawScale(canvas, scale);
        drawIndexes(canvas, scale);


        /*prepara funzione*/
        EditText funcTxt = findViewById(R.id.FunctionText);
        String funzione = funcTxt.getText().toString();
        try {
            mathFunction = new Function(funzione);
        }
        catch (Exception e) {
            mathFunction = new Function("0");
        }

        /*disegna*/
        disegnaFunzione(bitmap);
    }


    private void drawArrows(Canvas canvas) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1.5f);

        int d = 12;
        //arrow dx
        canvas.drawLine(endX, centerY, endX - d, centerY - d, paint);
        canvas.drawLine(endX, centerY, endX - d, centerY + d, paint);
        //arrow up
        canvas.drawLine(centerX, 0, centerX + d, 0 + d, paint);
        canvas.drawLine(centerX, 0, centerX - d, 0 + d, paint);
    }

    private void drawScale(Canvas canvas, float scale) {
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);

        float height = 12;
        float scaleChanged = scale;

        boolean condition = true;
        while (condition) {
            canvas.drawLine(centerX + scaleChanged, centerY + height, centerX + scaleChanged, centerY - height, paint);
            scaleChanged += scale;
            condition = (centerX + scaleChanged) < endX;
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(centerX - scaleChanged, centerY + height, centerX - scaleChanged, centerY - height, paint);
            scaleChanged += scale;
            condition = (centerX - scaleChanged) > 0;
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(centerX + height, centerY + scaleChanged, centerX - height, centerY + scaleChanged, paint);
            scaleChanged += scale;
            condition = (centerY + scaleChanged) < endY;
        }
        scaleChanged = scale;
        condition = true;
        while (condition) {
            canvas.drawLine(centerX + height, centerY - scaleChanged, centerX - height, centerY - scaleChanged, paint);
            scaleChanged += scale;
            condition = (centerY - scaleChanged) > 0;
        }
    }

    private void drawIndexes(Canvas canvas, float scale) {
        //Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/Inconsolata.ttf");

        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.FILL);
        paint.setTextSize(35);
        //paint.setTypeface(typeface);

        int textDistance = 4;

        for(float i = 0; i < endX; i ++)
        {
            if((i - centerX) % scale == 0) {
                String _text = Integer.toString((int)(Math.round(i - centerX) / scale));
                Rect _rect = new Rect();
                paint.getTextBounds(_text, 0, _text.length(), _rect);
                canvas.drawText(_text, i - _rect.centerX(), centerY - textDistance*_rect.centerY(), paint);
            }
        }
        textDistance += 1;
        for(float i = 0; i < endY; i ++)
        {
            if(((i - centerY) % scale == 0) && i-centerY != 0) {
                String _text = Integer.toString((int)(Math.round(-(i - centerY)) / scale));
                Rect _rect = new Rect();
                paint.getTextBounds(_text, 0, _text.length(), _rect);
                canvas.drawText(_text, centerX - textDistance*_rect.centerX(), i - _rect.centerY(), paint);
            }
        }
    }
    private void disegnaFunzione(Bitmap bitmap)
    {
        Canvas canvas = new Canvas(bitmap);

        Paint paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setAntiAlias(true);
        paint.setDither(true);

        Path path = new Path();

        boolean move = true;
        for(float i = 0; i < endX; i++)
        {
            float x = i;
            try
            {
                float y = funzioneMatematica(x);
                if(move)
                {
                    path.moveTo(x, y);
                    move = false;
                }
                else
                {
                    path.lineTo(x, y);
                }
            }
            catch (Exception e)
            {
                move = true;
            }



        }
        canvas.drawPath(path, paint);
    }

    private void resetBitmap()
    {
        ImageView img = findViewById(R.id.imageView);
        int width = img.getWidth();
        int height = img.getHeight();

        bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        img.setImageBitmap(bitmap);

        Canvas canvas = new Canvas(bitmap);

        /*Disegna assi*/
        Paint paint = new Paint();
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);

        canvas.drawLine(centerX, 0, centerX, endY, paint);
        canvas.drawLine(0, centerY, endX, centerY, paint);

        drawArrows(canvas);
        drawScale(canvas, scale);
        /*Disegna scala*/
        drawIndexes(canvas, scale);

    }

    private float funzioneMatematica(float x)
    {
        x -= centerX;
        double res = mathFunction.Evaluate(x / scale);
        res = -res * scale + centerY;

        return (float)(res);
    }


    public void spostaDestra(View v)
    {
        centerX += offset;

        resetBitmap();
        disegnaFunzione(bitmap);
    }
    public void spostaSinistra(View v)
    {
        centerX -= offset;

        resetBitmap();
        disegnaFunzione(bitmap);
    }
    public void spostaSu(View v)
    {
        centerY -= offset;

        resetBitmap();
        disegnaFunzione(bitmap);
    }
    public void spostaGiu(View v)
    {
        centerY += offset;

        resetBitmap();
        disegnaFunzione(bitmap);
    }
    public void zoomInX(View v)
    {
        scaleX *= 2;

        resetBitmap();
        disegnaFunzione(bitmap);
    }
    public void zoomOutX(View v)
    {
        scaleX /= 2;

        resetBitmap();
        disegnaFunzione(bitmap);
    }
    public void zoomInY(View v)
    {
        scaleY *= 2;

        resetBitmap();
        disegnaFunzione(bitmap);
    }
    public void zoomOutY(View v)
    {
        scaleY /= 2;

        resetBitmap();
        disegnaFunzione(bitmap);
    }
}