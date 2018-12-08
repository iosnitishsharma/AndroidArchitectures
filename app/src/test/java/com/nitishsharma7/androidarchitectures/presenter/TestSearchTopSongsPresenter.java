package com.nitishsharma7.androidarchitectures.presenter;

import com.nitishsharma7.androidarchitectures.lastfmrepository.LastFMRepository;
import com.nitishsharma7.androidarchitectures.models.tagtoptracks.TagTracksResponse;
import com.nitishsharma7.androidarchitectures.models.tagtoptracks.Track;
import com.nitishsharma7.androidarchitectures.models.tagtoptracks.Tracks;
import com.nitishsharma7.androidarchitectures.searchgenres.SearchTopSongsPresenter;
import com.nitishsharma7.androidarchitectures.searchgenres.SearchViewContract;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import retrofit2.Response;

public class TestSearchTopSongsPresenter {
    private SearchTopSongsPresenter presenter;

    @Mock
    private LastFMRepository repository;
    @Mock
    private SearchViewContract viewContract;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        presenter = Mockito.spy(new SearchTopSongsPresenter(viewContract, repository));
    }



    @Test
    public void searchSongWithNoQuery() {
        String searchQuery = null;

        // Trigger
        presenter.searchSongs(searchQuery);

        // Validation
        Mockito.verify(repository, Mockito.never()).searchSongsOfGenre(searchQuery, presenter);
    }

    @Test
    public void searchSongWithTag() {
        String searchQuery = "Pop";

        // Trigger
        presenter.searchSongs(searchQuery);

        // Validation
        Mockito.verify(repository, Mockito.times(1)).searchSongsOfGenre(searchQuery, presenter);
    }



    @SuppressWarnings("unchecked")
    @Test
    public void handleSuccessResponse() {

        TagTracksResponse tracksResponse = Mockito.mock(TagTracksResponse.class);


        Response response = Mockito.mock(Response.class);

        Tracks tracks = Mockito.mock(Tracks.class);

       // Tracks tracks = new Tracks();

        List<Track> searchResults = Collections.singletonList(new Track());

      //  Mockito.when(tracksResponse.getTracks()).thenReturn(tracks);

        Mockito.when(tracksResponse.getTracks()).thenReturn(tracks);

      //  Mockito.when(tracksResponse.getTracks().getTrack().size()).thenReturn(101);

        Mockito.when(tracksResponse.getTracks().getTrack()).thenReturn(searchResults);



       // Mockito.when(tracksResponse.getTracks().getTrack().size()).thenReturn(101);


       // Mockito.doReturn(tracks).when(tracksResponse.getTracks());
//
//        Mockito.doReturn(searchResults).when(tracks.getTrack());
//
//        Mockito.doReturn(101).when(searchResults.size());


    //  1)  searchResponse.getTracks()

      //2  searchResponse.getTracks().getTrack(),

           // 3_
        //     searchResponse.getTracks().getTrack().size());

        // Trigger
        presenter.onDataRetrieved(tracksResponse);

        // Validation
        Mockito.verify(viewContract, Mockito.times(1)).displaySearchResults(searchResults,
                101);
    }


}
