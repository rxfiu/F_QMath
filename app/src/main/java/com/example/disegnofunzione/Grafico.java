package com.example.disegnofunzione;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;

public class Grafico extends androidx.appcompat.widget.AppCompatImageView {
    Paint paint = new Paint();
    String str = "canial";

    private void init() {
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(5);
    }

    public Grafico(Context context) {
        super(context);
        init();
    }

    public Grafico(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public Grafico(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init();
    }

    public String getStr(){return str;}

    @Override
    public void onDraw(Canvas canvas) {

        int endX = canvas.getClipBounds().right;
        int endY = canvas.getClipBounds().bottom;
        int centerX = canvas.getClipBounds().centerX();
        int centerY = canvas.getClipBounds().centerY();

        canvas.drawLine(centerX, 0, centerX, endY, paint);
        canvas.drawLine(0, centerY, endX, centerY, paint);

    }


}