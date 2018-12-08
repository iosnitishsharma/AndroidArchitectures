package com.nitishsharma7.androidarchitectures.lastfmrepository;

import com.nitishsharma7.androidarchitectures.models.tagtoptracks.TagTracksResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface LastFMAPI {

    @GET(com.nitishsharma7.androidarchitectures.Endpoints.TAG_TOP_TRACKS)
    Call<TagTracksResponse> getGenreTopTracks(
            @Query("tag") String genreName,
            @Query("api_key") String apiKey);

}
