package com.demo.android_view.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.*;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import com.demo.android_view.R;

/**
 * created by tea9 at 2018/11/14
 */
public class LineProgressView extends View {

    private Context context;
    private Paint paint;
    private Paint linePaint;

    private float paintWidth;
    private float paintSize;
    private int paintColor;

    /* 渐变颜色数组 */
    private int[] mColorArray;
    /* 渐变起始颜色 */
    private int mStartColor;
    /* 渐变终止颜色 */
    private int mEndColor;
    // 当前进度
    private  float progress = 10;

    private int width,height;

    // 线的X起始左标
    private float startX;
    private float endX;

    private float lenth;

    public LineProgressView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        getAtt(attrs);
        initPaint();
        setLayerType(View.LAYER_TYPE_SOFTWARE, null); // 硬件加速
    }

    private void getAtt(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.LineProgressView);
        paintColor = typedArray.getColor(R.styleable.LineProgressView_paint_color,Color.parseColor("#df0781"));
        paintWidth = typedArray.getDimension(R.styleable.LineProgressView_paint_width,dp2px(10));
        paintSize = typedArray.getDimension(R.styleable.LineProgressView_paint_size,30f);
        mStartColor = typedArray.getColor(R.styleable.LineProgressView_start_color,Color.parseColor("#3FC199"));
        mEndColor = typedArray.getColor(R.styleable.LineProgressView_end_color,Color.parseColor("#000000"));
        mColorArray = new int[]{mStartColor,mEndColor};
        typedArray.recycle(); // 回收
    }

    private void initPaint() {
        paint = getPaint(Paint.Style.FILL,Color.parseColor("#000000"),paintSize);
        paint.setStrokeCap(Paint.Cap.ROUND);

        linePaint = getPaint(Paint.Style.FILL,Color.RED,paintWidth);
        linePaint.setStrokeCap(Paint.Cap.BUTT);
        linePaint.setStyle(Paint.Style.STROKE);

    }

    private Paint getPaint(Paint.Style style,int color,float width){
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG); //抗锯齿标志
        paint.setStyle(style);
        paint.setColor(color);
        paint.setStrokeWidth(width);
        paint.setAntiAlias(true);
        return paint;
    }


    protected int dp2px(int dpVal) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,dpVal,getResources().getDisplayMetrics());
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        width = w;
        height = h;
        startX = dp2px(10);
        endX = width-dp2px(10);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        LinearGradient linearGradient = new LinearGradient(0, getHeight(), getWidth() , getHeight(), mColorArray, null, Shader.TileMode.CLAMP);
        linePaint.setShader(linearGradient);
        // 根据 progress进度 ，获得X坐标
        lenth = (endX*progress/100)+startX-(progress/100*(startX));
        // 底部背景线
        //float startX, float startY, float stopX, float stopY, Paint paint
        canvas.drawLine(startX,height/2,endX,height/2,paint);
        // 画 进度线
        canvas.drawLine(startX,height/2,lenth,height/2,linePaint);
    }


    public void setProgress(final int mprogress) {
        final ValueAnimator anim = ValueAnimator.ofFloat(0,mprogress);
        anim.setDuration(3000);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animator) {
                progress  = (float) animator.getAnimatedValue();
                invalidate();
            }
        });
        anim.start();
    }
}
