package com.nitishsharma7.androidarchitectures.searchgenres;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

import com.nitishsharma7.androidarchitectures.models.tagtoptracks.Track;

import java.util.List;

public interface SearchViewContract {
    void displaySearchResults(@NonNull List<Track> searchResults,
                              @Nullable Integer totalCount);


    void displayProgressBar();
    void displayError();

    void displayError(String s);

    void displayEmpty();
}
