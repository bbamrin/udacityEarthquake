package com.example.nick.earthquakeapp;



import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.content.AsyncTaskLoader;

import java.util.ArrayList;

public class EarthquakeLoader extends AsyncTaskLoader<ArrayList<Earthquake>> {

    String url;
    ArrayList<Earthquake> earthquakes;

    public EarthquakeLoader(@NonNull Context context,String url,ArrayList<Earthquake> earthquakes) {
        super(context);
        this.url = url;
        this.earthquakes = earthquakes;
    }

    @Override
    public ArrayList<Earthquake> loadInBackground() {

        earthquakes = QueryUtils.extractEarthquakes(this.url);
        return earthquakes;
    }
}
