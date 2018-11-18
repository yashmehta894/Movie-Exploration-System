package com.example.dell.movieexplorationsystem;

/**
 * Created by Dell on 3/4/2017.
 */
public class Movie {

    long movieId;
    String movieName;
    float rating;
    String userRating;

    Movie(long m, float r){
        this.movieName = MainActivity.hm.get(m);
        this.movieId = m;
        this.rating = r;
        this.userRating= "Give Rating";

    }
}
