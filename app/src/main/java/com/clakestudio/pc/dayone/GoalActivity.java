package com.clakestudio.pc.dayone;

import android.app.*;
import android.app.Dialog;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.nfc.Tag;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.app.NotificationCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.Toast;


import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class GoalActivity extends AppCompatActivity {

    int cant;
    ArrayList<day> days;
    Toolbar toolbar;
    int day = 0;
    FloatingActionButton floatingActionButton;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    SharedPreferences sharedPreferences;
    EditText editText;
    public boolean fabShow;
    private static final String TAG = "GoalActivity";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goal);
        editText = (EditText)findViewById(R.id.eGoal);
        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        floatingActionButton = (FloatingActionButton)findViewById(R.id.fab);
        fabShow = sharedPreferences.getBoolean("fabShow", false);
        if (fabShow) {
        floatingActionButton.setVisibility(View.GONE);}
        else {
            floatingActionButton.setVisibility(View.VISIBLE);
        }
        getSupportActionBar().setTitle("The Path");
        toolbar.setTitleTextColor(Color.parseColor("#FFFFFF"));
        recyclerView = (RecyclerView)findViewById(R.id.rv);
        recyclerView.setNestedScrollingEnabled(false);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setHasFixedSize(true);
        recyclerView.setNestedScrollingEnabled(false);
        ArrayList<day> books = new ArrayList<>();
        final RVAdapter rvAdapter = new RVAdapter(books);
        recyclerView.setAdapter(rvAdapter);
        cant = sharedPreferences.getInt("cant", 0);
        onBookAdded();

        Log.i(TAG, "Started service");

        final boolean visited = sharedPreferences.getBoolean("has_visited", false);

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (!visited) {
                    DialogFragment dialog = new DialogFragment();
                    dialog.show(getFragmentManager(), "tag");

                    sharedPreferences.edit().putBoolean("has_visited", true).apply();
                }


                SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
                Date todayDate = new Date();
                String thisDate = currentDate.format(todayDate);
                sharedPreferences.edit().putString("date", thisDate).apply();


                day++;
                sharedPreferences.edit().putInt("day", day).apply();
                cant = sharedPreferences.getInt("cant", 0);
                cant++;

                sharedPreferences.edit().putInt("cant", cant).apply();
                FocusActivityIntent();
            }
        });


    }
    public void onBookAdded() {

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);
        days = new ArrayList<>();
        RVAdapter rvBookAdapter = new RVAdapter(days);
        recyclerView.setAdapter(rvBookAdapter);
        for (int i = 1; i<=cant; i++) {
            days.add(new day("Day "+ i, sharedPreferences.getString("authorBook"+i, null), sharedPreferences.getString("infoBook"+i, null)));
        }

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        getMenuInflater().inflate(R.menu.toolbar_buttons, menu);
        return super.onCreateOptionsMenu(menu);
    }
    public void FocusActivityIntent() {
        Intent intent = new Intent(this, FocusActivity.class);
        startActivity(intent);
        overridePendingTransition(R.animator.translate_enter, R.animator.translate_leave);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()){


            case  R.id.more:

                Intent intent = new Intent(getApplicationContext(), OptionsActivity.class);
                startActivity(intent);
                overridePendingTransition(R.animator.translate_enter, R.animator.translate_leave);

                return true;
            case  R.id.focus:


                sharedPreferences.edit().putBoolean("focusTimeMore", true).apply();
                Intent intentFocus = new Intent(getApplicationContext(), FocusActivity.class);
                startActivity(intentFocus);
                overridePendingTransition(R.animator.translate_enter, R.animator.translate_leave);
                finish();
            default:


                return super.onOptionsItemSelected(item);


        }



    }

    @Override
    protected void onStart() {
        super.onStart();

        sharedPreferences = getSharedPreferences("prefs", Context.MODE_PRIVATE);


         SimpleDateFormat currentDate = new SimpleDateFormat("dd/MM/yyyy");
         Date todayDate = new Date();
        String thisDate = currentDate.format(todayDate);

            if (sharedPreferences.getString("date", "hwdp").equals("hwdp")) {

                if (!sharedPreferences.getString("date", null).equals(thisDate)) {
                    timerResault();

                }
            }

    }



    @Override
    public void onBackPressed() {
    }

    public void timerResault() {


        floatingActionButton.setVisibility(View.VISIBLE);
        sharedPreferences.edit().putBoolean("fabShow", false).apply();


    }
    public void holdScreen() {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }
    public void releaseScreen() {
        getWindow().clearFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
    }

    /**
    public void notification() {

        int id = sharedPreferences.getInt("id", 0);
        id++;
        sharedPreferences.edit().putInt("id", id).apply();
        NotificationCompat.Builder mBuilder =
                new NotificationCompat.Builder(this);
        mBuilder.setSmallIcon(R.drawable.ic_launcher);
        mBuilder.setContentTitle("Focus time");
        mBuilder.setContentText("It's time to focus on your day");


        Intent resultIntent = new Intent(this, GoalDisplayActivity.class);
        TaskStackBuilder stackBuilder = TaskStackBuilder.create(this);
        stackBuilder.addParentStack(GoalDisplayActivity.class);

        // Adds the Intent that starts the Activity to the top of the stack
        stackBuilder.addNextIntent(resultIntent);
        PendingIntent resultPendingIntent = stackBuilder.getPendingIntent(0,PendingIntent.FLAG_UPDATE_CURRENT);
        mBuilder.setContentIntent(resultPendingIntent);

        NotificationManager mNotificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        // notificationID allows you to update the notification later on.
        mNotificationManager.notify(id, mBuilder.build());

    } **/
}
