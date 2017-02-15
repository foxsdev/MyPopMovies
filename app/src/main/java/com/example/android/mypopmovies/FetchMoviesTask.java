package com.example.android.mypopmovies;


import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

import com.example.android.mypopmovies.utilities.Movie;
import com.example.android.mypopmovies.utilities.NetworkUtils;

import org.json.JSONException;

import java.io.IOException;
import java.net.URL;

import static com.example.android.mypopmovies.utilities.NetworkUtils.getResponseFromHttpUrl;

/**
 * Created by Val on 15/02/2017.
 */
public class FetchMoviesTask extends AsyncTask<String, Void, Movie[]>{
    private static final String TAG = FetchMoviesTask.class.getSimpleName();
    private MoviesAdapter mMoviesAdapter;
    private TextView textView;

    public FetchMoviesTask(TextView textView) {
        Log.d(TAG, "Insanciation");
        this.textView = textView;
    }

    @Override
    protected Movie[] doInBackground(String... params) {
        if (params.length <= 1 ) {
            return null;
        }
        String criteria = params[0];
        String api_key = params[1];
        URL moviesRequestUrl = NetworkUtils.buildUrl(criteria, api_key);
        Movie[] movies = null;
        try {
            String jsonMoviesAnswer = NetworkUtils.getResponseFromHttpUrl(moviesRequestUrl);
            Log.d(TAG, jsonMoviesAnswer);
            movies = NetworkUtils.jsonMovieHelper.convertJsonToMoviesArray(jsonMoviesAnswer);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        } catch (JSONException e) {
            e.printStackTrace();
            return null;
        }
        return movies;
    }

    @Override
    protected void onPostExecute(Movie[] movies) {
        if (movies != null){
            for (Movie movie :
                    movies) {
                textView.append(movie.getOriginalTitle() + "\n");
            }
        }else{
            Log.d(TAG, "Something went wrong while getting movies");
        }
    }
}
