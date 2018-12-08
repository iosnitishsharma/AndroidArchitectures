package com.nitishsharma7.androidarchitectures.lastfmrepository;

import android.support.annotation.NonNull;
import android.util.Log;

import com.nitishsharma7.androidarchitectures.AppConstants;
import com.nitishsharma7.androidarchitectures.models.tagtoptracks.TagTracksResponse;

import retrofit2.Call;
import retrofit2.Response;

public class LastFMRepository {
    private final LastFMAPI api;
    public LastFMRepository(@NonNull final LastFMAPI lastFMAPI) {
        this.api = lastFMAPI;
    }

    public void searchSongsOfGenre(@NonNull final String query,
                            @NonNull final Callback callback) {
        Call<TagTracksResponse> call = api.getGenreTopTracks(query, AppConstants.KEY);
        call.enqueue(new retrofit2.Callback<TagTracksResponse>() {
            @Override
            public void onResponse(@NonNull Call<TagTracksResponse> call,
                                   @NonNull Response<TagTracksResponse> response) {
                if (response.isSuccessful()) {
                    final TagTracksResponse data = response.body();
                    if (data != null) {
                        callback.onDataRetrieved(data);
                    } else {
                        callback.onResultEmpty();
                    }
                } else {
                    callback.onResultEmpty();
                }
            }

            @Override
            public void onFailure(@NonNull Call<TagTracksResponse> call,
                                  @NonNull Throwable t) {
                Log.e("", "onFailure", t);
                callback.onFailed(t);
            }
        });
    }
    public interface Callback<T> {

        void onDataRetrieved(T data);

        void onFailed(Throwable t);

        void onResultEmpty();
    }

}
