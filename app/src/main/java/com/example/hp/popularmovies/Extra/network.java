package com.example.hp.popularmovies.Extra;

/**
 * Created by hp on 11/08/2017.
 */
import android.net.Uri;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class network {
    private static final String format = "json";
    final static String FORMAT_PARAM = "mode";
    public static URL buildUrl (String baseURL)
    { Uri movieUri = Uri.parse(baseURL).buildUpon()
            .appendQueryParameter(FORMAT_PARAM, format)

            .build();

        URL url = null;
        try {
            url = new URL(movieUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }



        return url;


    }
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }

}
