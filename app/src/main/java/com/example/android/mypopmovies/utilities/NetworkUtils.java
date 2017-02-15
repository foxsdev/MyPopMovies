package com.example.android.mypopmovies.utilities;

import android.graphics.*;
import android.net.Uri;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

/**
 * Created by Val on 15/02/2017.
 */
public class NetworkUtils {

    private static final String TAG = NetworkUtils.class.getSimpleName();

    //TODO: Put api key somewhere else in android app project configuration
    public static final String API_KEY = "*Your API KEY here *";

    //Query paths
    private static final String TMDB_BASE_URL = "https://api.themoviedb.org/3";
    private static final String TMDB_DISCOVER_COLLECTION = "discover";
    private static final String TMDB_MOVIE_COLLECTION = "movie";

    //Query paramaters keys
    private static final String API_KEY_KEY = "api_key";
    private static final String SORT_BY_KEY = "sort_by";

    //Query parameters values
    public static final String SORT_BY_VALUE_POPULARITY = "popularity";
    public static final String SORT_BY_VALUE_RATING = "vote_average";
    public static final String SORT_BY_DIRECTION_ASC = ".asc";
    public static final String SORT_BY_DIRECTION_DESC = ".desc";

    public static URL buildUrl(String order_criteria,String api_key){
        Uri buildUri = Uri.parse(TMDB_BASE_URL)
                .buildUpon()
                .appendPath(TMDB_DISCOVER_COLLECTION)
                .appendPath(TMDB_MOVIE_COLLECTION)
                //Sort order
                .appendQueryParameter(SORT_BY_KEY, order_criteria)
                .appendQueryParameter(API_KEY_KEY, api_key)
                .build();

        URL url = null;
        try {
            url=new URL(buildUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        Log.v(TAG, "Built URI " + url);
        return url;
    }

    /**
     * This method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
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

    public static class jsonMovieHelper {
        public static final String POSTER_PATH = "poster_path";
        public static final String OVERVIEW = "overview";
        public static final String RELEASE_DATE = "release_date";
        public static final String ORIGINAL_TITLE = "original_title";
        public static final String TITLE = "title";
        public static final String POPULARITY = "popularity";
        public static final String VOTE_AVERAGE = "vote_average";

        public static final String RESULTS = "results";

        public static Movie[] convertJsonToMoviesArray(String jsonMoviesAnswer) throws JSONException {
            //TODO: Deal with error messages in Json response from TMDB
            JSONObject global_object =  new JSONObject(jsonMoviesAnswer);
            Log.d(TAG, "convertJsonToMoviesArray");
            JSONArray jsonArrayMovies = global_object.getJSONArray(RESULTS);
            int array_size = jsonArrayMovies.length();
            Movie[] moviesArray = new Movie[array_size];
            for (int i = 0; i < array_size; i++) {
                Log.d(TAG,""+i);
                moviesArray[i] = new Movie(jsonArrayMovies.getJSONObject(i));
            }
            return moviesArray;
        }
    }
}
