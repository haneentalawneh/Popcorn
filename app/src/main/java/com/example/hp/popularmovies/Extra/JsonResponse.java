package com.example.hp.popularmovies.Extra;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.HttpURLConnection;
import java.util.ArrayList;

/**
 * Created by hp on 11/08/2017.
 */

public class JsonResponse {
    public static ArrayList<movie> getMoviesdataFromJson(Context context, String JsonStr)
            throws JSONException {

        // results is the key in the json file which has elements of the  data to the movies we want
        final String Results = "results";
        final String CODE = "cod";

       ArrayList <movie> movies =new ArrayList<movie>();
        //  this is an array of the object movie that we r going to store in it the data of the movies found from the json object

        JSONObject movieJson = new JSONObject(JsonStr);

        // this code is to check if there's an error
        if (movieJson.has(CODE)) {
            int errorCode = movieJson.getInt(CODE);

            switch (errorCode) {
                case HttpURLConnection.HTTP_OK:
                    break;
                // nothing is wrong
                case HttpURLConnection.HTTP_NOT_FOUND:
          // Invalid
                    return null;
                default:
                  // a problem with the server
                    return null;
            }
        }



        JSONArray JsonMovies = movieJson.getJSONArray(Results);

        // this array has all of the items inside the array that has the key results
        // displaying 20 movies
        for (int i = 0; i < 20; i++) {

            // these are the data of the movies we need to display in this project
            String OverView;
            int id;
            String movie_title;
            String release_date;
            String poster_path;
           double vote_average; // users rate


            //  here we get the Json Object based on the index in the movies array
            JSONObject movieItem = JsonMovies.getJSONObject(i);



          // here we get the the details of each the movieItem JSONObject
            id=movieItem.getInt("id");
            movie_title=movieItem.getString("original_title");
            release_date=movieItem.getString("release_date");
            poster_path=movieItem.getString("poster_path");
            OverView=movieItem.getString("overview");
            vote_average=movieItem.getDouble("vote_average");
           // but the poster path returned from the API is not  completed so we complete the url

           String poster_path_final="http://image.tmdb.org/t/p/w185/"+poster_path;

            // here we set the data that we have collected from the JSONObject into out movies array

            movie new_movie=new movie(OverView,movie_title,id,release_date,poster_path_final,vote_average);

            movies.add(new_movie);










        }

        return movies;
    }

}
