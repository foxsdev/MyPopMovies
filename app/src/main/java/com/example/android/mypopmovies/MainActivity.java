package com.example.android.mypopmovies;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import com.example.android.mypopmovies.utilities.NetworkUtils;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView mTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mTextView = (TextView) findViewById(R.id.tv_movie);

        loadMoviesData();
    }

    private void loadMoviesData() {
        new FetchMoviesTask(mTextView)
                .execute(NetworkUtils.SORT_BY_VALUE_POPULARITY +
                        NetworkUtils.SORT_BY_DIRECTION_DESC,
                        NetworkUtils.API_KEY);
    }
}
