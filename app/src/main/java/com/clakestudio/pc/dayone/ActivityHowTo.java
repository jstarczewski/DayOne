package com.clakestudio.pc.dayone;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by pc on 2017-07-15.
 */

public class ActivityHowTo extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_how_to);




    }

    @Override
    public void onBackPressed() {

        Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.translate_enter, R.animator.translate_leave);
        finish();

    }
}
