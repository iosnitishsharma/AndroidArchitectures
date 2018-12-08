package com.nitishsharma7.androidarchitectures.reposotories;

import com.nitishsharma7.androidarchitectures.AppConstants;
import com.nitishsharma7.androidarchitectures.lastfmrepository.LastFMAPI;
import com.nitishsharma7.androidarchitectures.lastfmrepository.LastFMRepository;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import retrofit2.Call;
import retrofit2.Callback;

public class LastFMRepositoryTest {

    private LastFMRepository repository;

    @Mock
    private LastFMAPI api;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        repository = Mockito.spy(new LastFMRepository(api));
    }

    @SuppressWarnings("unchecked")
    @Test
    public void searchSongs() {
        /* mocking
        is slower than normal code execution.
         */
        LastFMRepository.Callback githubRepositoryCallback = new LastFMRepository.Callback() {
            @Override
            public void onDataRetrieved(Object data) {

            }

            @Override
            public void onFailed(Throwable t) {

            }

            @Override
            public void onResultEmpty() {

            };
        };

        Call call = Mockito.mock(Call.class);
        String searchQuery = "Pop";
        Mockito.doReturn(call).when(api).getGenreTopTracks(searchQuery, AppConstants.KEY);
        Mockito.doNothing().when(call).enqueue(Mockito.any(Callback.class));
        // trigger
        repository.searchSongsOfGenre(searchQuery, githubRepositoryCallback);
        // validation
        Mockito.verify(api, Mockito.times(1)).getGenreTopTracks(searchQuery,AppConstants.KEY);
    }
}


