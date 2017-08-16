package com.example.hp.popularmovies.Extra;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import com.example.hp.popularmovies.MainActivity;
import com.example.hp.popularmovies.R;
import com.squareup.picasso.Picasso;

/**
 * Created by hp on 11/08/2017.
 */

public class MovieAdapter extends ArrayAdapter<movie>  {

    public MovieAdapter(Activity context, ArrayList<movie> movies) {
        super(context, 0, movies);
    }




    @Override
    public View getView(int position, View view, ViewGroup parent) {
        movie movie = getItem(position);

        // Adapters recycle views to AdapterViews.
        // If this is a new View object we're getting, then inflate the layout.
        // If not, this view already has the layout inflated from a previous call to getView,
        // and we modify the View widgets as usual.
        ImageView image;

        if (view == null) {
            view = LayoutInflater.from(getContext()).inflate(
                    R.layout.movie_layout, parent, false);
        }

            image = (ImageView) view.findViewById(R.id.poster);
        Picasso.with(getContext()).load(movie.getMovie_poster_path()).into(image);
        return view;



    }




}
