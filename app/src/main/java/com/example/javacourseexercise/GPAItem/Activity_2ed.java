package com.example.javacourseexercise.GPAItem;

import android.app.Dialog;
import android.graphics.Point;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.javacourseexercise.R;
import com.yalantis.contextmenu.lib.ContextMenuDialogFragment;

import org.litepal.crud.DataSupport;

import java.util.ArrayList;
import java.util.List;

public class Activity_2ed extends AppCompatActivity  {

    private List<Activity_2ed_class> activity_2ed_classList = new ArrayList<>();

    private ContextMenuDialogFragment mMenuDialogFragment;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2ed);

        ActionBar actionBar = getSupportActionBar();
        if(actionBar != null){
            actionBar.hide();
        }

        initActivity_2ed_class();
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.activity_2ed_recyclerview);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        final Activity_2ed_Adapter adapter = new Activity_2ed_Adapter(activity_2ed_classList);
        recyclerView.setAdapter(adapter);

        //退出当前活动按钮点击事件


        TextView activity_2ed_eixt = (TextView) findViewById(R.id.activity_2ed_back);
        activity_2ed_eixt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });



        //点击按钮出现对话框，添加recyclerview的item

        FloatingActionButton fab_additem = (FloatingActionButton) findViewById(R.id.activity_2ed_additem);
        fab_additem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // final AlertDialog.Builder builder=new AlertDialog.Builder(Activity_2ed.this);
                final View view = View.inflate(Activity_2ed.this, R.layout.dialog_additem, null);
                final Dialog dialog = new Dialog(Activity_2ed.this);
                final EditText edDate = (EditText) view.findViewById(R.id.dialog_additem_date);
                final EditText edEvent = (EditText) view.findViewById(R.id.dialog_additem_event);
                final EditText edScore = (EditText) view.findViewById(R.id.dialog_additem_score);


                //点击对话框按钮事件
                TextView textView_additem = (TextView) view.findViewById(R.id.dialog_additem_additem);
                textView_additem.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        Activity_2ed_class classadd = new Activity_2ed_class(edDate.getText().toString(), edEvent.getText().toString(), edScore.getText().toString());

                        Activity_2ed_tableclass tableclassadd = new Activity_2ed_tableclass();
                        tableclassadd.setDate(edDate.getText().toString());
                        tableclassadd.setScore(edScore.getText().toString());
                        tableclassadd.setEvent(edEvent.getText().toString());
                        tableclassadd.save();

                        adapter.addData(activity_2ed_classList.size(), classadd);


                        if(edDate.getText().toString().equals("") || edEvent.getText().toString().equals("")
                                || edScore.getText().toString().equals("")){
                            Toast.makeText(Activity_2ed.this, "你填的内容有空的！得分按0处理,请删除此项，以免出现其它错误！", Toast.LENGTH_LONG).show();
                            dialog.dismiss();
                        }
                        else {
                            String s = edScore.getText().toString();
                            int true_score = 0;
                            try{
                                true_score = Integer.parseInt(s);
                            }catch (NumberFormatException e){
                                e.printStackTrace();
                                Toast.makeText(Activity_2ed.this, "填入的得分不合理，得分按照0处理，请删除此项，以免照成其它错误！", Toast.LENGTH_LONG).show();
                                dialog.dismiss();
                            }
                            dialog.dismiss();
                        }


                    }
                });


                //对话框退出按钮
                TextView textView_deleteitem = (TextView) view.findViewById(R.id.dialog_additem_exit);
                textView_deleteitem.setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View v){
                        dialog.dismiss();
                    }
                });


                //对话框配置
                dialog.setContentView(view);
                Point Outsize = new Point();
                dialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
                getWindowManager().getDefaultDisplay().getSize(Outsize);
                dialog.getWindow().setLayout(Outsize.x*4/5, Outsize.y * 3/10);
                dialog.show();

            }

        });


    }

    private void initActivity_2ed_class() {

        List<Activity_2ed_tableclass> activity_2ed_tableclasses = DataSupport.findAll(Activity_2ed_tableclass.class);
        for (Activity_2ed_tableclass activity_2ed_tableclass : activity_2ed_tableclasses) {
            Activity_2ed_class activity_2ed_class = new Activity_2ed_class(activity_2ed_tableclass.getDate(), activity_2ed_tableclass.getEvent(),activity_2ed_tableclass.getScore());
            activity_2ed_classList.add(activity_2ed_class);
        }
    }
}
