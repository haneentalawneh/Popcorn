package com.example.hp.popularmovies;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
// this class is mainly used ti recieve any intent sent to this activity and to set tha data that was sent to it in the extra so ther are shown in the right places
public class MovieDetailsActivity extends AppCompatActivity {
    TextView title;
    TextView overview;
    TextView rate;
    TextView releaseDate;
    ImageView poster ;


    String title_Key="Movie title";
    String overview_Key="Movie_overView";
    String poster_Key="Movie poster";
    String release_Key="Movie release date";
    String rate_Key="Movie rate";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        title=(TextView) findViewById(R.id.title);
        overview=(TextView) findViewById(R.id.overview);
        rate=(TextView) findViewById(R.id.rate);
        releaseDate=(TextView) findViewById(R.id.release_date);
        poster=(ImageView) findViewById(R.id.posterview);


        Intent recieved_intent=getIntent();
        if(recieved_intent !=null)
        {
            if (recieved_intent.hasExtra(overview_Key)) {
            String overview_text = recieved_intent.getStringExtra(overview_Key);
            overview.setText(overview_text);

        }
            if (recieved_intent.hasExtra(title_Key)) {
                String title_text = recieved_intent.getStringExtra(title_Key);
                title.setText(title_text);

            }
            if (recieved_intent.hasExtra(rate_Key)) {
                // the rate only gives the number out of 10 so we need to indicate that it's out of 10
                String rate_text = recieved_intent.getStringExtra(rate_Key);
                rate.setText(rate_text+"/10");

            }
            if (recieved_intent.hasExtra(release_Key)) {
                String release_text = recieved_intent.getStringExtra(release_Key);
                // but we only want the year of release so I split the relese_text and get only the first one which represents the year
                String[] release_date_paramters =release_text.split("-");
                releaseDate.setText(release_date_paramters[0]);

            }
            if (recieved_intent.hasExtra(poster_Key)) {
                String poster_text = recieved_intent.getStringExtra(poster_Key);
                Picasso.with(MovieDetailsActivity.this).load(poster_text).into(poster);


            }
        }
    }



}
