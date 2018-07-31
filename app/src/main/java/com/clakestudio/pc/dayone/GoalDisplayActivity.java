package com.clakestudio.pc.dayone;

import android.app.FragmentManager;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by pc on 2017-07-13.
 */

public class GoalDisplayActivity extends AppCompatActivity {

    String goal;
    CountDownTimer countDownTimer;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal_display);


    }

    @Override
    protected void onStart() {
        super.onStart();
        final TextView textView = (TextView)findViewById(R.id.textView);
        final EditText editText = (EditText)findViewById(R.id.editText);
        final SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        goal = sharedPreferences.getString("goal", null);
        final Button button = (Button)findViewById(R.id.button6);
        editText.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);

        if (goal!=null) {
            textView.setText(goal);
            goalDisplay();
            button.setVisibility(View.GONE);
        }

        else {
            textView.setVisibility(View.GONE);
            editText.setVisibility(View.VISIBLE);
            button.setVisibility(View.VISIBLE);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (editText.getText().toString().length()<1|| editText.getVisibility()==View.INVISIBLE || editText.getText().toString().equals("")|| editText.getVisibility()==View.GONE) {
                        editText.setText("set your goal" );

                    }
                    else {
                        sharedPreferences.edit().putString("goal", editText.getText().toString()).apply();
                        textView.setText(editText.getText().toString());
                        button.setVisibility(View.GONE);
                        goalDisplay();
                    }
                }
            });

        }

    }


    public void goalDisplay() {

        final TextView textView = (TextView)findViewById(R.id.textView);
        final EditText editText = (EditText)findViewById(R.id.editText);
        final SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);

        editText.setVisibility(View.GONE);
        textView.setVisibility(View.VISIBLE);
        countDownTimer = new CountDownTimer(5000, 10000) {
            @Override
            public void onTick(long l) {

            }

            @Override
            public void onFinish() {


                if (sharedPreferences.getBoolean("optionsFirst", false)) {
                    Intent intent = new Intent(getApplicationContext(), GoalActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
                    finish();
                }
                else {
                    Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
                    finish();
                }
            }
        }.start();
    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onStop() {
        super.onStop();

    }

    @Override
    protected void onPause() {
        super.onPause();

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
