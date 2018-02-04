package com.example.javacourseexercise;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;
import com.yalantis.contextmenu.lib.MenuObject;
import com.yalantis.contextmenu.lib.MenuParams;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemClickListener;
import com.yalantis.contextmenu.lib.interfaces.OnMenuItemLongClickListener;

import org.litepal.LitePal;
import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements OnMenuItemClickListener, OnMenuItemLongClickListener {

    @BindView(R.id.week_main)
    LinearLayout week;

    @BindView(R.id.segments_main)
    LinearLayout segments;

    @BindViews({R.id.weekPanel_1, R.id.weekPanel_2, R.id.weekPanel_3, R.id.weekPanel_4,
            R.id.weekPanel_5})
    List<LinearLayout> mcellViews;

    private FragmentManager fragmentManager;
    private ContextMenuDialogFragment mMenuDialogFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fragmentManager = getSupportFragmentManager();

        LitePal.getDatabase();
        initToolbar();
        initMenuFragment();
        ButterKnife.bind(this);
        initDayView();
        initSegmentView();
        LitePal.getDatabase();
        initFun();
        initCell_1();
        ClickFloatButton();
    }

    /**
     * 监听FloatButton
     */
    private void ClickFloatButton(){
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this,
                       GPAMainActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 初始化示例的数据
     */
    private void initFun()
    {
        List<Course> list = DataSupport.findAll(Course.class);
        if(list.size()==0) {
            new Course("微机接口技术", "王娟",
                    "信2006", 1, 8, 24).save();
            new Course("编译原理与设计", "李侃",
                    "信2006", 1, 16, 21).save();
            new Course("计算机网络", "宿红毅",
                    "信2004", 1, 16, 13).save();
            new Course("计算机体系结构", "马忠梅",
                    "信2004", 9, 16, 32).save();
            new Course("20世纪国际关系历史", "张雷",
                    "信3010", 2, 12, 45).save();
            new Course("操作系统课程设计", "刘利雄",
                    "信5006", 1, 8, 52).save();
            new Course("操作系统课程设计", "刘利雄",
                    "信5006", 1, 8, 32).save();
            new Course("计算机硬件系统设计", "王娟",
                    "信5006", 1, 1, 33).save();
            new Course("", "",
                    "", 22, 22, 60).save();//不会显示出来
            WeekCount.init();
        }
    }

    /**
     * 展示项目的 github 链接
     */
    private void showGithub() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setContentView(R.layout.github_layout);
        DisplayMetrics dm=this.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        android.view.WindowManager.LayoutParams p =
                dialog.getWindow().getAttributes();             //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.66);                  //宽度设置为屏幕的0.66
        dialog.setCanceledOnTouchOutside(true);                 //设置点击屏幕Dialog消失
        dialog.getWindow().setAttributes(p);                    //设置生效
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView esc = (TextView)dialog.findViewById(R.id.github_confirm);
        esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView jump = (TextView)dialog.findViewById(R.id.jump_github);
        jump.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                Intent intent = new Intent(Intent.ACTION_VIEW);
                intent.setData(Uri.parse("https://github.com/BITxiao666/scholar"));
                startActivity(intent);
            }
        });
    }


    /**
     * 创建课程的横栏（周一~周五）
     */
    public void initDayView() {
        List<String> str_week = new ArrayList<>();
        str_week.add("周一");
        str_week.add("周二");
        str_week.add("周三");
        str_week.add("周四");
        str_week.add("周五");
        Calendar cal = Calendar.getInstance();
        int day_of_week = cal.get(Calendar.DAY_OF_WEEK)-1;
        TextView one_day = new TextView(this);
        one_day.setText("");
        one_day.setTextColor(Color.parseColor("#4A4A4A"));
        one_day.setBackgroundColor(Color.parseColor("#FAFAFA"));
        LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        one_grid.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
        one_grid.weight = 0.8f;
        one_day.setGravity(Gravity.CENTER_HORIZONTAL);
        one_day.setLayoutParams(one_grid);
        week.addView(one_day);
        int i;
        for (i = 0; i < 5; i++) {
            one_day = new TextView(this);
            one_day.setText(str_week.get(i));
            if(i+1==day_of_week){
                one_day.setTextColor(Color.parseColor("#EF6C00"));
            }
            else {
                one_day.setTextColor(Color.parseColor("#4A4A4A"));
            }
            one_grid.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            one_grid.weight = 1;
            one_day.setGravity(Gravity.CENTER_HORIZONTAL);
            one_day.setLayoutParams(one_grid);
            week.addView(one_day);
        }
    }

    /**
     * 创建课程表的竖栏（第一节到第五节）
     */
    private void initSegmentView() {
        List<String> str_segment = new ArrayList<>();
        str_segment.add("上午\n"+"1");
        str_segment.add("上午\n"+"2");
        str_segment.add("下午\n"+"1");
        str_segment.add("下午\n"+"2");
        str_segment.add("晚上");
        int i;
        TextView segment;
        LinearLayout.LayoutParams one_grid = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        for (i = 0; i < 5; i++) {
            segment = new TextView(this);
            segment.setText(str_segment.get(i));
            segment.setTextColor(Color.parseColor("#4A4A4A"));
            one_grid.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
            one_grid.weight = 1;
            segment.setGravity(Gravity.CENTER_HORIZONTAL);
            segment.setLayoutParams(one_grid);
            segments.addView(segment);
        }
    }

    /**
     * 动态加载每一节课
     */
    public void initCell_1(){
        for (int i=1;i<=5;i++) {
            for (int j=1;j<=5;j++) {
                int status = Course.getStatus(10*i+j,WeekCount.getCurrentWeek());
                if (status>0) {
                    LinearLayout linearLayout = new LinearLayout(this);
                    LinearLayout.LayoutParams Lp = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    Lp.weight = 1;
                    Lp.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    linearLayout.setLayoutParams(Lp);
                    LinearLayout.LayoutParams one_cell = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    CellView textView;
                    final Course course;
                    if(status==1){
                        textView = new CellView(this,
                                GridColor.getCourseBgColor((i+j)%10),
                                dip2px(this,5));
                        course = Course.getCourseWithWeek(10*i+j,WeekCount.getCurrentWeek());
                        textView.setText(course.getCourse_name()+"\n@"+course.getSite());
                    }
                    else {
                        textView = new CellView(this,
                                GridColor.getCourseBgColor(15),
                                dip2px(this,5));
                        course = Course.getCourseNoWeek(10*i+j);
                        textView.setText(course.getCourse_name()+"\n@"+course.getSite());
                    }
                    textView.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            if(course!=null) {
                                showCourseDetails(course);
                            }
                        }
                    });
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    one_cell.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLayoutParams(one_cell);
                    linearLayout.setPadding(2, 2, 2, 2);
                    linearLayout.addView(textView);
                    mcellViews.get(i-1).addView(linearLayout);
                }
                else{
                    LinearLayout linearLayout = new LinearLayout(this);
                    LinearLayout.LayoutParams Lp = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    Lp.weight = 1;
                    Lp.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    linearLayout.setLayoutParams(Lp);
                    LinearLayout.LayoutParams one_cell = new LinearLayout.LayoutParams(
                            ViewGroup.LayoutParams.MATCH_PARENT,
                            ViewGroup.LayoutParams.MATCH_PARENT);
                    TextView textView = new TextView(this);
                    textView.setText("");
                    textView.setBackgroundColor(Color.parseColor("#FFFFFF"));
                    textView.setTextColor(Color.parseColor("#FFFFFF"));
                    one_cell.gravity = Gravity.CENTER | Gravity.CENTER_HORIZONTAL;
                    textView.setGravity(Gravity.CENTER_HORIZONTAL);
                    textView.setLayoutParams(one_cell);
                    linearLayout.addView(textView);
                    mcellViews.get(i-1).addView(linearLayout);
                }
            }
        }
    }

    /**
     * 初始化toolbar栏
     */
    private void initMenuFragment() {
        MenuParams menuParams = new MenuParams();
        menuParams.setActionBarSize((int) getResources().getDimension(R.dimen.tool_bar_height));
        menuParams.setMenuObjects(getMenuObjects());
        menuParams.setClosableOutside(false);
        mMenuDialogFragment = ContextMenuDialogFragment.newInstance(menuParams);
        mMenuDialogFragment.setItemClickListener(this);
        mMenuDialogFragment.setItemLongClickListener(this);
    }

    /**
     * Toolbar 的内容在此修改
     * 函数来自开源框架
     * https://github.com/Yalantis/Context-Menu.Android
     */
    private List<MenuObject> getMenuObjects() {

        List<MenuObject> menuObjects = new ArrayList<>();

        MenuObject close = new MenuObject();
        close.setResource(R.drawable.icn_close);

        MenuObject send = new MenuObject("添加课程");
        send.setResource(R.drawable.ic_add_course);

        MenuObject like = new MenuObject("转到下周");
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.ic_change_week);
        like.setBitmap(b);

        MenuObject addFr = new MenuObject("新建学期");
        BitmapDrawable bd = new BitmapDrawable(getResources(),
                BitmapFactory.decodeResource(getResources(), R.drawable.ic_term));
        addFr.setDrawable(bd);

        MenuObject addFav = new MenuObject("获取源码");
        addFav.setResource(R.drawable.ic_github);


        menuObjects.add(close);
        menuObjects.add(send);
        menuObjects.add(like);
        menuObjects.add(addFr);
        menuObjects.add(addFav);
        return menuObjects;
    }

    private void initToolbar() {
        Toolbar mToolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView mToolBarTextView = (TextView) findViewById(R.id.text_view_toolbar_title);
        setSupportActionBar(mToolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setHomeButtonEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowTitleEnabled(false);
            getSupportActionBar().setDisplayHomeAsUpEnabled(false);
        }
        mToolBarTextView.setText("         "+String.valueOf(WeekCount.getCurrentWeek()));
    }

    /**
     * 点击menu时完成的动作
     */
    @Override
    public boolean onCreateOptionsMenu(final Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_main, menu);
        return true;
    }

    /**
     * 设置每一个菜单项被选中时执行的动作
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.context_menu:
                if (fragmentManager.findFragmentByTag(ContextMenuDialogFragment.TAG) == null) {
                    mMenuDialogFragment.show(fragmentManager, ContextMenuDialogFragment.TAG);
                }
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * 监听返回按键，以解决第三方控件无法
     * 正常加载的问题
     */
    @Override
    public void onBackPressed() {
        if (mMenuDialogFragment != null && mMenuDialogFragment.isAdded()) {
            mMenuDialogFragment.dismiss();
            finish();
        } else {
            finish();
        }
    }

    /**
     * 监听home按键，以解决第三方控件无法
     * 正常加载的问题
     */
    @Override
    protected void onUserLeaveHint() {
        finish();
        super.onUserLeaveHint();
    }

    /**
     * 设置每一个菜单项被选中时执行的动作
     */
    @Override
    public void onMenuItemClick(View clickedView, int position) {
        if(position == 1){
            Intent intent = new Intent(MainActivity.this,
                    EditActivity.class);
            startActivity(intent);
        }else if(position == 2){
            Intent intent = new Intent(MainActivity.this,
                    ChangeWeekActivity.class);
            startActivity(intent);
        }
        else if(position == 3){
            confirm_claer();
        }
        else if(position == 4){
            showGithub();
        }
    }

    /**
     * 长按菜单项时执行的动作，暂时没有使用
     */
    @Override
    public void onMenuItemLongClick(View clickedView, int position) {
        Toast.makeText(this, "Long clicked on position: " + position, Toast.LENGTH_SHORT).show();
    }

    /**
     * 调用了onCreate方法，解决动态刷新问题
     */
    @Override
    protected void onRestart() {
        super.onRestart();
        onDestroy();
        onCreate(null);
    }

    /**
     * 显示课程细节，点击修改按钮后跳到编辑界面
     */
    public void showCourseDetails(final Course course) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setContentView(R.layout.details_layout);

        DisplayMetrics dm=this.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.66);          //宽度设置为屏幕的0.66
        dialog.setCanceledOnTouchOutside(true);         // 设置点击屏幕Dialog消失
        dialog.getWindow().setAttributes(p);            //设置生效
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView textView = (TextView) dialog.findViewById(R.id.name_detail);
        textView.setText(course.getCourse_name());

        textView = (TextView) dialog.findViewById(R.id.teacher_detail);
        textView.setText(course.getTutor_name());

        textView = (TextView) dialog.findViewById(R.id.address_detail);
        textView.setText(course.getSite());

        textView = (TextView) dialog.findViewById(R.id.week_detail);
        textView.setText(course.getBegin_week()+" - "+course.getFinish_week());

        textView = (TextView)dialog.findViewById(R.id.edit_course_detail);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,
                        EditActivity.class);
                intent.putExtra("tag_course_id",course.getId());
                startActivity(intent);
            }
        });
    }

    /**
     * 清空当前学期确认函数
     * 若选择是，则清空
     * 否则返回
     */
    private void confirm_claer(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final AlertDialog dialog = builder.create();
        dialog.show();
        dialog.setContentView(R.layout.clear_course_layout);
        DisplayMetrics dm=this.getResources().getDisplayMetrics();
        int displayWidth = dm.widthPixels;
        android.view.WindowManager.LayoutParams p = dialog.getWindow().getAttributes();  //获取对话框当前的参数值
        p.width = (int) (displayWidth * 0.66);    //宽度设置为屏幕的0.66
        dialog.setCanceledOnTouchOutside(true);// 设置点击屏幕Dialog消失
        dialog.getWindow().setAttributes(p);     //设置生效
        dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);

        TextView esc = (TextView)dialog.findViewById(R.id.clear_course_esc);
        esc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        TextView confirm = (TextView)dialog.findViewById(R.id.clear_course_confirm);
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Course.courseClear();
                dialog.dismiss();
            }
        });
    }

    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
