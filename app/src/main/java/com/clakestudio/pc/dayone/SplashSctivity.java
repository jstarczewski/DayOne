package com.clakestudio.pc.dayone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pc on 2017-07-10.
 */

public class SplashSctivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        SharedPreferences sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        if(sharedPreferences.getBoolean("start", false)) {

        Intent MenuActivity = new Intent(this, PasswordActivity.class);
        startActivity(MenuActivity);
            overridePendingTransition(R.animator.translate_enter, R.animator.translate_leave);
        finish();}
        else { Intent MenuActivity = new Intent(this, InfoActivity.class);
            startActivity(MenuActivity);
            overridePendingTransition(R.animator.translate_enter, R.animator.translate_leave);
            finish();}

    }
}

