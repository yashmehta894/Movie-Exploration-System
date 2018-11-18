package com.example.dell.movieexplorationsystem;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;

import java.util.ArrayList;

/**
 * Created by Dell on 3/4/2017.
 */
public class GeneralRecommendations extends Activity{

    ArrayList<String> selectedGenres;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.myrecommendatons);
        final CheckBox action = (CheckBox) findViewById(R.id.action);
        CheckBox adventure = (CheckBox) findViewById(R.id.adventure);
        CheckBox animation = (CheckBox) findViewById(R.id.animation);
        CheckBox children = (CheckBox) findViewById(R.id.children);
        CheckBox comedy = (CheckBox) findViewById(R.id.comedy);
        CheckBox crime = (CheckBox) findViewById(R.id.crime);
        CheckBox documentary = (CheckBox) findViewById(R.id.documentary);
        CheckBox drama = (CheckBox) findViewById(R.id.drama);
        Button okBtn = (Button)findViewById(R.id.okBtn);
        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(action.isChecked()){
                    //selectedGenres.add();
                }

            }
        });
    }

}
