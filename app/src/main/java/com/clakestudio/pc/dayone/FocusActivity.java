package com.clakestudio.pc.dayone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.WindowManager;
import android.widget.TextView;

/**
 * Created by pc on 2017-07-14.
 */

public class FocusActivity extends AppCompatActivity {

    TextView textView;
    CountDownTimer countDownTimer;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_focus);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


        final SharedPreferences sharedPreferences= getSharedPreferences("prefs", Context.MODE_PRIVATE);
        textView = (TextView)findViewById(R.id.textView4);


        final MediaPlayer mPlayer = MediaPlayer.create(getApplicationContext(), R.raw.kx); // in 2nd param u have to pass your desire ringtone
        //mPlayer.prepare();

        final long fousTime= sharedPreferences.getLong("focus", 0);
        countDownTimer = new CountDownTimer(fousTime, 1000) {
            @Override
            public void onTick(long l) {
                textView.setText(""+(l/1000));


            }
            @Override
            public void onFinish() {

                    mPlayer.start();
                    getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);


                if (sharedPreferences.getBoolean("focusTimeMore", false)) {
                     sharedPreferences.edit().putBoolean("focusTimeMore", false).apply();
                    Intent intent = new Intent(getApplicationContext(), GoalActivity.class);
                    startActivity(intent);
                    overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
                }
                else {
                sharedPreferences.edit().putBoolean("timer", true).apply();
                sharedPreferences.edit().putBoolean("fabShow", true).apply();
                Intent intent = new Intent(getApplicationContext(), GoalActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
                finish();}


            }
        }.start();



    }

    @Override
    protected void onStop() {

        super.onStop();
        countDownTimer.cancel();


    }

    @Override
    public void onBackPressed() {

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();
    }
}
