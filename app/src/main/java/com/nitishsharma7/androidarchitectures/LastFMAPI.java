package com.nitishsharma7.androidarchitectures;

import android.nfc.Tag;

import com.nitishsharma7.androidarchitectures.models.tagtoptracks.TagTracksResponse;

import java.util.Map;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface LastFMAPI {
    @GET(Endpoints.TAG_INFO)
    Call<Response<Tag>> fetchTagInfo(@Query("tag") String tag,
                                       @QueryMap Map<String, Object> options);
    @GET(Endpoints.TAG_TOP_TRACKS)
    Call<Response<TagTracksResponse>> fetchTagTopTracks(@Query("tag") String tag);

    @GET(Endpoints.TAG_TOP_TRACKS)
    Call<TagTracksResponse>fetchTagTopTracks1(@Query("tag") String tag);

    @GET(Endpoints.TAG_TOP_TRACKS)
    Call<Response<TagTracksResponse>> fetchTagTopTracks(@Query("tag") String tag,
                                                  @QueryMap Map<String,Object> options);

    @GET(Endpoints.TAG_TOP_TRACKS)
    Call<TagTracksResponse> getGenreTopTracks(
            @Query("tag") String genreName,
            @Query("api_key") String apiKey);

}
