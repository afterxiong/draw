package com.bint.draw.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.RectF;

/**
 * Created by xxcc on 2018/9/3.
 */
public class LinBinView extends BaseView {

    private RectF rectF;
    private float progress = 0;

    public LinBinView(Context context) {
        super(context);

        ValueAnimator animator = ValueAnimator.ofFloat(0, 300);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        animator.start();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        rectF = new RectF();
        rectF.left = 0;
        rectF.top = height / 2 - width / 2;
        rectF.right = width;
        rectF.bottom = height / 2 + width / 2;


        paint.setStrokeWidth(px2pd(10));
        for (int i = 0; i < progress; i++) {
            if (i % 2 == 0) {
                canvas.drawArc(rectF,i,3,false,paint);
            }
        }
    }
}
