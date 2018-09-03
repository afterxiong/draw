package com.bint.draw.view;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.TypedValue;
import android.view.View;

import com.socks.library.KLog;

import java.lang.reflect.Type;

/**
 * Created by xxcc on 2018/9/3.
 */
public class BinView extends View {

    private Paint paint;
    private int widht;
    private int height;
    private RectF rectF;

    private ArgbEvaluator argbEvaluator = new ArgbEvaluator();
    private float sweepAngle = 0;

    public BinView(Context context) {
        super(context);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setStrokeWidth(px2dp(4));
        paint.setAntiAlias(true);
        paint.setStrokeCap(Paint.Cap.ROUND);
        ValueAnimator animator = ValueAnimator.ofFloat(0, 300);
        animator.setDuration(5000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {

            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                sweepAngle = (float) animation.getAnimatedValue();
                invalidate();
                KLog.d(sweepAngle);
            }
        });
        animator.start();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }


    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        rectF = new RectF();
        rectF.left = 0;
        rectF.top = height / 2 - widht / 2;
        rectF.right = widht;
        rectF.bottom = height / 2 + widht / 2;


        paint.setColor(Color.GRAY);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(px2dp(20));
        for (int i = 0; i < sweepAngle; i++) {
            Integer color = (Integer) argbEvaluator.evaluate(i / 360f, Color.BLUE, Color.GREEN);//颜色插值器（level 11以上才可以用）
            paint.setColor(color);

            if (i % 2 == 0) {
                canvas.drawArc(rectF, -90 + i, 1.35f, false, paint);
            }
        }


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        widht = w;
        height = h;
    }


    public int px2dp(int val) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, val, getResources().getDisplayMetrics());
    }
}
