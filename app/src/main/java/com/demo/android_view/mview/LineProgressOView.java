package com.demo.android_view.mview;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.demo.android_view.R;

/**
 * created by tea9 at 2018/11/15
 * 横线进度一个颜色
 */
public class LineProgressOView extends View {

    private Context context;
    private int background_color, color_a, margin; // margin 左右边距
    private float bar_height, bar_width;
    private float num_a;
    private Paint back_paint, num_a_paint;
    private int width, height;
    private int type;
    // 线的X起始左标
    private float startX;
    private float endX;

    public LineProgressOView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getAtt(attrs);
        initPaint();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); // 硬件加速
    }

    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.LineProgressOView);
        background_color = typedArray.getColor(R.styleable.LineProgressOView_background_color, Color.parseColor("#E3E2E2"));
        color_a = typedArray.getColor(R.styleable.LineProgressOView_bar_color, Color.parseColor("#FF6059"));
        bar_height = typedArray.getDimension(R.styleable.LineProgressOView_bar_height, 30f);
        bar_width = typedArray.getDimension(R.styleable.LineProgressOView_bar_width, 0);
        margin = typedArray.getInteger(R.styleable.LineProgressOView_margin, dp2px(30));
        num_a = typedArray.getFloat(R.styleable.LineProgressOView_num, 40);
        type = typedArray.getInteger(R.styleable.LineProgressOView_bar_type, 1);
        typedArray.recycle();
    }

    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    protected float dp2px(float dpVal) {
        return (float) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dpVal, getResources().getDisplayMetrics());
    }

    private void initPaint() {
        back_paint = getPaint(Paint.Style.FILL, background_color, bar_height);
        back_paint.setStrokeCap(Paint.Cap.ROUND);

        num_a_paint = getPaint(Paint.Style.FILL, color_a, bar_height);
        num_a_paint.setStrokeCap(Paint.Cap.ROUND);
        num_a_paint.setColor(color_a);
    }

    private Paint getPaint(Paint.Style style, int color, float width) {
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); //抗锯齿标志
        paint.setStyle(style);
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setAntiAlias(true);
        return paint;
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        startX = margin;
        endX = width - margin;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawLine(startX, height / 2, endX, height / 2, back_paint); // 背景线
        float length_a = (startX * num_a / 100) + endX - (num_a / 100 * (endX));
        float length_b = (endX * num_a / 100) + startX - (num_a / 100 * (startX));
        if (type==0) { //<-
            num_a_paint.setStrokeCap(Paint.Cap.ROUND);
            if (num_a > 0)
                canvas.drawLine(endX, height / 2, length_a, height / 2, num_a_paint);
        } else if (type==1) { //->
            num_a_paint.setStrokeCap(Paint.Cap.ROUND);
            if (num_a > 0)
                canvas.drawLine(startX, height / 2, length_b , height / 2, num_a_paint);
        }

    }

    public void setNumProgress(final int xx) {
        final ValueAnimator anim = ValueAnimator.ofFloat(0, xx);
        anim.setDuration(2000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                num_a = (float) animator.getAnimatedValue();
                invalidate();
            }
        });
        anim.start();
    }
    public void setType(int type) {
        this.type = type;
        invalidate();
    }
}

