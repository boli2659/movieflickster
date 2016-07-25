package com.codepath.movieapp.models;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class Movie {
    String posterPath;
    String title;
    String overview;
    String backdropPath;
    Double voteAverage;
    public String getPosterPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", posterPath);
    }

    public String getTitle() {
        return title;
    }

    public String getOverview() {
        return overview;
    }

    public String getBackdropPath() {
        return String.format("https://image.tmdb.org/t/p/w342/%s", backdropPath);
    }
    public Double getVoteAverage(){
        return voteAverage;
    }


    public Movie(JSONObject object) throws JSONException {
        this.posterPath = object.getString("poster_path");
        this.title = object.getString("original_title");
        this.overview = object.getString("overview");
        this.backdropPath = object.getString("backdrop_path");
        this.voteAverage = object.getDouble("vote_average");
    }

    public static ArrayList<Movie> fromJSONArray(JSONArray array) {
        ArrayList<Movie> results = new ArrayList<>();

        for(int x = 0; x < array.length(); x++){
            try {
                results.add(new Movie(array.getJSONObject(x)));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return results;
    }
}
