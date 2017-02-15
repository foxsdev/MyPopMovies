package com.example.android.mypopmovies.utilities;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Val on 15/02/2017.
 */

public class Movie {
    private String posterPath;
    private String overview;
    private String releaseDate;
    private String originalTitle;
    private String title;
    private String popularity;
    private String voteAverage;

    public Movie(String posterPath, String overview, String releaseDate, String originalTitle, String title, String popularity, String voteAverage) {
        this.posterPath = posterPath;
        this.overview = overview;
        this.releaseDate = releaseDate;
        this.originalTitle = originalTitle;
        this.title = title;
        this.popularity = popularity;
        this.voteAverage = voteAverage;
    }

    public Movie(JSONObject jsonObject) throws JSONException {
        this.posterPath = jsonObject.getString(NetworkUtils.jsonMovieHelper.POSTER_PATH);
        this.overview = jsonObject.getString(NetworkUtils.jsonMovieHelper.OVERVIEW);
        this.releaseDate = jsonObject.getString(NetworkUtils.jsonMovieHelper.RELEASE_DATE);
        this.originalTitle = jsonObject.getString(NetworkUtils.jsonMovieHelper.ORIGINAL_TITLE);
        this.title = jsonObject.getString(NetworkUtils.jsonMovieHelper.TITLE);
        this.popularity = jsonObject.getString(NetworkUtils.jsonMovieHelper.POPULARITY);
        this.voteAverage = jsonObject.getString(NetworkUtils.jsonMovieHelper.VOTE_AVERAGE);
    }

    public String getPosterPath() {
        return posterPath;
    }

    public String getOverview() {
        return overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public String getOriginalTitle() {
        return originalTitle;
    }

    public String getTitle() {
        return title;
    }

    public String getPopularity() {
        return popularity;
    }

    public String getVoteAverage() {
        return voteAverage;
    }
}
