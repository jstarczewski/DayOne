package com.clakestudio.pc.dayone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.TextView;

/**
 * Created by pc on 2017-07-13.
 */

public class OptionsActivity extends AppCompatActivity {

    CheckBox mySwitch;
    SharedPreferences sharedPreferences;
    EditText editText;
    TextView textView;
    String password;
    CheckBox checkBox;
    EditText editTextGoal;
    CheckBox mySwitch2;
    CheckBox mySwitch3;
    Button apply;
    long time;
    CheckBox checkBox3;
    CheckBox checkBox5;
    CheckBox checkBox8;
    CheckBox checkBox10;




    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_option);
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        editTextGoal = (EditText)findViewById(R.id.editText3);
        editTextGoal.setText(sharedPreferences.getString("goal", null));
        final Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        toolbar.setTitle("Options");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_white_24dp);
        checkBox3 = (CheckBox)findViewById(R.id.checkBox2);
        checkBox5 = (CheckBox)findViewById(R.id.checkBox3);
        checkBox8 = (CheckBox)findViewById(R.id.checkBox4);
        checkBox10 = (CheckBox)findViewById(R.id.checkBox5);



        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                onBackPressed();
            }
        });
        sharedPreferences.edit().putBoolean("optionsFirst", true).apply();
        textView = (TextView)findViewById(R.id.textView3);
        editText = (EditText) findViewById(R.id.editText2);
        mySwitch2 = (CheckBox)findViewById(R.id.switch2);
        apply = (Button)findViewById(R.id.button4);
        apply.setVisibility(View.GONE);
        editText.setVisibility(View.GONE);
        editTextGoal.setVisibility(View.GONE);
        mySwitch = (CheckBox) findViewById(R.id.switch1);
        mySwitch3 = (CheckBox)findViewById(R.id.switch3);

        password = "set password ->";
        editText.setText(sharedPreferences.getString("passwordString", null));
        if (!password.equals(sharedPreferences.getString("passwordString", null))) {

        }
        if (sharedPreferences.getBoolean("expandNote", false)) {
            mySwitch2.setChecked(true);
        }

        mySwitch3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (mySwitch3.isChecked() && apply.getVisibility()==View.GONE ) {

                    editTextGoal.setVisibility(View.VISIBLE);
                    apply.setVisibility(View.VISIBLE);

                    apply.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
                            sharedPreferences.edit().putString("goal", editTextGoal.getText().toString()).apply();
                            editTextGoal.setVisibility(View.GONE);
                            apply.setVisibility(View.GONE);




                        }
                    });

                }
                else {

                    apply.setVisibility(View.GONE);
                    editTextGoal.setVisibility(View.GONE);

                }



            }
        });


        mySwitch2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (mySwitch2.isChecked()) {

                    sharedPreferences.edit().putBoolean("expandNote", true).apply();

                }
                else {
                    sharedPreferences.edit().putBoolean("expandNote", false).apply();
                }

            }
        });

        if (sharedPreferences.getBoolean("password", false)) {
            mySwitch.setChecked(true);
        } else {
            editText.setVisibility(View.GONE);}


            mySwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton compoundButton, boolean b) {


                    if (mySwitch.isChecked() && apply.getVisibility()==View.GONE) {
                        sharedPreferences.edit().putBoolean("password", true).apply();
                        editText.setVisibility(View.VISIBLE);
                        apply.setVisibility(View.VISIBLE);

                        apply.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                if (editText.getText().toString().length() == 4) {

                                    sharedPreferences.edit().putString("passwordString", editText.getText().toString()).apply();
                                    editText.setVisibility(View.GONE);
                                    apply.setVisibility(View.GONE);

                                } else {
                                    textView.setText("must contain 4 characters");

                                }
                            }
                        });



                    } else {
                        sharedPreferences.edit().putBoolean("password", false).apply();
                        apply.setVisibility(View.GONE);
                        editText.setVisibility(View.GONE);
                    }


                }
            });

        if (!checkBox3.isChecked() && !checkBox5.isChecked() && !checkBox8.isChecked() && !checkBox10.isChecked()) {
            checkBox3.setChecked(true);
            sharedPreferences.edit().putBoolean("check3", true).apply();
            sharedPreferences.edit().putLong("focus", 180000).apply();
        }

        if (sharedPreferences.getBoolean("check3", false)) {
            checkBox3.setChecked(true);
            checkBox5.setChecked(false);
            checkBox8.setChecked(false);
            checkBox10.setChecked(false);
        }
        if (sharedPreferences.getBoolean("check5", false)) {
            checkBox5.setChecked(true);
            checkBox3.setChecked(false);
            checkBox8.setChecked(false);
            checkBox10.setChecked(false);
        }
        if (sharedPreferences.getBoolean("check8", false)) {
            checkBox8.setChecked(true);
            checkBox5.setChecked(false);
            checkBox3.setChecked(false);
            checkBox10.setChecked(false);
        }
        if (sharedPreferences.getBoolean("check10", false)) {
            checkBox10.setChecked(true);
            checkBox5.setChecked(false);
            checkBox8.setChecked(false);
            checkBox3.setChecked(false);
        }



        checkBox3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox3.isChecked()) {
                    checkBox5.setChecked(false);
                    checkBox8.setChecked(false);
                    checkBox10.setChecked(false);
                    time = 180000;
                    sharedPreferences.edit().putLong("focus", time).apply();
                    sharedPreferences.edit().putBoolean("check3", true).apply();
                    sharedPreferences.edit().remove("check5").apply();
                    sharedPreferences.edit().remove("check8").apply();
                    sharedPreferences.edit().remove("check10").apply();

                }
            }
        });
        checkBox5.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (checkBox5.isChecked()){
                    checkBox3.setChecked(false);
                    checkBox8.setChecked(false);
                    checkBox10.setChecked(false);
                time = 300000;
                sharedPreferences.edit().putLong("focus", time).apply();
                sharedPreferences.edit().putBoolean("check5", true).apply();
                    sharedPreferences.edit().remove("check3").apply();
                    sharedPreferences.edit().remove("check8").apply();
                    sharedPreferences.edit().remove("check10").apply();
              }
            }
        });
        checkBox8.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

                if (checkBox8.isChecked()){
                    checkBox3.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox10.setChecked(false);
                    time = 480000;
                    sharedPreferences.edit().putLong("focus", time).apply();
                    sharedPreferences.edit().putBoolean("check8", true).apply();
                    sharedPreferences.edit().remove("check5").apply();
                    sharedPreferences.edit().remove("check3").apply();
                    sharedPreferences.edit().remove("check10").apply();
                }


            }
        });
        checkBox10.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (checkBox10.isChecked()){
                    checkBox3.setChecked(false);
                    checkBox5.setChecked(false);
                    checkBox8.setChecked(false);
                    time = 600000;
                    sharedPreferences.edit().putLong("focus", time).apply();
                    sharedPreferences.edit().putBoolean("check10", true).apply();
                    sharedPreferences.edit().remove("check5").apply();
                    sharedPreferences.edit().remove("check8").apply();
                    sharedPreferences.edit().remove("check3").apply();
          }
            }
        }
        );


        }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), GoalActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
        finish();
    }
    public void info(View view) {
        Intent intent = new Intent(getApplicationContext(), ActivityHowTo.class);
        startActivity(intent);
        overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
        finish();
    }
    public  void ext(View view) {
        Intent intent = new Intent(getApplicationContext(), HowToExtendedActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.transalte_enter_reverse, R.animator.translate_leave_reverse);
        finish();
    }


}
