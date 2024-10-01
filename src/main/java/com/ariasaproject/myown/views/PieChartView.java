package com.ariasaproject.myown.views;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Path;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class PieChartView extends View {
    
    private final Paint piePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private final RectF pieRect = new RectF();
    private final Path slicePath = new Path();

    private List<PieSlice> slices = new ArrayList<>();
    private float totalValue = 0f;

    public PieChartView(Context context) {
        super(context);
        initPaints();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initPaints();
    }

    public PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initPaints();
    }

    private void initPaints() {
        piePaint.setStyle(Paint.Style.FILL);
        textPaint.setTextSize(32f); // Adjust text size as needed
        textPaint.setTextAlign(Paint.Align.CENTER);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        int padding = 20; // Adjust padding as needed
        pieRect.set(padding, padding, w - padding, h - padding);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (slices.isEmpty()) {
            return;
        }

        float currentAngle = 0f;
        for (PieSlice slice : slices) {
            float sweepAngle = (slice.getValue() / totalValue) * 360f;

            slicePath.reset();
            slicePath.moveTo(pieRect.centerX(), pieRect.centerY());
            slicePath.arcTo(pieRect, currentAngle, sweepAngle);
            slicePath.lineTo(pieRect.centerX(), pieRect.centerY());
            slicePath.close();

            piePaint.setColor(slice.getColor());
            canvas.drawPath(slicePath, piePaint);

            // Draw slice text (optional)
            float textAngle = currentAngle + sweepAngle / 2f;
            double textX = pieRect.centerX() + Math.cos(Math.toRadians(textAngle)) * (pieRect.width() / 3f);
            double textY = pieRect.centerY() + Math.sin(Math.toRadians(textAngle)) * (pieRect.height() / 3f);
            String text = slice.getLabel() + " (" + slice.getValue() + ")"; // Adjust text format
            canvas.drawText(text, (float) textX, (float) textY, textPaint);

            currentAngle += sweepAngle;
        }
    }

    public void setSlices(List<PieSlice> slices) {
        this.slices = slices;
        calculateTotalValue();
        invalidate();
    }

    private void calculateTotalValue() {
        totalValue = 0f;
        for (PieSlice slice : slices) {
            totalValue += slice.getValue();
        }
    }

    public static class PieSlice {
        private final String label;
        private final float value;
        private final int color;

        public PieSlice(String label, float value, int color) {
            this.label = label;
            this.value = value;
            this.color = color;
        }

        public String getLabel() {
            return label;
        }

        public float getValue() {
            return value;
        }

        public int getColor() {
            return color;
        }
    }
}
