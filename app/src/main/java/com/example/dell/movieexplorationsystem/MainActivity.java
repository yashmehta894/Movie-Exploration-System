package com.example.dell.movieexplorationsystem;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends AppCompatActivity {

    static HashMap<Long,String> hm;
    static ArrayList<Movie> top10Action = new ArrayList<>();
    static ArrayList<Movie> top10Adventure = new ArrayList<>();
    static ArrayList<Movie> top10Animation= new ArrayList<>();
    static ArrayList<Movie> top10Children= new ArrayList<>();
    static ArrayList<Movie> top10Comedy= new ArrayList<>();
    static ArrayList<Movie> top10Crime= new ArrayList<>();
    static ArrayList<Movie> top10Documentary= new ArrayList<>();
    static ArrayList<Movie> top10Drama= new ArrayList<>();


    public void getTop10(ArrayList<Movie> movies,InputStream is){
        BufferedReader reader = new BufferedReader(new InputStreamReader(is));
        try{
            String str;
            int i =1;
            while((str = reader.readLine())!=null && i<=10) {
                String[] tokens = str.split(" ");
                long movieId = Long.parseLong(tokens[0]);
                float rating = Float.parseFloat(tokens[1]);
                movies.add(new Movie(movieId, rating));
                i++;
            }

        }
        catch (IOException e){

        }
    }


    public void getMovies(){
        try{
            InputStream is = this.getResources().openRawResource(
                    R.raw.movies1);
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            //ArrayList<String> lines = new ArrayList<String>();

            String newLine;
            newLine = br.readLine();
            while ((newLine = br.readLine()) != null) {
                //System.out.println(newLine);
                int index1 = newLine.indexOf(",");
                int index2 = newLine.lastIndexOf(",");
                //Log.d("Info",newLine.substring(0,index1));
                long movieId = Long.parseLong(newLine.substring(0, index1));
                String name = newLine.substring(index1 + 1, index2);
                hm.put(movieId, name);
            }


            //lines.add(newLine);
        }catch (IOException i){

        }


    }
static  ProgressDialog pd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hm = new HashMap<>();
        getTop10(top10Action,this.getResources().openRawResource(
                R.raw.output1));
        getTop10(top10Adventure,this.getResources().openRawResource(
                R.raw.output2));
        getTop10(top10Animation,this.getResources().openRawResource(
                R.raw.output3));
        getTop10(top10Children,this.getResources().openRawResource(
                R.raw.output4));
        getTop10(top10Comedy,this.getResources().openRawResource(
                R.raw.output5));
        getTop10(top10Crime,this.getResources().openRawResource(
                R.raw.output6));
        getTop10(top10Documentary,this.getResources().openRawResource(
                R.raw.output7));
        getTop10(top10Drama,this.getResources().openRawResource(
                R.raw.output8));

        Button recommendationBtn = (Button) findViewById(R.id.myrec);
        recommendationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, GeneralRecommendations.class));
            }
        });
        Button action = (Button) findViewById(R.id.action);
        action.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Action.class));
            }
        });
        Button adventure = (Button) findViewById(R.id.adventure);
        adventure.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Adventure.class));
            }
        });

        Button animation = (Button) findViewById(R.id.animation);
        animation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Animation.class));
            }
        });
        Button children = (Button) findViewById(R.id.children);
        children.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Children.class));
            }
        });

        Button comedy = (Button) findViewById(R.id.comedy);
        comedy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Comedy.class));
            }
        });
        Button crime = (Button) findViewById(R.id.crime);
        crime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Crime.class));
            }
        });
        Button doc = (Button) findViewById(R.id.documentary);
        doc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Documentary.class));
            }
        });
        Button drama = (Button) findViewById(R.id.drama);
        drama.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Drama.class));
            }
        });





        new AsyncTask<Void, Void, Void>() {
            @Override
            protected Void doInBackground(Void... params) {
                getMovies();

                return null;
            }// End of doInBackground method

        }.execute((Void[]) null);

    }
}
