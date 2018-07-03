package com.example.nick.earthquakeapp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class EarthquakeActivity extends AppCompatActivity {

    RecyclerView earthquakeRecycler;
    ArrayList<Earthquake> earthquakeList;
    EarthQuakeRecyclerAdapter earthQuakeRecyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_earthquake);

        earthquakeList = new ArrayList<>();
        Earthquake earthquake = new Earthquake();
        earthquake.setDate("s");
        earthquake.setPlace("s");
        earthquake.setMagnitude("322");
        earthquakeList.add(earthquake);
        earthquakeList.add(earthquake);
        earthquakeList.add(earthquake);
        earthquakeList.add(earthquake);
        earthquakeList.add(earthquake);
        earthQuakeRecyclerAdapter = new EarthQuakeRecyclerAdapter(earthquakeList);
        earthquakeRecycler = (RecyclerView) findViewById(R.id.earthquakeRecycler);
        earthquakeRecycler.setLayoutManager(new LinearLayoutManager(EarthquakeActivity.this));
        earthquakeRecycler.setAdapter(earthQuakeRecyclerAdapter);

    }
}
