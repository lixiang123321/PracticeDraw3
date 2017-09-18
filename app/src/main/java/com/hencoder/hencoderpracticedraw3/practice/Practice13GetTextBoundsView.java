package com.hencoder.hencoderpracticedraw3.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice13GetTextBoundsView extends View {
    Paint paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
    Paint textPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    String text1 = "A";
    String text2 = "a";
    String text3 = "J";
    String text4 = "j";
    String text5 = "Â";
    String text6 = "â";
    int top = 200;
    int bottom = 400;
    String[] textGrp = new String[]{text1, text2, text3, text4, text5, text6};
    float[] offsetGrp = new float[textGrp.length];

    public Practice13GetTextBoundsView(Context context) {
        super(context);
    }

    public Practice13GetTextBoundsView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice13GetTextBoundsView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    private Rect bounds;

    {
        paint1.setStyle(Paint.Style.STROKE);
        paint1.setStrokeWidth(20);
        paint1.setColor(Color.parseColor("#E91E63"));

        textPaint.setTextSize(160);
        bounds = new Rect();
        for (int i = 0; i < textGrp.length; i++) {
            textPaint.getTextBounds(textGrp[i], 0, textGrp[i].length(), bounds);
            offsetGrp[i] = (bounds.top + bounds.bottom) / 2;// center horizontal line
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawRect(50, top, getWidth() - 50, bottom, paint1);

        // 使用 Paint.getTextBounds() 计算出文字的显示区域
        // 然后计算出文字的绘制位置，从而让文字上下居中
        // 这种居中算法的优点是，可以让文字精准地居中，分毫不差

        // 注意：原来的各个字符，中心线是不同的，目标是让他们都回到同一个中心线

        int middle = (top + bottom) / 2;
        for (int i = 0; i < textGrp.length; i++) {
            canvas.drawText(textGrp[i], 100 * (i + 1), middle - offsetGrp[i], textPaint);
        }
    }
}