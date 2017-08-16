package com.example.hp.popularmovies.Extra;

/**
 * Created by hp on 11/08/2017.
 */
import android.os.Parcel;
import android.os.Parcelable;
//
public class movie implements Parcelable {
    // this is the class that has the basic information  of each movie required in this project
// it implements parcable so it can be parced aytime we need it to in the mainActivity so we won't have to always request the json object from the internet
    private String movie_overview;
    private int movie_id;
    private String movie_title;
    private String movie_release_date;
    private String movie_poster_path;
    private double  movie_rate;
    // in this block are the settrs and the getters of the variables for the class movie
    public void setMovie_overview(String overview)
    {
        movie_overview=overview;
    }
    public  void setMovie_id(int id)
    {
        movie_id=id;
    }
    public  void setMovie_poster_path(String poster_path)
    {
        movie_poster_path=poster_path;
    }
    public  void setMovie_release_date(String release_date)
    {
        movie_release_date=release_date;
    }
    public  void setMovie_title(String title)
    {
        movie_title=title;
    }
    public void setMovie_rate(double rate)
    {
        movie_rate=rate;
    }

    public int getMovie_id()
    {
        return movie_id;

    }

    public String getMovie_overview()
    {
        return movie_overview;
    }
    public String getMovie_title()
    {
        return  movie_title;

    }
    public String getMovie_release_date()
    {
        return movie_release_date;
    }
    public String getMovie_poster_path()
    {
        return movie_poster_path;
    }
    public double getMovie_rate()
    {
        return movie_rate;
    }

    @Override
    public int describeContents() {
        return 0;
    }


    public movie(String overView, String title, int id, String release_date,String poster_path ,double rate)
    {
        this.movie_overview =overView;
        this.movie_id =id;
        this.movie_rate = rate;
        this.movie_title=title;
        this.movie_poster_path=poster_path;
        this.movie_release_date =release_date;
    }

    private movie(Parcel in){
        movie_overview = in.readString();
       movie_poster_path= in.readString();
        movie_title = in.readString();
        movie_release_date= in.readString();
         movie_id= in.readInt();
        movie_rate= in.readDouble();
    }



    public String toString() { return movie_overview+ "--" + movie_title + "--" + movie_id+"--"+movie_rate+"--"+movie_poster_path+"--"+movie_release_date; }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(movie_overview);
        parcel.writeString(movie_poster_path);
        parcel.writeString(movie_release_date);
        parcel.writeString(movie_title);
        parcel.writeInt(movie_id);
        parcel.writeDouble(movie_rate);
    }

    public final Parcelable.Creator<movie> CREATOR = new Parcelable.Creator<movie>() {
        @Override
        public movie createFromParcel(Parcel parcel) {
            return new movie(parcel);
        }

        @Override
        public movie[] newArray(int i) {
            return new movie[i];
        }

    };
}
