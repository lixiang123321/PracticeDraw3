package com.hencoder.hencoderpracticedraw3.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice14GetFontMetricsView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String[] texts = {"A", "a", "J", "j", "Â", "â"};
    int top = 200;
    int bottom = 400;
    float[] mOffsetGrp = new float[texts.length];

    public Practice14GetFontMetricsView(Context context) {
        super(context);
    }

    public Practice14GetFontMetricsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice14GetFontMetricsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    {
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(20);
        paint1.setColor(Color.parseColor("#E91E63"));

        mTextPaint.setTextSize(160);

        Paint.FontMetrics fontMetrics = new Paint.FontMetrics();
        for (int i = 0; i < texts.length; i++) {
            mTextPaint.getFontMetrics(fontMetrics);

            // debug 1
//            mOffsetGrp[i] = 0;

            // debug 2
//            mOffsetGrp[i] = (fontMetrics.top + fontMetrics.bottom) / 2;

            mOffsetGrp[i] = (fontMetrics.ascent + fontMetrics.descent) / 2;

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(50, top, getWidth() - 50, bottom, paint1);

        // 使用 Paint.getFontMetrics() 计算出文字的显示区域
        // 然后计算出文字的绘制位置，从而让文字上下居中
        // 这种居中算法的优点是，可以让不同的文字的 baseline 对齐

        int middle = (top + bottom) / 2;

        for (int i = 0; i < texts.length; i++) {
            canvas.drawText(texts[i], 100 * (i + 1), middle - mOffsetGrp[i], mTextPaint);
        }

        // debug 1. when offset set to 0, all string still on the same base line.
        // debug 2. all string 's FontMetrics, are same.
        // debug 3. use ascent + desent / 2, works ok.
        // debug 4. use top + bottom / 2, works not ok, a little lower.
        // (top + bottom) / 2 != (ascent + desent) / 2

    }
}
