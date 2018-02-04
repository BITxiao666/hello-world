package com.example.javacourseexercise;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;

/**
 * Created by BITshaw on 2018/1/24.
 * 绘制每一个课程单元格
 */

public class CellView extends android.support.v7.widget.AppCompatTextView {
    private int mBgColor = 0;                        //背景颜色
    private int coner_size = 0;                     //圆角大小

    /**
     *
     * @param context
     * @param bgColor
     * @param _coner_size 调用时传 dip2px(this, 3)
     */
    public CellView(Context context, int bgColor, int _coner_size) {
        super(context);
        mBgColor = bgColor;
        coner_size = _coner_size;
    }
    @Override
    protected void onDraw(Canvas canvas) {

        Paint paint = new Paint();
        paint.setAntiAlias(true);               //设置画笔为无锯齿
        paint.setColor(mBgColor);               //设置画笔颜色
        paint.setAlpha(180);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight()), coner_size, coner_size, paint);

        super.onDraw(canvas);
    }
}
