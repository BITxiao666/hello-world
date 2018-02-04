package com.example.javacourseexercise;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ChangeWeekActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_week);
        final EditText editText = (EditText) findViewById(R.id.new_current_week);
        editText.setText(String.valueOf(WeekCount.getCurrentWeek()));

        TextView save_button = (TextView) findViewById(R.id.save_button_week);
        save_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WeekCount.reset(Integer.valueOf(editText.getText().toString()));
                Intent intent_back = new Intent(ChangeWeekActivity.this,
                        MainActivity.class);
                startActivity(intent_back);
                finish();
            }
        });

        TextView back_button = (TextView) findViewById(R.id.back_button_week);
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent_back = new Intent(ChangeWeekActivity.this,
                        MainActivity.class);
                startActivity(intent_back);
                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent intent_back = new Intent(ChangeWeekActivity.this,
                MainActivity.class);
        startActivity(intent_back);
        finish();
    }
}
