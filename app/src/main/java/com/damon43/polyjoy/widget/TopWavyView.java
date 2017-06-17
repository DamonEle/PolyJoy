package com.damon43.polyjoy.widget;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.LinearInterpolator;

import com.damon43.common.commonutils.LogUtils;
import com.damon43.polyjoy.R;

/**
 * Created by Administrator on 2017/5/11.
 * 我的界面顶部的波浪view
 */

public class TopWavyView extends View {
    private static final int DEFULT_WIDTH = 400;
    private static final int DEFULT_HEIGHT = 80;
    private static final long MOVE_TIME = 1800;

    private int width;
    private int height;
    /*波浪起始点*/
    private float wavyStartX;
    private float wavyStartY;
    /*波浪控制点*/
    private float wavyConX1;
    private float wavyConY1;
    /*波浪控制点*/
    private float wavyConX2;
    private float wavyConY2;
    /*波浪控制点*/
    private float wavyConX3;
    private float wavyConY3;
    /*波浪控制点*/
    private float wavyConX4;
    private float wavyConY4;
    /*波浪终止点*/
    private float wavyEndX1;
    private float wavyEndY1;
    /*波浪终止点*/
    private float wavyEndX2;
    private float wavyEndY2;
    private Paint mPaint;
    private Context mContext;
    private static final float REFRESH_SIZE = 2;
    private static final float REFRESH_SIZE_OF = 50;
    private static final long REFRESH_TIME = 50;

    public TopWavyView(Context context) {
        this(context, null);
    }

    public TopWavyView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TopWavyView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setColor(mContext.getResources().getColor(R.color.accent));
        mPaint.setFlags(Paint.ANTI_ALIAS_FLAG);
        mPaint.setStyle(Paint.Style.FILL_AND_STROKE);

        getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                startWavy();
            }
        });
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureheigth = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        /*
        * 1.view乃顶级抽象的空间，系统分配的measureWidth值一般是一个很大的数值，可能跟当前控件所在的容器内的
        * 可绘制空间正相关。
        * 2.具体的view：该measureWidth值的大小跟view的具体类型有关，如girdview，系统默认分配给它的改值是改
        * girdview一列的高度，很明显特殊对待;imageview则跟view一样，估计没有做特殊处理
        * 3.得出结论，measureWidth与view的类型有关。 开发者应该使用该值来作为view最终需要呈现大小的依据,达到
        * 理想的效果
        * */
        if (widthMode == MeasureSpec.AT_MOST) {
            if (measureWidth > DEFULT_WIDTH) {
                LogUtils.logD("measurewidth:" + measureWidth);
                measureWidth = DEFULT_WIDTH;
            }
            width = measureWidth;
        } else if (widthMode == MeasureSpec.EXACTLY) {
            width = measureWidth * 2;
        }
        if (heightMode == MeasureSpec.AT_MOST) {
            if (measureheigth > DEFULT_HEIGHT) {
                measureheigth = DEFULT_HEIGHT;
            }
            height = measureheigth;
        } else if (heightMode == MeasureSpec.EXACTLY) {
            height = measureheigth;
        }
        resetControlPoint();
        setMeasuredDimension(width, height);
    }

    private void resetControlPoint() {
        wavyStartY = height*3 / 4f;
        wavyStartX = 0;
        wavyConX1 = width / 8f;
        wavyConY1 = wavyStartY - wavyConX1 + 100;
        wavyConX2 = width * 3 / 8f;
        wavyConY2 = wavyConX1 + wavyStartY - 100;
        wavyConX3 = width * 5 / 8f;
        wavyConY3 = wavyConY1;
        wavyConX4 = width * 7 / 8f;
        wavyConY4 = wavyConY2;
        wavyEndX1 = width / 2f;
        wavyEndY1 = wavyStartY;
        wavyEndX2 = width;
        wavyEndY2 = wavyStartY;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Path cubicPath = new Path();
//        FillType.WINDING	取path所有所在区域 默认值
//        FillType.EVEN_ODD	取path所在并不相交区域
//        FillType.INVERSE_WINDING	取path所有未占区域
//        FillType.INVERSE_EVEN_ODD  取path未占或相交区域
        cubicPath.setFillType(Path.FillType.EVEN_ODD);
        cubicPath.moveTo(wavyStartX, wavyStartY);
        cubicPath.cubicTo(wavyConX1, wavyConY1, wavyConX2, wavyConY2, wavyEndX1, wavyEndY1);
        cubicPath.moveTo(wavyEndX1, wavyEndY1);
        cubicPath.cubicTo(wavyConX3, wavyConY3, wavyConX4, wavyConY4, wavyEndX2, wavyEndY2);
        cubicPath.moveTo(wavyStartX, wavyStartY);
        cubicPath.lineTo(wavyStartX, 0);
        cubicPath.lineTo(wavyEndX2, 0);
        cubicPath.lineTo(wavyEndX2, wavyEndY2);
        canvas.drawPath(cubicPath, mPaint);
    }


    private void startWavy() {
        //  使用属性动画
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "translationX", 0, -width / 2).setDuration(
                MOVE_TIME);
        animator.setRepeatMode(ObjectAnimator.RESTART);
        animator.setInterpolator(new LinearInterpolator());
        animator.setRepeatCount(3000);
        animator.start();
    }
}
