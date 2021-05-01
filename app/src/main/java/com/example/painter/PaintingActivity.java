package com.example.painter;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class PaintingActivity extends AppCompatActivity implements View.OnClickListener {

    Button red, blue, green, undo, clear, done;
    MyCanvas myCanvas;
    TouchListener listener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_painting);

        Bundle b = getIntent().getExtras();
        Bitmap thumbnail = (Bitmap) b.get("data");
        myCanvas = findViewById(R.id.image);
        red = findViewById(R.id.red);
        blue = findViewById(R.id.blue);
        green = findViewById(R.id.green);
        undo = findViewById(R.id.undo);
        clear = findViewById(R.id.clear);
        done = findViewById(R.id.done);

        red.setOnClickListener(this);
        blue.setOnClickListener(this);
        green.setOnClickListener(this);
        undo.setOnClickListener(this);
        clear.setOnClickListener(this);
        done.setOnClickListener(this);


        myCanvas.setBackground(new BitmapDrawable(getResources(), thumbnail));
        listener = new TouchListener(this);
        myCanvas.setOnTouchListener(listener);


    }

    public void addPath(int id, float x, float y) {
        myCanvas.addPath(id, x, y);
    }

    public void updatePath(int id, float x, float y) {
        myCanvas.updatePath(id, x, y);
    }

    public void removePath(int id) {
        myCanvas.removePath(id);
    }

    public void onDoubleTap() {

    }

    public void onLongPress() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.red:
                myCanvas.pathPaint.setColor(getResources().getColor(R.color.red));
                break;
            case R.id.blue:
                myCanvas.pathPaint.setColor(getResources().getColor(R.color.blue));
                break;
            case R.id.green:
                myCanvas.pathPaint.setColor(getResources().getColor(R.color.green));
                break;


        }
    }


}