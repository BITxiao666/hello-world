# 开发日志

## 1月24日 肖子原

---

今天主要编写了课程表的静态模型。
一开始想到的方法是在`layout.xml`文件中分出来25个Cell，但发现这种方法非常不优雅，所以改进为每天的课程为一个Layout，每个Cell向其中动态添加View。
```xml
        <LinearLayout
            android:id="@+id/day_1"
            android:layout_width="0dp"
            android:layout_height="match_parent"
            android:layout_weight="1"
            android:weightSum="5"
            android:orientation="vertical"/>
```
每个Cell用一个TextView来实现，在创建时动态加载，但这样也存在一个问题，一个星期有五天有课，就有7到8个Layout,大量使用`GetById()`，后来发现有更加优雅的方法——`Butterknife`框架。
使用方法如下所示。
```java
   @BindViews({R.id.day_1, R.id.day_2, R.id.day_3, R.id.day_4,R.id.day_5})
    List<LinearLayout> mcellViews;
```
在UI风格方面，主要学习了超级课程表app，但考虑到超级课程表app面向所有大学，所以每天的课程数不定，采用 ListView 的方法实现。而我们的项目主要面向北理工的同学，北理工的课程每天只会安排在5个时段，所以竖直方向只要5个View。

为了实现圆角矩形文本框，我创建了一个自定义控件`CellView`，继承TextView，通过重写 onDraw 方法来实现。
```java
    @Override
    protected void onDraw(Canvas canvas) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);           
        paint.setColor(mBgColor);               
        paint.setAlpha(180);
        paint.setStyle(Paint.Style.FILL);
        canvas.drawRoundRect(new RectF(0, 0,getMeasuredWid
        th(), getMeasuredHeight()), coner_size, coner_size, paint);
        super.onDraw(canvas);
    }
```
为了在Cell之间留有一定的间隙，所以先用一个较大的Layout占据空间，再向里面添加一个稍小的CellView控件，Layout采用LinearLayout,采用`weight`的方法均分整个Day。
```java
   textView.setLayoutParams(one_cell);
   linearLayout.setPadding(2, 2, 2, 2);
   linearLayout.addView(textView);
   mcellViews.get(i-1).addView(linearLayout);
```
