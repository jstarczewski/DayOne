package com.clakestudio.pc.dayone;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2017-07-13.
 */

public class RVAdapter extends RecyclerView.Adapter<RVAdapter.DayHolder> {


    Context context;
    SharedPreferences sharedPreferences;
    CardView cardViewBook;
    int kk;
    int jj;



    public static class DayHolder extends RecyclerView.ViewHolder {


        CardView cardView;
        TextView titel;
        TextView author;
        TextView info;
        EditText eTextAuthor;
        EditText eTextNote;
        ImageButton imageButton;


        public DayHolder(View itemView) {
            super(itemView);

            cardView = (CardView)itemView.findViewById(R.id.bookCardView);
            titel = (TextView)itemView.findViewById(R.id.titel);
            author = (TextView)itemView.findViewById(R.id.author);
            info = (TextView)itemView.findViewById(R.id.info);
            eTextAuthor = (EditText)itemView.findViewById(R.id.editTextAuthor);
            eTextNote = (EditText)itemView.findViewById(R.id.editTextNote);
            imageButton = (ImageButton)itemView.findViewById(R.id.imageButton);



        }
    }



    private List<day> days = new ArrayList<>();
    RVAdapter(List<day> days) {
        this.days = days;
    }




    @Override
    public DayHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardnote, parent, false);
        final DayHolder dayHolder = new DayHolder(view);
        context = view.getContext();
        sharedPreferences = context.getSharedPreferences("prefs", Context.MODE_PRIVATE);
        cardViewBook = dayHolder.cardView;
        final GoalActivity goalActivity = (GoalActivity)context;
        if (sharedPreferences.getBoolean("expandNote", false)) {

            dayHolder.imageButton.setVisibility(View.VISIBLE);
            dayHolder.info.setVisibility(View.GONE);
            dayHolder.imageButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    if (dayHolder.info.getVisibility()==View.GONE && dayHolder.eTextAuthor.getVisibility()==View.GONE) {
                        dayHolder.info.setVisibility(View.VISIBLE);
                    }
                    else {
                        dayHolder.info.setVisibility(View.GONE);
                    }


                }
            });


        }
        else {
            dayHolder.imageButton.setVisibility(View.GONE);
            dayHolder.info.setVisibility(View.VISIBLE);
        }

            dayHolder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {


                    if (dayHolder.getAdapterPosition()==(days.size()-1)) {






                        if (dayHolder.author.getVisibility() == View.VISIBLE) {


                            dayHolder.author.setVisibility(View.GONE);
                            dayHolder.info.setVisibility(View.GONE);


                            dayHolder.eTextAuthor.setVisibility(View.VISIBLE);
                            dayHolder.eTextNote.setVisibility(View.VISIBLE);
                            goalActivity.holdScreen();

                            for (int i = 0; i <= dayHolder.getAdapterPosition(); i++) {
                                jj++;
                            }

                            dayHolder.eTextAuthor.setText(sharedPreferences.getString("authorBook" + jj, null));
                            dayHolder.eTextNote.setText(sharedPreferences.getString("infoBook" + jj, null));

                            jj = 0;


                        } else {
                            dayHolder.titel.setVisibility(View.VISIBLE);
                            dayHolder.author.setVisibility(View.VISIBLE);

                            if (sharedPreferences.getBoolean("expandNote", false)) {

                                dayHolder.info.setVisibility(View.GONE);
                            }
                            else {
                                dayHolder.info.setVisibility(View.VISIBLE);
                            }

                            for (int i = 0; i <= dayHolder.getAdapterPosition(); i++) {
                                kk++;
                            }
                            sharedPreferences.edit().putString("authorBook" + kk, dayHolder.eTextAuthor.getText().toString()).apply();
                            sharedPreferences.edit().putString("infoBook" + kk, dayHolder.eTextNote.getText().toString()).apply();


                            kk = 0;
                            dayHolder.author.setText(dayHolder.eTextAuthor.getText().toString());
                            dayHolder.info.setText(dayHolder.eTextNote.getText().toString());


                            dayHolder.eTextAuthor.setVisibility(View.GONE);
                            dayHolder.eTextNote.setVisibility(View.GONE);
                            goalActivity.releaseScreen();

                        }
                    }
                    else {}

                }
            });



        return dayHolder;
    }

    @Override
    public void onBindViewHolder(DayHolder holder, int position) {


             holder.titel.setText(days.get(position).day);
            holder.author.setText(days.get(position).titel);
            holder.info.setText(days.get(position).note);


    }

    @Override
    public int getItemCount() {
        return days.size();
    }



}
