package com.example.javacourseexercise.GPA;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.javacourseexercise.GPAItem.Activity_1st;
import com.example.javacourseexercise.GPAItem.Activity_2ed;
import com.example.javacourseexercise.GPAItem.Activity_3rd;
import com.example.javacourseexercise.GPAItem.Activity_4th;
import com.example.javacourseexercise.GPAItem.Activity_5th;
import com.example.javacourseexercise.R;

import java.util.List;



/**
 * Created by Liao on 2018/1/31.
 */

public class GPAAdapter extends RecyclerView.Adapter<GPAAdapter.ViewHolder>{

    private List<GPA> mGPAList;

    static class ViewHolder extends RecyclerView.ViewHolder{

        //注册点击事件
        View gpaView;

        ImageView gpaImage;
        TextView gpaName;
        TextView gpaTotalscore;

        public ViewHolder(View view){
            super(view);
            gpaView = view;
            gpaImage = (ImageView) view.findViewById(R.id.gpa_image);
            gpaName = (TextView) view.findViewById(R.id.gpa_name);
            gpaTotalscore = (TextView) view.findViewById(R.id.gpa_totalscore);
        }
    }

    public GPAAdapter(List<GPA> gpaList){
        mGPAList=gpaList;
    }

    @Override
    public ViewHolder onCreateViewHolder(final ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.gpaitemlayout, parent, false);
        final ViewHolder holder = new ViewHolder(view);
        holder.gpaView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();

                GPA gpa = mGPAList.get(position);

                switch (gpa.getName()){
                    case "学生干部":
                        Intent intent1 = new Intent(parent.getContext(), Activity_1st.class);
                        v.getContext().startActivity(intent1);
                        break;
                    case "志愿活动":
                        Intent intent2 = new Intent(parent.getContext(), Activity_2ed.class);
                        v.getContext().startActivity(intent2);
                        break;
                    case "学术与竞赛":
                        Intent intent3 = new Intent(parent.getContext(), Activity_3rd.class);
                        v.getContext().startActivity(intent3);
                        break;
                    case "体育":
                        Intent intent4 = new Intent(parent.getContext(), Activity_4th.class);
                        v.getContext().startActivity(intent4);
                        break;
                    case "艺术与修养":
                        Intent intent5 = new Intent(parent.getContext(), Activity_5th.class);
                        v.getContext().startActivity(intent5);
                        break;
                    default:
                        break;
                }
            }
        });
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        GPA gpa = mGPAList.get(position);
        holder.gpaImage.setImageResource(gpa.getImageId());
        holder.gpaName.setText(gpa.getName());
        holder.gpaTotalscore.setText(gpa.getTotalScore());

    }

    @Override
    public int getItemCount() {
        return mGPAList.size();
    }
}
