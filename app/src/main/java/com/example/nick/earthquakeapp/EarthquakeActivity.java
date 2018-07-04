package com.example.nick.earthquakeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    RecyclerView earthquakeRecycler;
    ArrayList<Earthquake> earthquakeList;
    EarthquakeRecyclerAdapter earthquakeRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);
        DividerItemDecoration itemDecoration = new DividerItemDecoration(getApplicationContext(),DividerItemDecoration.VERTICAL);
        earthquakeList = QueryUtils.extractEarthquakes();
        earthquakeRecyclerAdapter = new EarthquakeRecyclerAdapter(earthquakeList,getApplicationContext());
        earthquakeRecycler = (RecyclerView) findViewById(R.id.earthquakeRecycler);
        earthquakeRecycler.setLayoutManager(new LinearLayoutManager(EarthquakeActivity.this));
        earthquakeRecycler.setAdapter(earthquakeRecyclerAdapter);
        earthquakeRecycler.addItemDecoration(itemDecoration);


    }
}
