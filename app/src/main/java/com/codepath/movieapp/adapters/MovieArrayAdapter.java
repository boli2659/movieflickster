package com.codepath.movieapp.adapters;


import android.content.Context;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.codepath.movieapp.R;
import com.codepath.movieapp.models.Movie;
import com.squareup.picasso.Picasso;

import java.util.List;

public class MovieArrayAdapter extends ArrayAdapter<Movie> {

    private static class ViewHolder {
        TextView title;
        TextView overview;
        volatile ImageView movieImage;
    }

    public MovieArrayAdapter(Context context, List<Movie> movies) {
        super(context, android.R.layout.simple_list_item_1, movies);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Movie movie = getItem(position);

        ViewHolder viewHolder;
        int orientation = getContext().getResources().getConfiguration().orientation;
        if (convertView == null) {
            viewHolder = new ViewHolder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            if(movie.getVoteAverage() > 4.9) {
                convertView = inflater.inflate(R.layout.item_movie_popular, parent, false);
                viewHolder.title = (TextView) convertView.findViewById(R.id.tvMovieTitle);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.tvMovieOverview);
                viewHolder.movieImage = (ImageView) convertView.findViewById(R.id.ivMovie);
            } else {
                convertView = inflater.inflate(R.layout.item_movie, parent, false);

                viewHolder.title = (TextView) convertView.findViewById(R.id.tvTitle);
                viewHolder.overview = (TextView) convertView.findViewById(R.id.tvOverview);
                viewHolder.movieImage = (ImageView) convertView.findViewById(R.id.ivMovieImage);
            }
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.movieImage.setImageResource(0);

        viewHolder.title.setText(movie.getTitle());
        viewHolder.overview.setText(movie.getOverview());

        if (orientation == Configuration.ORIENTATION_PORTRAIT && movie.getVoteAverage() < 4.9) {
            Picasso.with(getContext()).load(movie.getPosterPath()).placeholder(R.drawable.film)
                    .error(R.drawable.film).into(viewHolder.movieImage);
        } else {
            Picasso.with(getContext()).load(movie.getBackdropPath()).placeholder(R.drawable.film)
                    .error(R.drawable.film).into(viewHolder.movieImage);
            if (movie.getVoteAverage() > 4.9) {
                Picasso.with(getContext()).load(movie.getBackdropPath()).fit().centerCrop().placeholder(R.drawable.film)
                        .error(R.drawable.film).into(viewHolder
                        .movieImage);
            }

        }

        return convertView;
    }
}
