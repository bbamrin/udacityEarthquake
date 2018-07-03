package com.example.nick.earthquakeapp;


import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import java.util.ArrayList;

public class EarthQuakeRecyclerAdapter extends RecyclerView.Adapter<EarthquakeViewHolder> {

    ArrayList<Earthquake> earthquakeList;


    public EarthQuakeRecyclerAdapter(ArrayList<Earthquake> earthquakeList) {
        this.earthquakeList = earthquakeList;
    }

    @NonNull
    @Override
    public EarthquakeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        EarthquakeViewHolder viewHolder = new EarthquakeViewHolder(inflater.inflate(R.layout.earthquake_item, parent, false));
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull EarthquakeViewHolder holder, int position) {
        Earthquake earthquake = earthquakeList.get(position);
        holder.magnitudeTextView.setText(earthquake.getMagnitude());
        holder.placeTextView.setText(earthquake.getPlace());
        //probably i have to work with date with special variable type instead just using string, but for now string is good
        holder.dateTextView.setText(earthquake.getDate());

    }

    @Override
    public int getItemCount() {
        return earthquakeList.size();
    }
}
