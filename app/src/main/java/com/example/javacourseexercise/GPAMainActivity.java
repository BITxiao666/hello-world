package com.example.javacourseexercise;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.example.javacourseexercise.GPA.GPA;
import com.example.javacourseexercise.GPA.GPAAdapter;
import com.example.javacourseexercise.GPAItem.Activity_1st_tableclass;
import com.example.javacourseexercise.GPAItem.Activity_2ed_tableclass;
import com.example.javacourseexercise.GPAItem.Activity_3rd_tableclass;
import com.example.javacourseexercise.GPAItem.Activity_4th_tableclass;
import com.example.javacourseexercise.GPAItem.Activity_5th_tableclass;
import com.example.javacourseexercise.RadarPac.RadarMainActivity;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class GPAMainActivity extends AppCompatActivity {


    //定义主界面中的GPA 的List
    private List<GPA> gpaList =new ArrayList<>();


    @Override
    protected void onRestart() {
        super.onRestart();
        onDestroy();
        onCreate(null);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpamainactivity);
//        Toolbar toolbar = (Toolbar) findViewById(R.id.gpag_toolbar);
//        setSupportActionBar(toolbar);

        //fab button 点击事件
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.gpa_fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
                Intent intent = new Intent(GPAMainActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        });


        //总分点击事件
        TextView textView_totalscore = (TextView) findViewById(R.id.gpa_totalscore);
        textView_totalscore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GPAMainActivity.this, RadarMainActivity.class);
                startActivity(intent);
            }
        });



        //初始化 RecycleView 中的Item内容
        initGPA();
        //加载gpa_recycleview 控件
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.gpa_recyclerview);
        StaggeredGridLayoutManager layoutManager =new StaggeredGridLayoutManager(
                2, StaggeredGridLayoutManager.VERTICAL
        );

        recyclerView.setLayoutManager(layoutManager);
        GPAAdapter adapter = new GPAAdapter(gpaList);
        recyclerView.setAdapter(adapter);

    }


    //初始化GPA列表函数
    private void initGPA(){

        int sum = 0;
        int sum_1st = 0;
        List<Activity_1st_tableclass> activity_1st_tableclasses = DataSupport.findAll(Activity_1st_tableclass.class);
        for(Activity_1st_tableclass activity_1st_tableclass: activity_1st_tableclasses){
            String s= activity_1st_tableclass.getScore();
            try{
                sum_1st += Integer.parseInt(s);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        String sum_1st_str = String.valueOf(sum_1st);
        sum += sum_1st;

        int sum_2ed = 0;
        List<Activity_2ed_tableclass> activity_2ed_tableclasses = DataSupport.findAll(Activity_2ed_tableclass.class);
        for(Activity_2ed_tableclass activity_2ed_tableclass: activity_2ed_tableclasses){
            String s= activity_2ed_tableclass.getScore();
            try{
                sum_2ed += Integer.parseInt(s);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        String sum_2ed_str = String.valueOf(sum_2ed);
        sum += sum_2ed;

        int sum_3rd = 0;
        List<Activity_3rd_tableclass> activity_3rd_tableclasses = DataSupport.findAll(Activity_3rd_tableclass.class);
        for(Activity_3rd_tableclass activity_3rd_tableclass: activity_3rd_tableclasses){
            String s= activity_3rd_tableclass.getScore();
            try{
                sum_3rd += Integer.parseInt(s);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        String sum_3rd_str = String.valueOf(sum_3rd);
        sum += sum_3rd;


        int sum_4th = 0;
        List<Activity_4th_tableclass> activity_4th_tableclasses = DataSupport.findAll(Activity_4th_tableclass.class);
        for(Activity_4th_tableclass activity_4th_tableclass: activity_4th_tableclasses){
            String s= activity_4th_tableclass.getScore();
            try{
                sum_4th += Integer.parseInt(s);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        String sum_4th_str = String.valueOf(sum_4th);
        sum += sum_4th;


        int sum_5th = 0;
        List<Activity_5th_tableclass> activity_5th_tableclasses = DataSupport.findAll(Activity_5th_tableclass.class);
        for(Activity_5th_tableclass activity_5th_tableclass: activity_5th_tableclasses){
            String s= activity_5th_tableclass.getScore();
            try{
                sum_5th += Integer.parseInt(s);
            }catch (NumberFormatException e){
                e.printStackTrace();
            }
        }

        String sum_5th_str = String.valueOf(sum_5th);
        sum += sum_5th;

        TextView totalSore = (TextView) findViewById(R.id.gpa_totalscore);

        totalSore.setText(String.valueOf(sum));


        gpaList = new ArrayList<>();
        GPA item_1st=new GPA("学生干部", R.drawable.w1, sum_1st_str);
        gpaList.add(item_1st);
        GPA item_2ed=new GPA("志愿活动",R.drawable.w2, sum_2ed_str);
        gpaList.add(item_2ed);
        GPA item_3th=new GPA("学术与竞赛", R.drawable.w3,sum_3rd_str);
        gpaList.add(item_3th);
        GPA item_4th=new GPA("体育", R.drawable.w4, sum_4th_str);
        gpaList.add(item_4th);
        GPA item_5th=new GPA("艺术与修养",R.drawable.w5,sum_5th_str);
        gpaList.add(item_5th);
    }
}
