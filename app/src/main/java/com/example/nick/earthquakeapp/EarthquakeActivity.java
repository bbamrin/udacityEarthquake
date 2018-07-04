package com.example.nick.earthquakeapp;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity implements OnItemClickListener {

    RecyclerView earthquakeRecycler;
    ArrayList<Earthquake> earthquakeList;
    EarthquakeRecyclerAdapter earthquakeRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState)  {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        earthquakeList = QueryUtils.extractEarthquakes();
        earthquakeRecyclerAdapter = new EarthquakeRecyclerAdapter(earthquakeList,getApplicationContext());
        earthquakeRecyclerAdapter.setOnItemClickListener(EarthquakeActivity.this);
        earthquakeRecycler = (RecyclerView) findViewById(R.id.earthquakeRecycler);
        earthquakeRecycler.setLayoutManager(new LinearLayoutManager(EarthquakeActivity.this));
        earthquakeRecycler.setAdapter(earthquakeRecyclerAdapter);
        earthquakeRecycler.addItemDecoration(itemDecoration);

    }

    @Override
    public void onClick(int position, View itemView) {
        String fullUrl =  earthquakeList.get(position).getFullUrl();
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(fullUrl));
        Log.d("LOGT","clicked " + position);
        startActivity(browserIntent);
    }
}
