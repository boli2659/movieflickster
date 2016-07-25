package com.codepath.movieapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class Details extends AppCompatActivity {

    RatingBar mRatingBar;
    TextView title;
    TextView overview;
    ImageView poster;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
        mRatingBar = (RatingBar)findViewById(R.id.ratingBar);
        title = (TextView)findViewById(R.id.tvTitleDetails);
        overview = (TextView)findViewById(R.id.tvDetailsOverview);
        poster = (ImageView)findViewById(R.id.ivMovieDetails);
        String t = getIntent().getStringExtra("title");
        String o = getIntent().getStringExtra("overview");
        String p = getIntent().getStringExtra("poster");
        Float r = getIntent().getFloatExtra("rating", 4.5f);
        title.setText(t);
        overview.setText(o);
        Picasso.with(this).load(p).into(poster);
        mRatingBar.setRating(r);
    }
}
