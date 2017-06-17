package com.damon43.polyjoy.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.damon43.common.commonutils.LogUtils;
import com.damon43.polyjoy.R;


/**
 * @author damonmasty
 *         Created on 下午8:50 17-6-2.
 */
/*希望除了上帝和我以外，不会有人阅读到以下这段代码...*/
public class JoysRecordView extends View {

    private static final float LINE_WIDTH = 1.5F;
    private static final float LITTLE_LINE_WIDTH = 1F;
    private static final float RECF_WIDTH = 40F;
    private static final float TEXT_WIDTH = 25f;
    private static float BOTTOM_TEXT_MARGIN;
    private static float TEXT_HEIGHT_OFFECT = 20F;
    private static float TEXT_WIDTH_OFFECT = 50f;
    private final Context mContext;
    /*灰色线画笔*/
    private Paint mGrayLinePaint;
    /*灰色线画笔*/
    private Paint mGrayLinePaint2;
    /*文本画笔*/
    private Paint mTextPaint;
    /*折线画笔*/
    private Paint mConnectPaint;
    /*点画笔*/
    private Paint mPointPaint;
    /*柱状画笔*/
    private Paint mRecfPaint;

    private int mWidth;
    private int mDefaultWidth = 450;
    private int mHeight;
    private int mDefaultHeight = 300;
    private int mCountLineCount = 8;
    private int mKindsLineCount = 5;

    private String[] strs = new String[]{"社会", "美女", "科学", "头条", "体育"};
    private float textwidth;
    private float DEFULT_TEXTWIDTH;
    private boolean isAllSee = true;

    public JoysRecordView(Context context) {
        this(context, null);
    }

    public JoysRecordView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public JoysRecordView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mContext = context;
        init();
    }

    private void init() {
        mGrayLinePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGrayLinePaint.setColor(mContext.getResources().getColor(R.color.gray2));
        mGrayLinePaint.setStrokeWidth(LINE_WIDTH);
        mGrayLinePaint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        mGrayLinePaint2.setColor(mContext.getResources().getColor(R.color.gray2));
        mGrayLinePaint2.setStrokeWidth(LITTLE_LINE_WIDTH);
        mRecfPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mRecfPaint.setColor(mContext.getResources().getColor(R.color.ahplablue));
        mRecfPaint.setStrokeWidth(RECF_WIDTH);
        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setColor(mContext.getResources().getColor(R.color.gray));
        mTextPaint.setTextSize(TEXT_WIDTH);
        DEFULT_TEXTWIDTH = mTextPaint.measureText("头条");
        Paint.FontMetrics foneMetrics = mTextPaint.getFontMetrics();
        TEXT_HEIGHT_OFFECT = foneMetrics.bottom - foneMetrics.top;//获取文本高度
        BOTTOM_TEXT_MARGIN = TEXT_HEIGHT_OFFECT / 3;
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthMeasureMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMeasureMode = MeasureSpec.getMode(heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);

        if (widthMeasureMode == MeasureSpec.AT_MOST) {
            mWidth = measureWidth > mDefaultWidth ? mDefaultWidth : measureWidth;
        } else if (widthMeasureMode == MeasureSpec.EXACTLY) {
            mWidth = measureWidth;
        }
        if (heightMeasureMode == MeasureSpec.AT_MOST) {
            mHeight = measureHeight > mDefaultHeight ? mDefaultHeight : measureHeight;
        } else if (widthMeasureMode == MeasureSpec.EXACTLY) {
            mHeight = measureHeight;
        }

        setMeasuredDimension(mWidth, mHeight);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        float yOffect = mHeight - TEXT_HEIGHT_OFFECT * 3 / 2 - BOTTOM_TEXT_MARGIN;
        float lineHeight = yOffect / mCountLineCount;
        float bottomLineY = yOffect + TEXT_HEIGHT_OFFECT / 2;
        //画次数横坐标

        for (int i = 0; i < mCountLineCount; i++) {
            float y = (i * lineHeight) + TEXT_HEIGHT_OFFECT / 2;

            canvas.drawLine(TEXT_WIDTH_OFFECT + TEXT_WIDTH_OFFECT / 3, y, mWidth, y, mGrayLinePaint2);
            canvas.drawText((mCountLineCount - i) * 10 + "次", 0, y + TEXT_HEIGHT_OFFECT / 4, mTextPaint);
        }
        //画种类纵坐标
        int lineWidth = (int) (mWidth - TEXT_WIDTH_OFFECT) / (mKindsLineCount + 1);
        for (int i = 1; i <= mKindsLineCount; i++) {
            float x = (i * lineWidth) + TEXT_WIDTH_OFFECT;
            //在view宽高值的x，y上绘画会绘制到view界面之外的地方 导致效果不可见，因为view的相对xy坐标是从0开始
            canvas.drawLine(x, TEXT_HEIGHT_OFFECT / 2, x, bottomLineY, mGrayLinePaint2);
            canvas.drawText(strs[i - 1], x - DEFULT_TEXTWIDTH / 2, bottomLineY + BOTTOM_TEXT_MARGIN + TEXT_HEIGHT_OFFECT * 3 / 4, mTextPaint);
            if (isAllSee) {
                canvas.drawLine(x, bottomLineY, x, yOffect * (i * 8 / (mCountLineCount * 10)), mRecfPaint);
            }
        }
        canvas.drawLine(TEXT_WIDTH_OFFECT + TEXT_WIDTH_OFFECT / 3, bottomLineY, mWidth, bottomLineY, mGrayLinePaint);


    }
}
