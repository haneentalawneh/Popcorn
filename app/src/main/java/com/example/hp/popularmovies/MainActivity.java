package com.example.hp.popularmovies;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.hp.popularmovies.Extra.MovieAdapter;
import com.example.hp.popularmovies.Extra.movie;
import com.example.hp.popularmovies.Extra.JsonResponse;
import com.example.hp.popularmovies.Extra.network;
import com.squareup.picasso.Picasso;

import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity extends AppCompatActivity {
    /*These are all of the importnat details of a movies so the can passsed to the details pag*/
    String title_Key = "Movie title";
    String overview_Key = "Movie_overView";
    String poster_Key = "Movie poster";
    String release_Key = "Movie release date";
    String rate_Key = "Movie rate";

    MovieAdapter movieAdapter;
    GridView view;
    ArrayList<movie> jsonMovieData = null;
    private ArrayList<movie> movieSavedList = null;

    private static final String MOVIE_BASE_URL_POPULAR = "http://api.themoviedb.org/3/movie/popular?api_key=";// please add your api key
    private static final String MOVIE_BASE_URL_RATE = "http://api.themoviedb.org/3/movie/top_rated?api_key=";// please add your api key

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        view = (GridView) findViewById(R.id.baselayout);


        URL T = network.buildUrl(MOVIE_BASE_URL_POPULAR);

/* to make the application responsive to device rotation so the UI won't crash we change the number of volumn base on the orientation */
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE)

        {
            view.setNumColumns(4);
        }
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT)

        {
            view.setNumColumns(2);
            new FetchMoviesData().execute(T);
        }

/*checking if the saved intance hasn't cotained any new data yet so we can fetch an AsyncTask else we get the paracble array of movies */
        if (savedInstanceState == null || !savedInstanceState.containsKey("movies")) {
            new FetchMoviesData().execute(T);
        } else

        {

            movieSavedList = savedInstanceState.getParcelableArrayList("movies");
            adapt(movieSavedList);

        }

    }

/*in the case that the app saves instance state it gets the jsonMoviedata and gives it a key "movies"*/
    @Override
    public void onSaveInstanceState(Bundle outState) {

        outState.putParcelableArrayList("movies", jsonMovieData);


        super.onSaveInstanceState(outState);

    }
    /*this inner class take the bult URL and builds an http connection then gets the data we want using JSON */

    public class FetchMoviesData extends AsyncTask<URL, Void, ArrayList<movie>> {

        // Override the doInBackground
        @Override
        protected ArrayList<movie> doInBackground(URL... params) {


            if (params.length == 0) {
                return null;

            }

            URL movieUrl = params[0];

            try {
                String jsonMovieResponse = network
                        .getResponseFromHttpUrl(movieUrl);


                jsonMovieData = JsonResponse.getMoviesdataFromJson(MainActivity.this, jsonMovieResponse);


                return jsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPreExecute() {


        }

        //  Overriding the onPostExecute method to display the image from the results of the network request and to initilize our custom adapter giving it the array of movies
        @Override
        protected void onPostExecute(final ArrayList<movie> movieData) {
            /* if the moviedata is null this will indicate that there's no internet connection so the app just shows a toast*/
            if (movieData == null) {
                Toast.makeText(MainActivity.this, "No Internet connection , please Try again!!", Toast.LENGTH_LONG).show();

            } else {
                adapt(movieData);

            }

        }
    }
/*this function declars a new adapter of the time movie adapter and sets the main layout  the grid view ti it and it passes the array found in the results */
        public void adapt(ArrayList<movie> movies) {

            if (movies != null) {
                movieAdapter = new MovieAdapter(MainActivity.this, movies);
                view.setAdapter(movieAdapter);

                view.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l)// this funcion is called whenever in item in grid iew is clicked so it can get the item that was clcked based on the index i */
                    {
                        movie chosen_movie = movieAdapter.getItem(i);
                        moveToDetails(chosen_movie);


                    }
                });


            }
        }
/*this function is called whenever an item is clciked so it can create an intent and move all of the important details as extra*/
        public void moveToDetails(movie movie) {
            String RELEASE = movie.getMovie_release_date();
            String TITLE = movie.getMovie_title();
            String OVERVIEW = movie.getMovie_overview();
            String RATE = String.valueOf(movie.getMovie_rate());
            String POSTER = movie.getMovie_poster_path();

            Intent intent = new Intent(MainActivity.this, MovieDetailsActivity.class);
            intent.putExtra(overview_Key, OVERVIEW);
            intent.putExtra(poster_Key, POSTER);
            intent.putExtra(rate_Key, RATE);
            intent.putExtra(release_Key, RELEASE);
            intent.putExtra(title_Key, TITLE);

            startActivity(intent);

        }

        @Override
        public boolean onCreateOptionsMenu(Menu menu) {

            MenuInflater menuInflater = getMenuInflater();
            menuInflater.inflate(R.menu.menu, menu);
            return true;
        }

        @Override
        public boolean onOptionsItemSelected(MenuItem item) {

            int id = item.getItemId();
            if (id == R.id.popular) // if the item that was clicked from the menu is popular we fetch a new async  task and give the popular movies bas url
            {
                URL T = network.buildUrl(MOVIE_BASE_URL_POPULAR);
                new FetchMoviesData().execute(T);

                return true;
            }
            if (id == R.id.rate)
            // if the item that was clicked from the menu is rate we fetch a new async  task and give the top rated movies bas url
                {
                URL T = network.buildUrl(MOVIE_BASE_URL_RATE);
                new FetchMoviesData().execute(T);

                return true;
            }
            return false;
        }

    }