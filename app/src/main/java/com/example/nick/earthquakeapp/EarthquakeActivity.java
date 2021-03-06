package com.example.nick.earthquakeapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements OnItemClickListener, LoaderManager.LoaderCallbacks<ArrayList<Earthquake>>, SwipeRefreshLayout.OnRefreshListener {
    private static final String USGF_REQUEST_URL =
            "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson&orderby=time&minmag=5&limit=100";

    RecyclerView earthquakeRecycler;
    ArrayList<Earthquake> earthquakeList;
    EarthquakeRecyclerAdapter earthquakeRecyclerAdapter;
    ProgressBar progressBar;
    SwipeRefreshLayout swipeRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        earthquakeList = new ArrayList<>();
        earthquakeRecycler = (RecyclerView) findViewById(R.id.earthquakeRecycler);
        swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.refreshLayout);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);

        swipeRefreshLayout.setOnRefreshListener(this);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(), DividerItemDecoration.VERTICAL);
        earthquakeRecyclerAdapter = new EarthquakeRecyclerAdapter(earthquakeList, getApplicationContext());
        earthquakeRecyclerAdapter.setOnItemClickListener(EarthquakeActivity.this);
        earthquakeRecycler.setLayoutManager(new LinearLayoutManager(EarthquakeActivity.this));
        earthquakeRecycler.setAdapter(earthquakeRecyclerAdapter);
        earthquakeRecycler.addItemDecoration(itemDecoration);
        earthquakeRecycler.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
        getSupportLoaderManager().initLoader(0, null, EarthquakeActivity.this).forceLoad();

    }

    @Override
    public void onClick(int position, View itemView) {
        String fullUrl = earthquakeList.get(position).getFullUrl();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl));
        startActivity(browserIntent);
    }

    @NonNull
    @Override
    public Loader<ArrayList<Earthquake>> onCreateLoader(int id, @Nullable Bundle args) {
        return new EarthquakeLoader(EarthquakeActivity.this, USGF_REQUEST_URL, earthquakeList);
    }

    @Override
    public void onLoadFinished(@NonNull Loader<ArrayList<Earthquake>> loader, ArrayList<Earthquake> data) {
        if (data!=null){
            earthquakeList = data;
            earthquakeRecyclerAdapter.setEarthquakeList(data);
            earthquakeRecyclerAdapter.notifyDataSetChanged();
        }
        earthquakeRecycler.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        if (swipeRefreshLayout != null) {
            swipeRefreshLayout.setVisibility(View.VISIBLE);
            swipeRefreshLayout.setRefreshing(false);
        }
    }

    @Override
    public void onLoaderReset(@NonNull Loader<ArrayList<Earthquake>> loader) {
        earthquakeRecycler.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        earthquakeList = new ArrayList<>();
        earthquakeRecyclerAdapter.notifyDataSetChanged();
    }

    @Override
    public void onRefresh() {
        swipeRefreshLayout.setRefreshing(true);
        getSupportLoaderManager().initLoader(0, null, EarthquakeActivity.this).forceLoad();
    }
}
