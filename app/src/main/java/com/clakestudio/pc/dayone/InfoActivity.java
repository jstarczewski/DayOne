package com.clakestudio.pc.dayone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * Created by pc on 2017-07-14.
 */

public class InfoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);




    }
    public void go(View view){
        SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        sharedPreferences.edit().putBoolean("start", true).apply();
        Intent intent = new Intent(getApplicationContext(), GoalDisplayActivity.class);
        startActivity(intent);
        finish();
    }
}
