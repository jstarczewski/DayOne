package com.clakestudio.pc.dayone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

/**
 * Created by pc on 2017-07-13.
 */

public class PasswordActivity extends AppCompatActivity {

    EditText editText;
    SharedPreferences sharedPreferences;
    TextView textView;
    EditText editTextGoal;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password);

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editText = (EditText)findViewById(R.id.editTextPas);
        textView = (TextView)findViewById(R.id.textView16);
        editTextGoal = (EditText)findViewById(R.id.editText4);


        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                editTextGoal.setVisibility(View.VISIBLE);
                editTextGoal.setOnKeyListener(new View.OnKeyListener() {
                    @Override
                    public boolean onKey(View view, int i, KeyEvent keyEvent) {

                        if (editTextGoal.getText().toString().equals(sharedPreferences.getString("goal", null))) {
                            Intent intent = new Intent(getApplicationContext(), GoalDisplayActivity.class);
                            startActivity(intent);
                            overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
                            finish();
                        }



                        return false;
                    }
                });



            }
        });

        if (sharedPreferences.getBoolean("password", false)) {
            editText.setOnKeyListener(new View.OnKeyListener() {
                @Override
                public boolean onKey(View view, int i, KeyEvent keyEvent) {

                    if (editText.getText().toString().equals(sharedPreferences.getString("passwordString", null))) {

                        Intent intent = new Intent(getApplicationContext(), GoalDisplayActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
                        finish();
                    }
                    return false;
                }
            });
        }
        else {
            Intent intent = new Intent(getApplicationContext(), GoalDisplayActivity.class);
            startActivity(intent);
            overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
            finish();
        }




    }
}
