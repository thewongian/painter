package com.example.painter;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.graphics.Canvas;
import android.graphics.Path;

import java.util.HashMap;
import java.util.Stack;

public class MyCanvas extends View {
    HashMap<Integer, Path> activePaths;
    Paint pathPaint;
    Stack<Paint> pathStack;
    public final static int DEFAULT_STROKE_WIDTH = 30;

    public MyCanvas(Context context, AttributeSet attrs) {
        super(context, attrs);
        activePaths = new HashMap<>();
        pathPaint = initializePaint(getResources().getColor(R.color.red));
        pathStack = new Stack<Paint>();
    }
    public Paint initializePaint(int color) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(color);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(DEFAULT_STROKE_WIDTH);
        return paint;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        pathStack.push(pathPaint);
        pathPaint = initializePaint(pathPaint.getColor());
        for(Path path: activePaths.values()){
            canvas.drawPath(path, pathPaint);
        }
        super.onDraw(canvas);



    }

    public void addPath(int id, float x, float y) {
        Path path = new Path();
        path.moveTo(x, y);
        activePaths.put(id, path);
        invalidate();
    }

    public void updatePath(int id, float x, float y) {
        Path path = activePaths.get(id);
        if(path != null){
            path.lineTo(x, y);
        }
        invalidate();
    }

    public void removePath(int id) {
        if(activePaths.containsKey(id)){
            activePaths.remove(id);
        }
        invalidate();
    }
}
