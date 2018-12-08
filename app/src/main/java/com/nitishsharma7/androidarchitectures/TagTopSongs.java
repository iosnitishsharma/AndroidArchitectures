package com.nitishsharma7.androidarchitectures;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.app.SearchManager;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Menu;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.nitishsharma7.androidarchitectures.models.tagtoptracks.TagTracksResponse;
import com.nitishsharma7.androidarchitectures.models.tagtoptracks.Track;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class TagTopSongs extends AppCompatActivity {

    private RecyclerView recyclerView;
    private Toolbar toolbar;
    private SwipeRefreshLayout swipeRefreshRecyclerList;
    private RecyclerViewAdapter mAdapter;
    private ArrayList<Track> modelList = new ArrayList<>();

    private LastFMAPI api;
    private  String searchText ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tag_top_songs);
        findViews();
        initToolbar(getString(R.string.toolbar_title));
        setAdapter();
        setUpSearch();
        swipeRefreshRecyclerList.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                //Customise refresh if needed
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        if (swipeRefreshRecyclerList.isRefreshing())
                            swipeRefreshRecyclerList.setRefreshing(false);
                    }
                }, 5000);

            }
        });

    }

    private void setUpSearch() {
        final EditText genreSearch = findViewById(R.id.search_text);
        Retrofit retrofit =
                new Retrofit.Builder()
                        .addConverterFactory(GsonConverterFactory.create())
                        .baseUrl("https://ws.audioscrobbler.com/2.0/").build();
         api = retrofit.create(LastFMAPI.class);
        genreSearch.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v,
                                          int actionId,
                                          KeyEvent event) {
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    try {
                        searchTagTopSongs(api, genreSearch.getText().toString());
                    } catch (Exception e) {
                        Log.d("Exception123",e.toString());
                        e.printStackTrace();
                    }
                    return true;
                }
                return false;
            }
        });
    }

    private void findViews() {
        toolbar =  findViewById(R.id.toolbar);
        recyclerView = findViewById(R.id.recycler_view);
        swipeRefreshRecyclerList =  findViewById(R.id.swipe_refresh_recycler_list);
    }

    public void initToolbar(String title) {
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(title);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_search, menu);
        SearchView searchView = (SearchView) menu.findItem(R.id.action_search).getActionView();
        SearchManager searchManager = (SearchManager) this.getSystemService(this.SEARCH_SERVICE);
        searchView.setSearchableInfo(searchManager.getSearchableInfo(this.getComponentName()));

        EditText searchEdit = ((EditText) searchView.findViewById(android.support.v7.appcompat.R.id.search_src_text));
        searchEdit.setTextColor(Color.WHITE);
        searchEdit.setHintTextColor(Color.WHITE);
        searchEdit.setBackgroundColor(Color.TRANSPARENT);
        searchEdit.setHint(R.string.search);

        InputFilter[] fArray = new InputFilter[2];
        fArray[0] = new InputFilter.LengthFilter(40);
        fArray[1] = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {

                for (int i = start; i < end; i++) {

                    if (!Character.isLetterOrDigit(source.charAt(i)))
                        return "";
                }
                return null;


            }
        };
        searchEdit.setFilters(fArray);
        View v = searchView.findViewById(android.support.v7.appcompat.R.id.search_plate);
        v.setBackgroundColor(Color.TRANSPARENT);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                ArrayList<Track> filterList = new ArrayList<Track>();
                if (s.length() > 0) {
                    for (int i = 0; i < modelList.size(); i++) {
                        if (modelList.get(i).getName()
                                .toLowerCase().contains(s.toString().toLowerCase())) {
                            filterList.add(modelList.get(i));
                            mAdapter.updateList(filterList);
                        }
                    }

                } else {
                    mAdapter.updateList(modelList);
                }
                return false;
            }
        });


        return true;
    }


    private void setAdapter() {
        mAdapter = new RecyclerViewAdapter(TagTopSongs.this, modelList);
        recyclerView.setHasFixedSize(true);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(layoutManager);


        recyclerView.setAdapter(mAdapter);


        mAdapter.SetOnItemClickListener(new RecyclerViewAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position, Track model) {
                Toast.makeText(TagTopSongs.this, "Hello " + model.getName(), Toast.LENGTH_SHORT).show();
            }
        });
            }

    /**
     * @param api Retrofit interface to fetch data from Lastfm
     * @param query     search last fm  tag query
     */

    private void searchTagTopSongs(LastFMAPI api, String query) {
        Call<TagTracksResponse> call = null;
        try {
           call = api.getGenreTopTracks(query, AppConstants.KEY);
             searchText = query;

           final ProgressDialog progressDialog = new ProgressDialog(TagTopSongs.this);
            progressDialog.setMessage("Please wait...");
            progressDialog.setCancelable(false);
            progressDialog.show();

          call.enqueue(new Callback<TagTracksResponse>() {
            @Override
            public void onResponse(Call<TagTracksResponse> call,
                                   Response<TagTracksResponse> response) {
                handleResponse(response);
                progressDialog.dismiss();
            }

            @Override
            public void onFailure(Call<TagTracksResponse> call,
                                  Throwable t) {
                progressDialog.dismiss();
                showFailedDialog(t.getMessage());
                Log.e("", "", t);
                showErrorMessage(getString(R.string.error));
            }
        });
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private  void showFailedDialog(String message){
        AlertDialog.Builder builder;
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder = new AlertDialog.Builder(TagTopSongs.this, android.R.style.Theme_Material_Dialog_Alert);
        } else {
            builder = new AlertDialog.Builder(TagTopSongs.this);
        }
        builder.setTitle(R.string.error_genric)
                .setMessage(message+getString(R.string.try_again))
                .setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        searchTagTopSongs(api,searchText);
                    }
                })
                .setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();
    }

    private void handleResponse(@NonNull Response<TagTracksResponse> response) {
        if (response.isSuccessful()) {
            TagTracksResponse searchResponse = response.body();
            if (searchResponse != null) {
                handleSearchResults(searchResponse.getTracks().getTrack());
            } else {
                showErrorMessage("Error");
            }
        } else {
            showErrorMessage("Error");
        }
    }

    private void showErrorMessage(String s) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show();
    }


    public void handleSearchResults(List<Track> searchResults) {
        mAdapter.updateList((ArrayList<Track>) searchResults);
    }


}
