package com.nitishsharma7.androidarchitectures.searchgenres;

import android.support.annotation.NonNull;

import com.nitishsharma7.androidarchitectures.lastfmrepository.LastFMRepository;
import com.nitishsharma7.androidarchitectures.models.tagtoptracks.TagTracksResponse;

public class SearchTopSongsPresenter implements SearchPresenterContract, LastFMRepository
        .Callback<TagTracksResponse> {

    private final SearchViewContract viewContract;
    private final LastFMRepository repository;

      public SearchTopSongsPresenter(@NonNull final SearchViewContract viewContract,
                            @NonNull final LastFMRepository repository) {
        this.viewContract = viewContract;
        this.repository = repository;
    }

    /**
     * fetch data from remote or local sources- repositories
     * The presenter - business logics
     * present in the view
     * @param searchQuery search query e.g pop
     */

    @Override
    public void searchSongs(String searchQuery) {
        viewContract.displayProgressBar();
        if (searchQuery != null && searchQuery.length() > 0) {
            repository.searchSongsOfGenre(searchQuery, this);
        }
    }


    @Override
    public void onDataRetrieved(TagTracksResponse searchResponse) {
            if (searchResponse != null && searchResponse.getTracks() != null) {
                int size = searchResponse.getTracks().getTrack().size();
                viewContract.displaySearchResults(searchResponse.getTracks().getTrack(), 101);
            } else {
                viewContract.displayError("System error");
            }
    }

    @Override
    public void onFailed(Throwable t) {
        viewContract.displayError();
    }

    @Override
    public void onResultEmpty() {
        viewContract.displayEmpty();
    }
}


