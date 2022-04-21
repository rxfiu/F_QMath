package com.example.fqmath;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PixelFormat;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity2 extends AppCompatActivity {
    float centerX, centerY, endX, endY;
    Bitmap bitmap;
    Function mathFunction;
    float offset = 10;
    float scale = 100f, scaleX = 1, scaleY = 1;


    public RecyclerViewFunctionsAdapter getAdapter() {
        return adapter;
    }

    RecyclerViewFunctionsAdapter adapter;
    public Graph getGraph() {
        return graph;
    }

    Graph graph;

    public List<FunctionToDraw> getFunctions() {
        return functions;
    }

    List<FunctionToDraw> functions;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        functions = new ArrayList<FunctionToDraw>();
        configGraph();

        Button buttonAddFunction = findViewById(R.id.buttonAddFunction);
        buttonAddFunction.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                onButtonAddFunctionClick(v);
            }
        });

        ImageButton zoomIn = findViewById(R.id.imageButtonZoomIn);
        zoomIn.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                onImageButtonZoomIn(v);
            }
        });

        ImageButton zoomOut = findViewById(R.id.imageButtonZoomOut);
        zoomOut.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick (View v)
            {
                onImageButtonZoomOut(v);
            }
        });
    }
    void configRecyclerView() {
        RecyclerView recyclerView = findViewById(R.id.recyclerViewFunctions);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new RecyclerViewFunctionsAdapter(this, getGraph(), getFunctions());
        recyclerView.setAdapter(adapter);
    }
    void configScrolling() {
        final ImageView imageView = findViewById(R.id.imageView);
        final Graph g = getGraph();
        //noinspection AndroidLintClickableViewAccessibility
        imageView.setOnTouchListener(new View.OnTouchListener() {
            PointF startPoint = new PointF();
            PointF endPoint = new PointF();

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                switch (event.getAction()) {

                    case MotionEvent.ACTION_MOVE:
                        endPoint.set(event.getX(), event.getY());
                        PointF dx = new PointF(
                                startPoint.x - endPoint.x,
                                startPoint.y - endPoint.y);
                        g.getBounds().setOffsetX(dx.x);
                        g.getBounds().setOffsetY(dx.y);
                        g.reDrawAll();
                        break;
                    case MotionEvent.ACTION_DOWN:
                        startPoint.set(event.getX(), event.getY());
                        break;
                    case MotionEvent.ACTION_UP:

                        break;
                    default:
                        break;
                }
                imageView.invalidate();
                return true;
            }
        });
    }
    private void configGraph() {
        final ImageView imageView = findViewById(R.id.imageView);
        imageView.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                //Remove it here unless you want to get this callback for EVERY
                //layout pass, which can get you into infinite loops if you ever
                //modify the layout from within this method.
                imageView.getViewTreeObserver().removeOnGlobalLayoutListener(this);

                //Now you can get the width and height from content
                int width = imageView.getWidth();
                int height = imageView.getHeight();

                bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
                imageView.setImageBitmap(bitmap);

                Canvas canvas = new Canvas(bitmap);
                graph = new Graph(canvas, getFunctions());
                configRecyclerView();
                configScrolling();
            }
        });
    }

    public void onButtonAddFunctionClick(View view) {
        EditText funcTxt = findViewById(R.id.editTextFunction);
        ImageView imageView = findViewById(R.id.imageView);
        String fx = funcTxt.getText().toString();

        Graph graph = getGraph();

        FunctionManager.addFunction(fx, getFunctions(), graph, getAdapter());
    }

    public void onImageButtonZoomIn(View view) {
        ImageView imageView = findViewById(R.id.imageView);

        FunctionManager.zoomIn(getGraph());

        imageView.invalidate();
    }

    public void onImageButtonZoomOut(View view) {
        ImageView imageView = findViewById(R.id.imageView);

        FunctionManager.zoomOut(getGraph());

        imageView.invalidate();
    }
}